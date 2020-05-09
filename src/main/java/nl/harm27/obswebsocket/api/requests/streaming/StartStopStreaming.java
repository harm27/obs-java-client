package nl.harm27.obswebsocket.api.requests.streaming;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Toggle streaming on or off.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStopStreaming">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class StartStopStreaming {
    private StartStopStreaming() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.START_STOP_STREAMING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}