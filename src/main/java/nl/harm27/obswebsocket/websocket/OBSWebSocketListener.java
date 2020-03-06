package nl.harm27.obswebsocket.websocket;

import nl.harm27.obswebsocket.OBSWebSocket;

import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OBSWebSocketListener implements WebSocket.Listener {
    private Logger logger = Logger.getLogger(OBSWebSocketListener.class.getName());
    private OBSWebSocket obsWebSocket;

    public OBSWebSocketListener(OBSWebSocket obsWebSocket) {
        this.obsWebSocket = obsWebSocket;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        obsWebSocket.connected(webSocket);
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        webSocket.request(1);
        CompletableFuture.runAsync(() -> obsWebSocket.receiveMessage(data.toString()));
        return null;
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        CompletableFuture.runAsync(obsWebSocket::notifyShutdown);
        return null;
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        logger.log(Level.SEVERE, "WebSocket error: ", error);
    }
}
