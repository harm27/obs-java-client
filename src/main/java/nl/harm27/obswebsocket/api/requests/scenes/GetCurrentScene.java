package nl.harm27.obswebsocket.api.requests.scenes;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.SceneItem;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

import java.util.List;

/**
 * Get the current scene's name and source items.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetCurrentScene">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class GetCurrentScene {
    private GetCurrentScene() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_CURRENT_SCENE, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetCurrentScene.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("name")
        private String name;
        @SerializedName("sources")
        private List<SceneItem> sources;

        /**
         * Name of the currently active scene.
         */
        public String getName() {
            return name;
        }

        /**
         * Ordered list of the current scene's source items.
         */
        public List<SceneItem> getSources() {
            return sources;
        }
    }
}