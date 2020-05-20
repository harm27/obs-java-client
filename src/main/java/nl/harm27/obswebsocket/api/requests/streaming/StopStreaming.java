package nl.harm27.obswebsocket.api.requests.streaming;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Stop streaming. Will return an error if streaming is not active.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StopStreaming">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class StopStreaming {
    private StopStreaming() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.STOP_STREAMING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return StopStreaming.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}