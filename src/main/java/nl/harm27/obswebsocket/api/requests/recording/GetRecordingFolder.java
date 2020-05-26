package nl.harm27.obswebsocket.api.requests.recording;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Get the path of the current recording folder.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetRecordingFolder">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class GetRecordingFolder {
    private GetRecordingFolder() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_RECORDING_FOLDER, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetRecordingFolder.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("rec-folder")
        private String recFolder;

        /**
         * Path of the recording folder.
         */
        public String getRecFolder() {
            return recFolder;
        }
    }
}