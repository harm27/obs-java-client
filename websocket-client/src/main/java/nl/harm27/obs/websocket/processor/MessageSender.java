package nl.harm27.obs.websocket.processor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import nl.harm27.obs.websocket.RequestSenderManager;
import nl.harm27.obs.websocket.api.base.BaseRequest;
import nl.harm27.obs.websocket.api.base.BaseResponse;
import nl.harm27.obs.websocket.api.requests.general.GetVersion;
import nl.harm27.obs.websocket.authentication.AuthenticationHandler;
import nl.harm27.obs.websocket.authentication.AuthenticationResult;
import nl.harm27.obs.websocket.websocket.OBSWebSocketClient;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private final OBSWebSocketClient obsWebSocketClient;
    private final List<BaseRequest> queuedMessages;
    private final ObjectMapper objectMapper;
    private final RequestSenderManager requestSenderManager;
    private final AuthenticationHandler authenticationHandler;
    private List<String> supportedRequests;

    public MessageSender(RequestSenderManager requestSenderManager, OBSWebSocketClient obsWebSocketClient, AuthenticationHandler authenticationHandler) {
        this.requestSenderManager = requestSenderManager;
        this.authenticationHandler = authenticationHandler;
        authenticationHandler.addAuthenticationResultConsumer(this::processQueuedMessages);
        queuedMessages = new ArrayList<>();
        this.obsWebSocketClient = obsWebSocketClient;
        objectMapper = new ObjectMapper().
                setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).
                setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                setSerializationInclusion(JsonInclude.Include.NON_EMPTY).
                disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public void onWebSocketOpen() {
        authenticationHandler.checkAuthenticationRequired();
        requestSenderManager.getGeneralRequestSender().getVersion().sendMessage(this::parseVersionAndMethods);
        processQueuedMessages();
    }

    private void processQueuedMessages(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful())
            processQueuedMessages();
    }

    private void processQueuedMessages() {
        for (BaseRequest request : queuedMessages) {
            if (authenticationHandler.getAuthenticationResult().isSuccessful() || !request.isAuthenticationRequired())
                sendMessage(request);
        }
    }

    private void parseVersionAndMethods(BaseResponse baseResponse) {
        if (!(baseResponse instanceof GetVersion.Response))
            return;

        var response = (GetVersion.Response) baseResponse;
        supportedRequests = response.getAvailableRequestsAsList();
    }

    public void sendMessage(BaseRequest request) {
        String requestName = request.getRequestName();
        if (supportedRequests != null && !supportedRequests.contains(requestName))
            throw new InvalidMethodException(requestName);
        else if (!obsWebSocketClient.isConnected() || (request.isAuthenticationRequired() && !authenticationHandler.getAuthenticationResult().isSuccessful()))
            queuedMessages.add(request);
        else {
            try {
                obsWebSocketClient.sendText(objectMapper.writeValueAsString(request));
            } catch (JsonProcessingException e) {
                throw new SendingException(e);
            }
        }
    }
}
