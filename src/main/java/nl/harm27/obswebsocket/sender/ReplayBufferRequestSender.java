package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.replaybuffer.SaveReplayBuffer;
import nl.harm27.obswebsocket.api.requests.replaybuffer.StartReplayBuffer;
import nl.harm27.obswebsocket.api.requests.replaybuffer.StartStopReplayBuffer;
import nl.harm27.obswebsocket.api.requests.replaybuffer.StopReplayBuffer;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the Replay Buffer category.
 */
public class ReplayBufferRequestSender extends RequestSender {
    public ReplayBufferRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    /**
     * Toggle the Replay Buffer on/off.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStopReplayBuffer">OBS WebSocket Documentation</a>
     * @since v4.2.0
     */
    public void startStopReplayBuffer(Consumer<StartStopReplayBuffer.Response> responseConsumer) {
        sendRequest(new StartStopReplayBuffer.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StartStopReplayBuffer.Response) baseResponse));
    }

    /**
     * Start recording into the Replay Buffer.
     * Will return an error if the Replay Buffer is already active or if the "Save Replay Buffer" hotkey is not set in OBS' settings.
     * Setting this hotkey is mandatory, even when triggering saves only through obs-websocket.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartReplayBuffer">OBS WebSocket Documentation</a>
     * @since v4.2.0
     */
    public void startReplayBuffer(Consumer<StartReplayBuffer.Response> responseConsumer) {
        sendRequest(new StartReplayBuffer.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StartReplayBuffer.Response) baseResponse));
    }

    /**
     * Stop recording into the Replay Buffer. Will return an error if the Replay Buffer is not active.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StopReplayBuffer">OBS WebSocket Documentation</a>
     * @since v4.2.0
     */
    public void stopReplayBuffer(Consumer<StopReplayBuffer.Response> responseConsumer) {
        sendRequest(new StopReplayBuffer.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((StopReplayBuffer.Response) baseResponse));
    }

    /**
     * Flush and save the contents of the Replay Buffer to disk.
     * This is basically the same as triggering the "Save Replay Buffer" hotkey.
     * Will return an error if the Replay Buffer is not active.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SaveReplayBuffer">OBS WebSocket Documentation</a>
     * @since v4.2.0
     */
    public void saveReplayBuffer(Consumer<SaveReplayBuffer.Response> responseConsumer) {
        sendRequest(new SaveReplayBuffer.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((SaveReplayBuffer.Response) baseResponse));
    }
}
