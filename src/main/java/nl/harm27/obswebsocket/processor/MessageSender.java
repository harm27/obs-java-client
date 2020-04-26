package nl.harm27.obswebsocket.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.websocket.OBSWebSocketClient;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private final OBSWebSocketClient obsWebSocketClient;
    private final List<BaseRequest> queuedMessages;
    private final Gson gson;

    public MessageSender(OBSWebSocketClient obsWebSocketClient) {
        queuedMessages = new ArrayList<>();
        this.obsWebSocketClient = obsWebSocketClient;
        gson = new GsonBuilder().create();
    }

    public void processQueuedMessages() {
        for (BaseRequest request : queuedMessages) {
            sendMessage(request);
        }
    }

    public void sendMessage(BaseRequest request) {
        if (!obsWebSocketClient.isConnected())
            queuedMessages.add(request);
        else
            obsWebSocketClient.sendText(gson.toJson(request));
    }
}
