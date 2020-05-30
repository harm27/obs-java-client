package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.studiomode.GetStudioModeStatus;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the Studio Mode category.
 */
public class StudioModeRequestSender extends RequestSender {
    public StudioModeRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    public void getStudioModeStatus(Consumer<GetStudioModeStatus.Response> responseConsumer) {
        sendRequest(new GetStudioModeStatus.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetStudioModeStatus.Response) baseResponse));
    }
}
