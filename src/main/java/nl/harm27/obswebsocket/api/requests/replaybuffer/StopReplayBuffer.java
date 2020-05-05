package nl.harm27.obswebsocket.api.requests.replaybuffer;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Stop recording into the Replay Buffer. Will return an error if the Replay Buffer is not active.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StopReplayBuffer">OBS WebSocket Documentation</a>
 * @since v4.2.0
 */
public class StopReplayBuffer {
    private StopReplayBuffer() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.STOP_REPLAY_BUFFER, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}