package nl.harm27.obswebsocket.api.requests.recording;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Resume/unpause the current recording (if paused). Returns an error if recording is not active or not paused.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#ResumeRecording">OBS WebSocket Documentation</a>
 * @since v4.7.0
 */
public class ResumeRecording {
    private ResumeRecording() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.RESUME_RECORDING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}