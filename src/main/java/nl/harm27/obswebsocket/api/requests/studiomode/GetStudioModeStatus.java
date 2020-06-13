package nl.harm27.obswebsocket.api.requests.studiomode;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Indicates if Studio Mode is currently enabled.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStudioModeStatus">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class GetStudioModeStatus {
    private GetStudioModeStatus() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_STUDIO_MODE_STATUS, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetStudioModeStatus.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("studio-mode")
        private boolean studioMode;

        /**
         * Indicates if Studio Mode is enabled.
         */
        public boolean isStudioMode() {
            return studioMode;
        }
    }
}