package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.complex.StreamSettings;
import nl.harm27.obswebsocket.api.requests.streaming.*;

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

    /**
     * Stop streaming. Will return an error if streaming is not active.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StopStreaming">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void stopStreaming(Consumer<StopStreaming.Response> responseConsumer) {
        sendRequest(new StopStreaming.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StopStreaming.Response) baseResponse));
    }

    /**
     * Sets one or more attributes of the current streaming server settings.
     * Any options not passed will remain unchanged.
     * Returns the updated settings in response.
     * If 'type' is different than the current streaming service type, all settings are required.
     * Returns the full settings of the stream (the same as GetStreamSettings).
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetStreamSettings">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void setStreamSettings(String type, StreamSettings settings, boolean save, Consumer<SetStreamSettings.Response> responseConsumer) {
        sendRequest(new SetStreamSettings.Request(getNextMessageId(), type, settings, save),
                baseResponse -> responseConsumer.accept((SetStreamSettings.Response) baseResponse));
    }

    /**
     * Get the current streaming server settings.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStreamSettings">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void getStreamSettings(Consumer<GetStreamSettings.Response> responseConsumer) {
        sendRequest(new GetStreamSettings.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetStreamSettings.Response) baseResponse));
    }

    /**
     * Save the current streaming server settings to disk.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SaveStreamSettings">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void saveStreamSettings(Consumer<SaveStreamSettings.Response> responseConsumer) {
        sendRequest(new SaveStreamSettings.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((SaveStreamSettings.Response) baseResponse));
    }

    /**
     * Send the provided text as embedded CEA-608 caption data.
     * As of OBS Studio 23.1, captions are not yet available on Linux.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SendCaptions">OBS WebSocket Documentation</a>
     * @since v4.6.0
     */
    public void sendCaptions(String text, Consumer<SendCaptions.Response> responseConsumer) {
        sendRequest(new SendCaptions.Request(getNextMessageId(), text),
                baseResponse -> responseConsumer.accept((SendCaptions.Response) baseResponse));
    }
}