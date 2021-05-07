package nl.harm27.obs.websocket.generator.generators.requests;

import com.helger.jcodemodel.*;
import nl.harm27.obs.websocket.generator.generators.generic.GenericClassGenerator;
import nl.harm27.obs.websocket.generator.generators.generic.TypeManager;

public class GenericRequestsGenerator extends GenericClassGenerator {
    private final RequestsBaseGenerator requestsBaseGenerator;

    protected GenericRequestsGenerator(RequestsBaseGenerator requestsBaseGenerator, TypeManager typeManager) {
        super(typeManager);
        this.requestsBaseGenerator = requestsBaseGenerator;
    }

    protected void generateConstructor(JDefinedClass targetClass) {
        AbstractJClass consumerResponseClass = typeManager.getConsumer(requestsBaseGenerator.getBaseResponseClass());
        AbstractJClass consumerRequestClass = typeManager.getBiConsumer(requestsBaseGenerator.getBaseRequestClass(), consumerResponseClass);
        AbstractJClass supplierMessageIdClass = typeManager.getSupplier(typeManager.getPrimitiveType("string"));

        JMethod constructor = targetClass.constructor(JMod.PUBLIC);
        JVar consumerRequests = constructor.param(consumerRequestClass, "requestConsumer");
        JVar consumerBatches = constructor.param(consumerRequestClass, "batchConsumer");
        JVar supplierMessageId = constructor.param(supplierMessageIdClass, "messageIdSupplier");

        JBlock body = constructor.body();
        body.add(JExpr.invokeSuper().arg(consumerRequests).arg(consumerBatches).arg(supplierMessageId));
    }
}
