package nl.harm27.obswebsocket.api.requests.streaming;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.complex.StreamSettings;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Get the current streaming server settings.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStreamSettings">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class GetStreamSettings {
    private GetStreamSettings() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_STREAM_SETTINGS, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetStreamSettings.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("type")
        private String type;
        @JsonProperty("settings")
        private StreamSettings settings;

        /**
         * The type of streaming service configuration, usually rtmp_custom or rtmp_common.
         */
        public String getType() {
            return type;
        }

        /**
         * The actual settings of the stream.
         */
        public StreamSettings getSettings() {
            return settings;
        }
    }
}