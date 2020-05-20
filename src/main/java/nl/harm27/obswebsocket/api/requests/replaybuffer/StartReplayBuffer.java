package nl.harm27.obswebsocket.api.requests.replaybuffer;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Start recording into the Replay Buffer.
 * Will return an error if the Replay Buffer is already active or if the "Save Replay Buffer" hotkey is not set in OBS' settings.
 * Setting this hotkey is mandatory, even when triggering saves only through obs-websocket.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartReplayBuffer">OBS WebSocket Documentation</a>
 * @since v4.2.0
 */
public class StartReplayBuffer {
    private StartReplayBuffer() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.START_REPLAY_BUFFER, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return StartReplayBuffer.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}