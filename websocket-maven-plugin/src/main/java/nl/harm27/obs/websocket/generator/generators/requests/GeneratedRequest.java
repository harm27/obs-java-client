package nl.harm27.obs.websocket.generator.generators.requests;

import com.helger.jcodemodel.JDefinedClass;
import nl.harm27.obs.websocket.generator.datamodel.requests.Request;

public class GeneratedRequest {
    private final Request request;
    private final JDefinedClass builderClass;
    private final JDefinedClass requestClass;
    private final JDefinedClass responseClass;

    public GeneratedRequest(Request request, JDefinedClass builderClass, JDefinedClass requestClass, JDefinedClass responseClass) {
        this.request = request;
        this.builderClass = builderClass;
        this.requestClass = requestClass;
        this.responseClass = responseClass;
    }

    public Request getRequest() {
        return request;
    }

    public JDefinedClass getBuilderClass() {
        return builderClass;
    }

    public JDefinedClass getRequestClass() {
        return requestClass;
    }

    public JDefinedClass getResponseClass() {
        return responseClass;
    }

    public String getName() {
        return request.getName();
    }

    public String getDescription() {
        return request.getDescription();
    }

    public String getSince() {
        return request.getSince();
    }

    public String getDeprecated() {
        return request.getDeprecated();
    }
}
