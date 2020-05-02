package nl.harm27.obswebsocket.api.requests.recording;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Toggle recording on or off.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStopRecording">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class StartStopRecording {
    private StartStopRecording() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.START_STOP_RECORDING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}