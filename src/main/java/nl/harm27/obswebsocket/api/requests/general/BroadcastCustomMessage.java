package nl.harm27.obswebsocket.api.requests.general;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Broadcast custom message to all connected WebSocket clients
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#BroadcastCustomMessage-1">OBS WebSocket Documentation</a>
 * @since v4.7.0
 */
public class BroadcastCustomMessage {
    private BroadcastCustomMessage() {
    }

    public static class Request extends BaseRequest {
        @SerializedName("realm")
        private final String realm;
        @SerializedName("data")
        private final Object data;

        /**
         * @param realm Identifier to be choosen by the client
         * @param data  User-defined data
         */
        public Request(String messageId, String realm, Object data) {
            super(RequestType.BROADCAST_CUSTOM_MESSAGE, messageId);
            this.realm = realm;
            this.data = data;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return BroadcastCustomMessage.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}