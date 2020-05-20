package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.authentication.AuthenticationHandler;
import nl.harm27.obswebsocket.authentication.AuthenticationResult;
import nl.harm27.obswebsocket.listener.EventListener;
import nl.harm27.obswebsocket.listener.ListenerRegistry;
import nl.harm27.obswebsocket.processor.MessageReceiver;
import nl.harm27.obswebsocket.processor.MessageSender;
import nl.harm27.obswebsocket.sender.*;
import nl.harm27.obswebsocket.websocket.OBSWebSocketClient;

import java.net.http.WebSocket;
import java.util.function.Consumer;

public class OBSWebSocket {
    private final MessageSender messageSender;
    private final OBSWebSocketClient obsWebSocketClient;
    private final AuthenticationHandler authenticationHandler;
    private final MessageReceiver messageReceiver;
    private final ListenerRegistry listenerRegistry;
    private final RequestSenderManager requestSenderManager;

    private int lastMessageId = 0;

    private OBSWebSocket(String ip, int port, Consumer<AuthenticationResult> authenticationResultConsumer, String password) {
        authenticationHandler = new AuthenticationHandler(this, password, authenticationResultConsumer);
        obsWebSocketClient = new OBSWebSocketClient(this, ip, port);
        messageSender = new MessageSender(obsWebSocketClient);
        requestSenderManager = new RequestSenderManager(this);
        listenerRegistry = new ListenerRegistry();
        messageReceiver = new MessageReceiver(listenerRegistry);
        obsWebSocketClient.connect();
    }

    public synchronized String getMessageId() {
        lastMessageId++;
        return String.valueOf(lastMessageId);
    }

    public void sendMessage(BaseRequest request, Consumer<BaseResponse> responseConsumer) {
        messageReceiver.addMessage(request.getMessageId(), request.getResponseType(), responseConsumer);
        messageSender.sendMessage(request);
    }

    public void registerListener(EventListener eventListener) {
        listenerRegistry.registerListener(eventListener);
    }

    public void notifyShutdown() {
        obsWebSocketClient.notifyShutdown();
    }

    public void receiveMessage(String message) {
        messageReceiver.receiveMessage(message);
    }

    public void connected(WebSocket webSocket) {
        obsWebSocketClient.setWebSocket(webSocket);
        messageSender.processQueuedMessages();
        authenticationHandler.checkAuthenticationRequired();
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationHandler.getAuthenticationResult();
    }

    public GeneralRequestSender getGeneralRequestSender() {
        return requestSenderManager.getGeneralRequestSender();
    }

    public ScenesRequestSender getScenesRequestSender() {
        return requestSenderManager.getScenesRequestSender();
    }

    public RecordingRequestSender getRecordingRequestSender() {
        return requestSenderManager.getRecordingRequestSender();
    }

    public ReplayBufferRequestSender getReplayBufferRequestSender() {
        return requestSenderManager.getReplayBufferRequestSender();
    }

    public StreamingRequestSender getStreamingRequestSender() {
        return requestSenderManager.getStreamingRequestSender();
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
