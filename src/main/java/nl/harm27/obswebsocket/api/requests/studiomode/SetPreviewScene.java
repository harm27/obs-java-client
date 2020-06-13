package nl.harm27.obswebsocket.api.requests.studiomode;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Set the active preview scene. Will return an error if Studio Mode is not enabled.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetPreviewScene">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class SetPreviewScene {
    private SetPreviewScene() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("scene-name")
        private final String sceneName;

        /**
         * @param sceneName The name of the scene to preview.
         */
        public Request(String messageId, String sceneName) {
            super(RequestType.SET_PREVIEW_SCENE, messageId);
            this.sceneName = sceneName;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SetPreviewScene.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}