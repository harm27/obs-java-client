package nl.harm27.obswebsocket.api.requests.streaming;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Send the provided text as embedded CEA-608 caption data.
 * As of OBS Studio 23.1, captions are not yet available on Linux.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SendCaptions">OBS WebSocket Documentation</a>
 * @since v4.6.0
 */
public class SendCaptions {
    private SendCaptions() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("text")
        private final String text;

        public Request(String messageId, String text) {
            super(RequestType.SEND_CAPTIONS, messageId);
            this.text = text;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SendCaptions.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}