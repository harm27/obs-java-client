package nl.harm27.obswebsocket.api.requests.recording;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Start recording. Will return an error if recording is already active.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartRecording">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class StartRecording {
    private StartRecording() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.START_RECORDING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}