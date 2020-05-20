package nl.harm27.obswebsocket.api.requests.scenes;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.Scene;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

import java.util.List;

/**
 * Get a list of scenes in the currently active profile.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetSceneList">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class GetSceneList {
    private GetSceneList() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_SCENE_LIST, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetSceneList.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("current-scene")
        private String currentScene;
        @SerializedName("scenes")
        private List<Scene> scenes;

        /**
         * Name of the currently active scene.
         */
        public String getCurrentScene() {
            return currentScene;
        }

        /**
         * Ordered list of the current profile's scenes (See {@link GetCurrentScene} for more information).
         */
        public List<Scene> getScenes() {
            return scenes;
        }
    }
}