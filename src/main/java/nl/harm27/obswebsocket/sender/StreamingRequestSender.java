package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.streaming.GetStreamingStatus;
import nl.harm27.obswebsocket.api.requests.streaming.StartStopStreaming;
import nl.harm27.obswebsocket.api.requests.streaming.StartStreaming;

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

    /**
     * Toggle streaming on or off.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStopStreaming">OBS WebSocket Documentation</a>
     * @since v0.3
     */
    public void startStopStreaming(Consumer<StartStopStreaming.Response> responseConsumer) {
        sendRequest(new StartStopStreaming.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StartStopStreaming.Response) baseResponse));
    }

    /**
     * Start streaming. Will return an error if streaming is already active.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStreaming">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void startStreaming(StartStreaming.Stream stream, Consumer<StartStreaming.Response> responseConsumer) {
        StartStreaming.Request request = new StartStreaming.Request(getNextMessageId());
        if (stream != null)
            request.setStream(stream);

        sendRequest(request,
                baseResponse -> responseConsumer.accept((StartStreaming.Response) baseResponse));
    }
}
