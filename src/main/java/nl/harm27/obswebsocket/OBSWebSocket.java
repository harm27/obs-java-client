package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.authentication.AuthenticationHandler;
import nl.harm27.obswebsocket.authentication.AuthenticationResult;
import nl.harm27.obswebsocket.listener.EventListener;
import nl.harm27.obswebsocket.listener.ListenerRegistry;
import nl.harm27.obswebsocket.processor.MessageReceiver;
import nl.harm27.obswebsocket.processor.MessageSender;
import nl.harm27.obswebsocket.sender.GeneralRequestSender;
import nl.harm27.obswebsocket.sender.RequestSenderManager;
import nl.harm27.obswebsocket.websocket.OBSWebSocketClient;

import java.net.http.WebSocket;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class OBSWebSocket {
    public static final Pattern NUMERIC_FORMAT_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final MessageSender messageSender;
    private final OBSWebSocketClient obsWebSocketClient;
    private final AuthenticationHandler authenticationHandler;
    private final MessageReceiver messageReceiver;
    private final ListenerRegistry listenerRegistry;
    private final RequestSenderManager requestSenderManager;

    private int lastMessageId = 0;

    public OBSWebSocket(String ip, int port) {
        this(ip, port, null);
    }

    public OBSWebSocket(String ip, int port, String password) {
        authenticationHandler = new AuthenticationHandler(this, password);
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
}
