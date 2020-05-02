package nl.harm27.obswebsocket.api.requests.recording;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Pause the current recording. Returns an error if recording is not active or already paused.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#PauseRecording">OBS WebSocket Documentation</a>
 * @since v4.7.0
 */
public class PauseRecording {
    private PauseRecording() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.PAUSE_RECORDING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}