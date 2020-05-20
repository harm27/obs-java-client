package nl.harm27.obswebsocket.websocket;

import nl.harm27.obswebsocket.processor.MessageReceiver;
import nl.harm27.obswebsocket.processor.MessageSender;

import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OBSWebSocketListener implements WebSocket.Listener {
    private final Logger logger = Logger.getLogger(OBSWebSocketListener.class.getName());
    private final OBSWebSocketClient obsWebSocketClient;
    private final MessageSender messageSender;
    private final MessageReceiver messageReceiver;

    public OBSWebSocketListener(OBSWebSocketClient obsWebSocketClient, MessageSender messageSender, MessageReceiver messageReceiver) {
        this.obsWebSocketClient = obsWebSocketClient;
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        obsWebSocketClient.setWebSocket(webSocket);
        messageSender.processQueuedMessages();
        messageSender.checkAuthenticationRequired();
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        webSocket.request(1);
        CompletableFuture.runAsync(() -> messageReceiver.receiveMessage(data.toString()));
        return null;
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        CompletableFuture.runAsync(obsWebSocketClient::notifyShutdown);
        return null;
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        logger.log(Level.SEVERE, "WebSocket error: ", error);
    }
}
