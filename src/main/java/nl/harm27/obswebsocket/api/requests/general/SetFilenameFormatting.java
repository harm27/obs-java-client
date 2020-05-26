package nl.harm27.obswebsocket.api.requests.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Set the filename formatting string
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetFilenameFormatting">OBS WebSocket Documentation</a>
 * @since v4.3.0
 */
public class SetFilenameFormatting {
    private SetFilenameFormatting() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("filename-formatting")
        private final String filenameFormatting;

        /**
         * @param filenameFormatting Filename formatting string to set.
         */
        public Request(String messageId, String filenameFormatting) {
            super(RequestType.SET_FILENAME_FORMATTING, messageId);
            this.filenameFormatting = filenameFormatting;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SetFilenameFormatting.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}