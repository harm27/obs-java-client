package nl.harm27.obswebsocket.api.requests.streaming;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.StreamSettings;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Sets one or more attributes of the current streaming server settings.
 * Any options not passed will remain unchanged.
 * Returns the updated settings in response.
 * If 'type' is different than the current streaming service type, all settings are required.
 * Returns the full settings of the stream (the same as GetStreamSettings).
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetStreamSettings">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class SetStreamSettings {
    private SetStreamSettings() {
    }

    public static class Request extends BaseRequest {
        @SerializedName("type")
        private final String type;
        @SerializedName("settings")
        private final StreamSettings settings;
        @SerializedName("save")
        private final boolean save;

        public Request(String messageId, String type, StreamSettings settings, boolean save) {
            super(RequestType.SET_STREAM_SETTINGS, messageId);
            this.type = type;
            this.settings = settings;
            this.save = save;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SetStreamSettings.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("type")
        private String type;
        @SerializedName("settings")
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