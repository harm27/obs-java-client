package nl.harm27.obswebsocket.api.requests.replaybuffer;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Toggle the Replay Buffer on/off.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStopReplayBuffer">OBS WebSocket Documentation</a>
 * @since v4.2.0
 */
public class StartStopReplayBuffer {
    private StartStopReplayBuffer() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.START_STOP_REPLAY_BUFFER, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return StartStopReplayBuffer.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}