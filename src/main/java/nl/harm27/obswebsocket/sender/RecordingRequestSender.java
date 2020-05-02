package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.recording.*;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the Recording category.
 */
public class RecordingRequestSender extends RequestSender {
    public RecordingRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    /**
     * Toggle recording on or off.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStopRecording">OBS WebSocket Documentation</a>
     * @since v0.3
     */
    public void startStopRecording(Consumer<StartStopRecording.Response> responseConsumer) {
        sendRequest(new StartStopRecording.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StartStopRecording.Response) baseResponse));
    }

    /**
     * Start recording. Will return an error if recording is already active.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartRecording">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void startRecording(Consumer<StartRecording.Response> responseConsumer) {
        sendRequest(new StartRecording.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StartRecording.Response) baseResponse));
    }

    /**
     * Stop recording. Will return an error if recording is not active.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StopRecording">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void stopRecording(Consumer<StopRecording.Response> responseConsumer) {
        sendRequest(new StopRecording.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StopRecording.Response) baseResponse));
    }

    /**
     * Pause the current recording. Returns an error if recording is not active or already paused.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#PauseRecording">OBS WebSocket Documentation</a>
     * @since v4.7.0
     */
    public void pauseRecording(Consumer<PauseRecording.Response> responseConsumer) {
        sendRequest(new PauseRecording.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((PauseRecording.Response) baseResponse));
    }

    /**
     * Resume/unpause the current recording (if paused). Returns an error if recording is not active or not paused.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#ResumeRecording">OBS WebSocket Documentation</a>
     * @since v4.7.0
     */
    public void resumeRecording(Consumer<ResumeRecording.Response> responseConsumer) {
        sendRequest(new ResumeRecording.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((ResumeRecording.Response) baseResponse));
    }
}
