package nl.harm27.obs.websocket.generator.generators.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helger.jcodemodel.*;
import nl.harm27.obs.websocket.generator.generators.generic.*;

import java.util.Arrays;
import java.util.List;

public class RequestsBaseGenerator extends GenericBaseGenerator {
    private final JPackage basePackageModel;
    private final JPackage senderPackageModel;
    private final List<String> requestNames;
    private JDefinedClass requestTypeEnum;
    private JDefinedClass baseBuilderClass;
    private JDefinedClass baseRequestClass;
    private JDefinedClass requestSenderClass;
    private JDefinedClass baseResponseClass;

    public RequestsBaseGenerator(JPackage basePackageModel, JPackage senderPackageModel, TypeManager typeManager, List<String> requestNames) {
        super(typeManager);
        this.basePackageModel = basePackageModel;
        this.senderPackageModel = senderPackageModel;
        this.requestNames = requestNames;
    }

    public void generate() throws JCodeModelException, UnknownTypeException {
        requestTypeEnum = generateEnum(basePackageModel, "RequestType", requestNames, StringConstants.BASE_REQUEST_TYPE_JAVADOC);
        generateBaseRequest();
        generateBaseSender();
    }

    private void generateBaseRequest() throws JCodeModelException, UnknownTypeException {
        generateResponseClass();
        generateRequestClass();
        generateBuilderClass();
    }

    private void generateBuilderClass() throws JCodeModelException {
        baseBuilderClass = basePackageModel._class(JMod.PUBLIC | JMod.ABSTRACT, "BaseBuilder");
        generateJavadocForClass(baseBuilderClass.javadoc(), StringConstants.BASE_BUILDER_JAVADOC, StringConstants.REQUESTS_URL_PART);
        generateRequestMethodHelpers(baseBuilderClass);
    }

    private void generateRequestClass() throws JCodeModelException, UnknownTypeException {
        baseRequestClass = basePackageModel._class(JMod.PUBLIC | JMod.ABSTRACT, "BaseRequest");
        generateJavadocForClass(baseRequestClass.javadoc(), StringConstants.BASE_REQUEST_JAVADOC, StringConstants.REQUESTS_URL_PART);

        JFieldVar requestTypeField = generateFieldForProperty(baseRequestClass, FunctionType.GETTER, new Field(requestTypeEnum, "requestType", "request-type", StringConstants.BASE_REQUEST_TYPE_JAVADOC));
        JFieldVar messageIdField = generateFieldForProperty(baseRequestClass, FunctionType.GETTER, new Field(StringConstants.MESSAGE_ID_FIELD, "message-id", StringConstants.STRING_TYPE, StringConstants.BASE_REQUEST_MESSAGE_ID_JAVADOC));

        generateRequestConstructor(requestTypeField, messageIdField);
        generateRequestMethods();
    }

    private void generateRequestMethods() {
        JMethod getResponseTypeMethod = baseRequestClass.method(JMod.PUBLIC | JMod.ABSTRACT, typeManager.getAnyClassType(), "getResponseType");
        getResponseTypeMethod.javadoc().add(StringConstants.BASE_REQUEST_GET_RESPONSE_TYPE_METHOD_JAVADOC);

        JMethod getRequestNameMethod = baseRequestClass.method(JMod.PUBLIC | JMod.ABSTRACT, typeManager.getPrimitiveType(StringConstants.STRING_TYPE), "getRequestName");
        getRequestNameMethod.javadoc().add(StringConstants.BASE_REQUEST_NAME_METHODE_JAVADOC);

        JMethod isAuthenticationRequiredMethod = baseRequestClass.method(JMod.PUBLIC, typeManager.getPrimitiveType("boolean"), "isAuthenticationRequired");
        isAuthenticationRequiredMethod.javadoc().add(StringConstants.BASE_REQUEST_AUTHENTICATION_REQUIRED_METHOD);
        isAuthenticationRequiredMethod.body()._return(JExpr.lit(true));
    }

    private void generateRequestConstructor(JFieldVar requestTypeField, JFieldVar messageIdField) {
        JMethod constructor = baseRequestClass.constructor(JMod.PUBLIC);
        JVar requestTypeVar = constructor.param(requestTypeEnum, "requestType");
        JVar messageIdVar = constructor.param(typeManager.getPrimitiveType(StringConstants.STRING_TYPE), StringConstants.MESSAGE_ID_FIELD);

        JBlock body = constructor.body();
        body.add(JExpr._this().ref(requestTypeField).assign(requestTypeVar));
        body.add(JExpr._this().ref(messageIdField).assign(messageIdVar));
    }

    private void generateResponseClass() throws JCodeModelException, UnknownTypeException {
        baseResponseClass = basePackageModel._class(JMod.PUBLIC | JMod.ABSTRACT, "BaseResponse");
        generateJavadocForClass(baseResponseClass.javadoc(), StringConstants.BASE_RESPONSE_JAVADOC, StringConstants.REQUESTS_URL_PART);
        generateFieldForProperty(baseResponseClass, FunctionType.GETTER, new Field(StringConstants.MESSAGE_ID_FIELD, "message-id", StringConstants.STRING_TYPE, StringConstants.BASE_RESPONSE_MESSAGE_ID_JAVADOC));
        JDefinedClass statusEnum = generateEnum(basePackageModel, "Status", Arrays.asList("ok", "error"), "Status of the response");
        generateFieldForProperty(baseResponseClass, FunctionType.GETTER, new Field(statusEnum, "status", "status", StringConstants.BASE_RESPONSE_STATUS_JAVADOC));
        generateOptionalField(baseResponseClass, typeManager.getPrimitiveType(StringConstants.STRING_TYPE));
    }

    private void generateBaseSender() throws JCodeModelException {
        requestSenderClass = senderPackageModel._class(JMod.ABSTRACT | JMod.PUBLIC, "RequestSender");
        generateRequestMethodHelpers(requestSenderClass);
    }

    protected void generateOptionalField(JDefinedClass baseClass, AbstractJType typeClass) {
        JFieldVar field = baseClass.field(JMod.PRIVATE, typeClass, StringConstants.ERROR_FIELD_NAME);
        field.annotate(JsonProperty.class).param(StringConstants.ERROR_FIELD_NAME);

        JMethod method = baseClass.method(JMod.PUBLIC, typeManager.getOptionalForType(typeClass), StringUtil.generateFieldMethodName(StringConstants.ERROR_FIELD_NAME, "get"));
        method.javadoc().add(StringConstants.BASE_RESPONSE_ERROR_JAVADOC);
        method.body()._return(typeManager.getOptionalReturnForField(field));
    }

    private void generateRequestMethodHelpers(JDefinedClass targetClass) {
        AbstractJClass consumerResponseClass = typeManager.getConsumer(baseResponseClass);
        AbstractJClass consumerRequestClass = typeManager.getBiConsumer(baseRequestClass, consumerResponseClass);
        AbstractJClass supplierMessageIdClass = typeManager.getSupplier(typeManager.getPrimitiveType(StringConstants.STRING_TYPE));

        JFieldVar requestConsumer = targetClass.field(JMod.PRIVATE, consumerRequestClass, "requestConsumer");
        JFieldVar messageIdSupplier = targetClass.field(JMod.PRIVATE, supplierMessageIdClass, "messageIdSupplier");

        generateConstructor(targetClass, consumerRequestClass, supplierMessageIdClass, requestConsumer, messageIdSupplier);
        generateMethods(targetClass, consumerResponseClass, requestConsumer, messageIdSupplier);
    }

    private void generateConstructor(JDefinedClass targetClass, AbstractJClass consumerRequestClass, AbstractJClass supplierMessageIdClass, JFieldVar requestConsumer, JFieldVar messageIdSupplier) {
        JMethod constructor = targetClass.constructor(JMod.PUBLIC);
        JVar consumerRequests = constructor.param(consumerRequestClass, "requestConsumer");
        JVar supplierMessageId = constructor.param(supplierMessageIdClass, "messageIdSupplier");

        JBlock body = constructor.body();
        body.add(JExpr._this().ref(requestConsumer).assign(consumerRequests));
        body.add(JExpr._this().ref(messageIdSupplier).assign(supplierMessageId));
    }

    private void generateMethods(JDefinedClass targetClass, AbstractJClass consumerResponseClass, JFieldVar requestConsumer, JFieldVar messageIdSupplier) {
        JMethod getNewMessageIdMethod = targetClass.method(JMod.PROTECTED, typeManager.getPrimitiveType(StringConstants.STRING_TYPE), "getNewMessageId");
        getNewMessageIdMethod.body()._return(messageIdSupplier.invoke("get"));

        JMethod sendMessageMethod = targetClass.method(JMod.PROTECTED, typeManager.getVoidType(), "sendMessage");
        JVar request = sendMessageMethod.param(baseRequestClass, "request");
        JVar responseConsumer = sendMessageMethod.param(consumerResponseClass, "responseConsumer");
        sendMessageMethod.body().add(requestConsumer.invoke("accept").arg(request).arg(responseConsumer));
    }

    public JDefinedClass getRequestSenderClass() {
        return requestSenderClass;
    }

    public JDefinedClass getBaseBuilderClass() {
        return baseBuilderClass;
    }

    public JDefinedClass getBaseRequestClass() {
        return baseRequestClass;
    }

    public JDefinedClass getBaseResponseClass() {
        return baseResponseClass;
    }

    public JDefinedClass getRequestTypeClass() {
        return requestTypeEnum;
    }

    public JEnumConstant getEnumValue(String name) {
        return requestTypeEnum.enumConstant(StringUtil.generateEnumValue(name));
    }
}
