package nl.harm27.obswebsocket.api.requests.streaming;

import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Save the current streaming server settings to disk.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SaveStreamSettings">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class SaveStreamSettings {
    private SaveStreamSettings() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.SAVE_STREAM_SETTINGS, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}