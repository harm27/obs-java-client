package nl.harm27.obswebsocket.api.requests.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Attempt to authenticate the client to the server.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#authenticate">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class Authenticate {
    private Authenticate() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("auth")
        private final String auth;

        /**
         * @param auth Response to the auth challenge (see "Authentication" for more information).
         */
        public Request(String messageId, String auth) {
            super(RequestType.AUTHENTICATE, messageId);
            this.auth = auth;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public boolean isAuthenticationRequired() {
            return false;
        }

        @Override
        public String getRequestName() {
            return Authenticate.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}