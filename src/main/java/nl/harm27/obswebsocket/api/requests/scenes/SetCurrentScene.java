package nl.harm27.obswebsocket.api.requests.scenes;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Switch to the specified scene.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetCurrentScene">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class SetCurrentScene {
    private SetCurrentScene() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("scene-name")
        private final String sceneName;

        /**
         * @param sceneName Name of the scene to switch to.
         */
        public Request(String messageId, String sceneName) {
            super(RequestType.SET_CURRENT_SCENE, messageId);
            this.sceneName = sceneName;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return SetCurrentScene.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
    }
}