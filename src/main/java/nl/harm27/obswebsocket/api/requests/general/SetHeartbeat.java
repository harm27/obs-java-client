package nl.harm27.obswebsocket.api.requests.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Enable/disable sending of the Heartbeat event
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetHeartbeat">OBS WebSocket Documentation</a>
 * @since v4.3.0
 */
public class SetHeartbeat {
    private SetHeartbeat() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("enable")
        private final boolean enable;

        /**
         * @param enable Starts/Stops emitting heartbeat messages
         */
        public Request(String messageId, boolean enable) {
            super(RequestType.SET_HEARTBEAT, messageId);
            this.enable = enable;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SetHeartbeat.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}