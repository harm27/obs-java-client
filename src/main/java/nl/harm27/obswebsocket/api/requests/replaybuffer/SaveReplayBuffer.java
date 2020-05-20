package nl.harm27.obswebsocket.api.requests.replaybuffer;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Flush and save the contents of the Replay Buffer to disk.
 * This is basically the same as triggering the "Save Replay Buffer" hotkey.
 * Will return an error if the Replay Buffer is not active.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SaveReplayBuffer">OBS WebSocket Documentation</a>
 * @since v4.2.0
 */
public class SaveReplayBuffer {
    private SaveReplayBuffer() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.SAVE_REPLAY_BUFFER, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SaveReplayBuffer.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}