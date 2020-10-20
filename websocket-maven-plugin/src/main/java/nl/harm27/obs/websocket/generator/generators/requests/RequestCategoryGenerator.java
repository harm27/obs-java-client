package nl.harm27.obs.websocket.generator.generators.requests;

import com.helger.jcodemodel.*;
import nl.harm27.obs.websocket.generator.datamodel.requests.Request;
import nl.harm27.obs.websocket.generator.generators.generic.StringUtil;
import nl.harm27.obs.websocket.generator.generators.generic.TypeManager;
import nl.harm27.obs.websocket.generator.generators.generic.UnknownTypeException;

import java.util.ArrayList;
import java.util.List;

public class RequestCategoryGenerator extends GenericRequestsGenerator {
    private final JPackage senderPackageModel;
    private final JPackage requestCategoryPackageModel;
    private final RequestsBaseGenerator requestsBaseGenerator;
    private final String category;
    private final List<Request> requests;

    public RequestCategoryGenerator(JPackage senderPackageModel, JPackage requestsPackageModel, RequestsBaseGenerator requestsBaseGenerator, TypeManager typeManager, String category, List<Request> requests) {
        super(requestsBaseGenerator, typeManager);
        this.senderPackageModel = senderPackageModel;
        this.requestsBaseGenerator = requestsBaseGenerator;
        this.category = category;
        this.requests = requests;
        requestCategoryPackageModel = requestsPackageModel.subPackage(StringUtil.generateValidPackageName(category));
    }

    public void generate() throws JCodeModelException, UnknownTypeException {
        List<GeneratedRequest> generatedRequests = new ArrayList<>();
        for (Request request : requests) {
            RequestGenerator requestGenerator = new RequestGenerator(requestCategoryPackageModel, requestsBaseGenerator, typeManager, request);
            generatedRequests.add(requestGenerator.generate());
        }

        generateSender(generatedRequests);
    }

    private void generateSender(List<GeneratedRequest> generatedRequests) throws JCodeModelException {
        String className = String.format("%sRequestSender", StringUtil.generateValidClassName(category));
        JDefinedClass requestSenderClass = senderPackageModel._class(className)._extends(requestsBaseGenerator.getRequestSenderClass());

        generateConstructor(requestSenderClass);

        for (GeneratedRequest generatedRequest : generatedRequests) {
            generateRequestMethod(requestSenderClass, generatedRequest);
        }
    }

    private void generateRequestMethod(JDefinedClass requestSenderClass, GeneratedRequest generatedRequest) {
        JMethod requestMethod = requestSenderClass.method(JMod.PUBLIC, generatedRequest.getBuilderClass(), StringUtil.generateValidMethodName(generatedRequest.getName()));
        generateJavadocForClass(requestMethod.javadoc(), generatedRequest.getDescription(), generatedRequest.getName(), generatedRequest.getSince(), generatedRequest.getDeprecated());
        requestMethod.body()._return(generatedRequest.getBuilderClass()._new().arg(new JLambdaMethodRef(JExpr._this(), "sendMessage")).arg(new JLambdaMethodRef(JExpr._this(), "getNewMessageId")));
    }

}
