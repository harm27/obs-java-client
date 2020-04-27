package nl.harm27.obswebsocket.api.requests.general;

import com.google.gson.annotations.SerializedName;
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
        @SerializedName("Preview")
        PREVIEW,
        @SerializedName("Source")
        SOURCE,
        @SerializedName("Scene")
        SCENE,
        @SerializedName("StudioProgram")
        STUDIO_PROGRAM,
        @SerializedName("Multiview")
        MULTIVIEW
    }

    public static class Request extends BaseRequest {
        @SerializedName("type")
        private Type type;
        @SerializedName("monitor")
        private int monitor;
        @SerializedName("geometry")
        private String geometry;
        @SerializedName("name")
        private String name;

        public Request(String messageId) {
            super(RequestType.OPEN_PROJECTOR, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
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