package nl.harm27.obswebsocket.websocket;

import nl.harm27.obswebsocket.OBSWebSocket;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;

public class OBSWebSocketClient {
    private final OBSWebSocket obsWebSocket;
    private final String ip;
    private final int port;
    private WebSocket webSocket;

    public OBSWebSocketClient(OBSWebSocket obsWebSocket, String ip, int port) {
        this.obsWebSocket = obsWebSocket;
        this.ip = ip;
        this.port = port;
    }

    public void connect() {
        HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build().newWebSocketBuilder()
                .buildAsync(URI.create(String.format("ws://%s:%d", ip, port)), new OBSWebSocketListener(obsWebSocket));
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public void notifyShutdown() {
        webSocket.abort();
        webSocket = null;
    }

    public void sendText(String message) {
        webSocket.sendText(message, true);
    }

    public boolean isConnected() {
        return webSocket != null;
    }
}
