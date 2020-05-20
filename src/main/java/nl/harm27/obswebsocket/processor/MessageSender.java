package nl.harm27.obswebsocket.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private final Gson gson;
    private final OBSWebSocket obsWebSocket;
    private final AuthenticationHandler authenticationHandler;

    public MessageSender(OBSWebSocket obsWebSocket, OBSWebSocketClient obsWebSocketClient, AuthenticationHandler authenticationHandler) {
        this.obsWebSocket = obsWebSocket;
        this.authenticationHandler = authenticationHandler;
        authenticationHandler.addAuthenticationResultConsumer(this::processQueuedMessages);
        queuedMessages = new ArrayList<>();
        this.obsWebSocketClient = obsWebSocketClient;
        gson = new GsonBuilder().create();
    }

    public void checkAuthenticationRequired() {
        authenticationHandler.checkAuthenticationRequired();
    }

    private void processQueuedMessages(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful())
            processQueuedMessages();
    }

    public void processQueuedMessages() {
        for (BaseRequest request : queuedMessages) {
            if (authenticationHandler.getAuthenticationResult().isSuccessful() || !request.isAuthenticationRequired())
                sendMessage(request);
        }
    }

    public void sendMessage(BaseRequest request) {
        if (!obsWebSocketClient.isConnected() || (request.isAuthenticationRequired() && !authenticationHandler.getAuthenticationResult().isSuccessful()))
            queuedMessages.add(request);
        else
            obsWebSocketClient.sendText(gson.toJson(request));
    }
}
