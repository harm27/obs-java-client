package nl.harm27.obswebsocket.processor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.general.GetVersion;
import nl.harm27.obswebsocket.authentication.AuthenticationHandler;
import nl.harm27.obswebsocket.authentication.AuthenticationResult;
import nl.harm27.obswebsocket.websocket.OBSWebSocketClient;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private final OBSWebSocketClient obsWebSocketClient;
    private final List<BaseRequest> queuedMessages;
    private final ObjectMapper objectMapper;
    private final OBSWebSocket obsWebSocket;
    private final AuthenticationHandler authenticationHandler;
    private List<String> supportedRequests;

    public MessageSender(OBSWebSocket obsWebSocket, OBSWebSocketClient obsWebSocketClient, AuthenticationHandler authenticationHandler) {
        this.obsWebSocket = obsWebSocket;
        this.authenticationHandler = authenticationHandler;
        authenticationHandler.addAuthenticationResultConsumer(this::processQueuedMessages);
        queuedMessages = new ArrayList<>();
        this.obsWebSocketClient = obsWebSocketClient;
        objectMapper = new ObjectMapper().
                setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).
                setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void onWebSocketOpen() {
        authenticationHandler.checkAuthenticationRequired();
        obsWebSocket.sendMessage(new GetVersion.Request(obsWebSocket.getMessageId()), this::parseVersionAndMethods);
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

        GetVersion.Response response = (GetVersion.Response) baseResponse;
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
