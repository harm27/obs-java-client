package nl.harm27.obswebsocket.api.requests.scenes;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.SceneItem;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

import java.util.List;

/**
 * Changes the order of scene items in the requested scene.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#ReorderSceneItems">OBS WebSocket Documentation</a>
 * @since v4.5.0
 */
public class ReorderSceneItems {
    private ReorderSceneItems() {
    }

    public static class Request extends BaseRequest {
        @SerializedName("items")
        private final List<SceneItem> items;
        @SerializedName("scene")
        private String scene;

        /**
         * @param items Ordered list of objects with name and/or id specified. Id preferred due to uniqueness per scene
         */
        public Request(String messageId, List<SceneItem> items) {
            super(RequestType.REORDER_SCENE_ITEMS, messageId);
            this.items = items;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        /**
         * Name of the scene to reorder (defaults to current).
         */
        public void setScene(String scene) {
            this.scene = scene;
        }
    }

    public static class Response extends BaseResponse {
    }
}