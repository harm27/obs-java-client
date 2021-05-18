package nl.harm27.obs.websocket;

import nl.harm27.obs.websocket.authentication.AuthenticationHandler;
import nl.harm27.obs.websocket.authentication.AuthenticationResult;
import nl.harm27.obs.websocket.listener.EventListener;
import nl.harm27.obs.websocket.websocket.OBSWebSocketClient;

import java.util.function.Consumer;

public class OBSWebSocket {
    private final OBSWebSocketClient obsWebSocketClient;
    private final AuthenticationHandler authenticationHandler;
    private final ListenerRegistry listenerRegistry;
    private final RequestSenderManager requestSenderManager;

    private OBSWebSocket(String ip, int port, Consumer<AuthenticationResult> authenticationResultConsumer, String password) {
        authenticationHandler = new AuthenticationHandler(this, password);
        authenticationHandler.addAuthenticationResultConsumer(authenticationResultConsumer);
        obsWebSocketClient = new OBSWebSocketClient(ip, port);
        listenerRegistry = new ListenerRegistry();
        requestSenderManager = new RequestSenderManager(obsWebSocketClient, authenticationHandler, listenerRegistry);
        authenticationHandler.setGeneralRequestSender(requestSenderManager.getGeneralRequestSender());
    }

    public void registerListener(EventListener eventListener) {
        listenerRegistry.registerListener(eventListener);
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationHandler.getAuthenticationResult();
    }

    public RequestSenderManager getRequestSenderManager() {
        return requestSenderManager;
    }

    public void notifyShutdown() {
        obsWebSocketClient.notifyShutdown();
    }

    public static class Builder {
        private String ip;
        private int port;
        private String password;
        private Consumer<AuthenticationResult> authenticationResultConsumer;

        public OBSWebSocket build() {
            return new OBSWebSocket(ip, port, authenticationResultConsumer, password);
        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setAuthenticationResultConsumer(Consumer<AuthenticationResult> authenticationResultConsumer) {
            this.authenticationResultConsumer = authenticationResultConsumer;
            return this;
        }
    }
}
