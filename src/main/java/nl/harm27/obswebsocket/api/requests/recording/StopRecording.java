package nl.harm27.obswebsocket.api.requests.recording;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Stop recording. Will return an error if recording is not active.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StopRecording">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class StopRecording {
    private StopRecording() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.STOP_RECORDING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}