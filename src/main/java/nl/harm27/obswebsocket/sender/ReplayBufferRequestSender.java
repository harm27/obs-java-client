package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.replaybuffer.StartStopReplayBuffer;

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
}
