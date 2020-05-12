package nl.harm27.obswebsocket.api.requests.streaming;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.StreamSettings;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Start streaming. Will return an error if streaming is already active.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StartStreaming">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class StartStreaming {
    private StartStreaming() {
    }

    public static class Request extends BaseRequest {
        @SerializedName("stream")
        private Stream stream;

        public Request(String messageId) {
            super(RequestType.START_STREAMING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        public void setStream(Stream stream) {
            this.stream = stream;
        }
    }

    public static class Response extends BaseResponse {
    }

    public static class Stream {
        @SerializedName("type")
        private String type;
        @SerializedName("metadata")
        private Object metadata;
        @SerializedName("settings")
        private StreamSettings settings;

        public void setType(String type) {
            this.type = type;
        }

        public void setMetadata(Object metadata) {
            this.metadata = metadata;
        }

        public void setSettings(StreamSettings settings) {
            this.settings = settings;
        }
    }

}