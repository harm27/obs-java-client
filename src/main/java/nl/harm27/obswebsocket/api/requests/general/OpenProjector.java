package nl.harm27.obswebsocket.api.requests.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Open a projector window or create a projector on a monitor. Requires OBS v24.0.4 or newer.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#OpenProjector">OBS WebSocket Documentation</a>
 * @since v4.8.0
 */
public class OpenProjector {
    private OpenProjector() {
    }

    public enum Type {
        @JsonProperty("Preview")
        PREVIEW,
        @JsonProperty("Source")
        SOURCE,
        @JsonProperty("Scene")
        SCENE,
        @JsonProperty("StudioProgram")
        STUDIO_PROGRAM,
        @JsonProperty("Multiview")
        MULTIVIEW
    }

    public static class Request extends BaseRequest {
        @JsonProperty("type")
        private Type type;
        @JsonProperty("monitor")
        private int monitor;
        @JsonProperty("geometry")
        private String geometry;
        @JsonProperty("name")
        private String name;

        public Request(String messageId) {
            super(RequestType.OPEN_PROJECTOR, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return OpenProjector.class.getSimpleName();
        }

        /**
         * Type of projector
         */
        public void setType(Type type) {
            this.type = type;
        }

        /**
         * Monitor to open the projector on. If -1 or omitted, opens a window.
         */
        public void setMonitor(int monitor) {
            this.monitor = monitor;
        }

        /**
         * Size and position of the projector window (only if monitor is -1).
         * Encoded in Base64 using Qt's geometry encoding (https://doc.qt.io/qt-5/qwidget.html#saveGeometry).
         * Corresponds to OBS's saved projectors.
         */
        public void setGeometry(String geometry) {
            this.geometry = geometry;
        }

        /**
         * Name of the source or scene to be displayed (ignored for other projector types).
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Response extends BaseResponse {
    }
}