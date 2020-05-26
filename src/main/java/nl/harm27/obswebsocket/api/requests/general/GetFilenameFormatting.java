package nl.harm27.obswebsocket.api.requests.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Get the filename formatting string
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetFilenameFormatting">OBS WebSocket Documentation</a>
 * @since v4.3.0
 */
public class GetFilenameFormatting {
    private GetFilenameFormatting() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_FILENAME_FORMATTING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetFilenameFormatting.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("filename-formatting")
        private String filenameFormatting;

        /**
         * Current filename formatting string.
         */
        public String getFilenameFormatting() {
            return filenameFormatting;
        }
    }
}