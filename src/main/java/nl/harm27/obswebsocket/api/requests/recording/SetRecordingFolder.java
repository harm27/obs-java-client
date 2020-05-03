package nl.harm27.obswebsocket.api.requests.recording;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Please note: if SetRecordingFolder is called while a recording is in progress, the change won't be applied immediately and will be effective on the next recording.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetRecordingFolder">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class SetRecordingFolder {
    private SetRecordingFolder() {
    }

    public static class Request extends BaseRequest {
        @SerializedName("rec-folder")
        private final String recFolder;

        /**
         * @param recFolder Path of the recording folder.
         */
        public Request(String messageId, String recFolder) {
            super(RequestType.SET_RECORDING_FOLDER, messageId);
            this.recFolder = recFolder;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }
    }

    public static class Response extends BaseResponse {
    }
}