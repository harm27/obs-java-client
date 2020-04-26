package nl.harm27.obswebsocket.api.requests.general;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

import java.util.Optional;

/**
 * Tells the client if authentication is required. If so, returns authentication parameters challenge and salt (see "Authentication" for more information).
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#getauthrequired">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class GetAuthRequired {
    private GetAuthRequired() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_AUTH_REQUIRED, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("authRequired")
        private boolean authRequired;
        @SerializedName("challenge")
        private String challenge;
        @SerializedName("salt")
        private String salt;

        /**
         * Indicates whether authentication is required.
         */
        public boolean isAuthRequired() {
            return authRequired;
        }

        /**
         * The required challenge to be used if authentication is required.
         */
        public Optional<String> getChallenge() {
            return Optional.ofNullable(challenge);
        }

        /**
         * The required salt to be used if authentication is required.
         */
        public Optional<String> getSalt() {
            return Optional.ofNullable(salt);
        }
    }
}