package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.general.BroadcastCustomMessage;
import nl.harm27.obswebsocket.api.requests.general.GetVersion;
import nl.harm27.obswebsocket.api.requests.general.SetHeartbeat;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the General category.
 */
public class GeneralRequestSender extends RequestSender {
    public GeneralRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    public void getVersion(Consumer<GetVersion.Response> responseConsumer) {
        sendRequest(new GetVersion.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetVersion.Response) baseResponse));
    }

    public void setHeartbeat(boolean enable, Consumer<SetHeartbeat.Response> responseConsumer) {
        sendRequest(new SetHeartbeat.Request(getNextMessageId(), enable),
                baseResponse -> responseConsumer.accept((SetHeartbeat.Response) baseResponse));
    }

    public void broadcastCustomMessage(String realm, Object data, Consumer<BroadcastCustomMessage.Response> responseConsumer) {
        sendRequest(new BroadcastCustomMessage.Request(getNextMessageId(), realm, data),
                baseResponse -> responseConsumer.accept((BroadcastCustomMessage.Response) baseResponse));
    }
}
