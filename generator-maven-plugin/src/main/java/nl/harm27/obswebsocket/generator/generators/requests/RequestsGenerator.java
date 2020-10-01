package nl.harm27.obswebsocket.generator.generators.requests;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JPackage;
import nl.harm27.obswebsocket.generator.datamodel.requests.Request;
import nl.harm27.obswebsocket.generator.datamodel.requests.RequestsDefinition;
import nl.harm27.obswebsocket.generator.generators.generic.TypeManager;
import nl.harm27.obswebsocket.generator.generators.generic.UnknownTypeException;

import java.util.List;
import java.util.Map;

public class RequestsGenerator {
    private final JPackage senderPackageModel;
    private final JPackage requestsPackageModel;
    private final JPackage basePackageModel;
    private final TypeManager typeManager;

    public RequestsGenerator(JCodeModel codeModel, TypeManager typeManager, String senderPackageName, String apiPackageName) {
        this.typeManager = typeManager;
        senderPackageModel = codeModel._package(senderPackageName);
        requestsPackageModel = codeModel._package(String.format("%s.requests", apiPackageName));
        basePackageModel = codeModel._package(String.format("%s.base", apiPackageName));
    }

    public void generate(RequestsDefinition requestsDefinition) throws JCodeModelException, UnknownTypeException {
        RequestsBaseGenerator requestsBaseGenerator = new RequestsBaseGenerator(basePackageModel, senderPackageModel, typeManager, requestsDefinition.getRequestNames());
        requestsBaseGenerator.generate();

        for (Map.Entry<String, List<Request>> requestCategory : requestsDefinition.getRequestsMap().entrySet()) {
            RequestCategoryGenerator requestCategoryGenerator = new RequestCategoryGenerator(senderPackageModel, requestsPackageModel, requestsBaseGenerator, typeManager, requestCategory.getKey(), requestCategory.getValue());
            requestCategoryGenerator.generate();
        }
    }

}
