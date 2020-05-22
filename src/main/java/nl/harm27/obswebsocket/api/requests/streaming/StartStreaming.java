package nl.harm27.obswebsocket.api.requests.streaming;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("stream")
        private Stream stream;

        public Request(String messageId) {
            super(RequestType.START_STREAMING, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return StartStreaming.class.getSimpleName();
        }

        public void setStream(Stream stream) {
            this.stream = stream;
        }
    }

    public static class Response extends BaseResponse {
    }

    /**
     * Special stream configuration. Please note: these won't be saved to OBS' configuration.
     */
    public static class Stream {
        @JsonProperty("type")
        private String type;
        @JsonProperty("metadata")
        private Object metadata;
        @JsonProperty("settings")
        private StreamSettings settings;

        /**
         * If specified ensures the type of stream matches the given type (usually 'rtmp_custom' or 'rtmp_common').
         * If the currently configured stream type does not match the given stream type,
         * all settings must be specified in the settings object or an error will occur when starting the stream.
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Adds the given object parameters as encoded query string parameters to the 'key' of the RTMP stream.
         * Used to pass data to the RTMP service about the streaming.
         * May be any String, Numeric, or Boolean field.
         */
        public void setMetadata(Object metadata) {
            this.metadata = metadata;
        }

        /**
         * Settings for the stream.
         */
        public void setSettings(StreamSettings settings) {
            this.settings = settings;
        }
    }
}