package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.streaming.GetStreamingStatus;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the Streaming category.
 */
public class StreamingRequestSender extends RequestSender {
    public StreamingRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    /**
     * Get current streaming and recording status.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStreamingStatus">OBS WebSocket Documentation</a>
     * @since v0.3
     */
    public void getStreamingStatus(Consumer<GetStreamingStatus.Response> responseConsumer) {
        sendRequest(new GetStreamingStatus.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetStreamingStatus.Response) baseResponse));
    }
}
