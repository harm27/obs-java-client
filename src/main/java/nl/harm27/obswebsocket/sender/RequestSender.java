package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;

import java.util.function.Consumer;

public abstract class RequestSender {
    private final OBSWebSocket obsWebSocket;

    public RequestSender(OBSWebSocket obsWebSocket) {
        this.obsWebSocket = obsWebSocket;
    }

    protected String getNextMessageId() {
        return obsWebSocket.getMessageId();
    }

    protected void sendRequest(BaseRequest baseRequest, Consumer<BaseResponse> responseConsumer) {
        obsWebSocket.sendMessage(baseRequest, responseConsumer);
    }
}
