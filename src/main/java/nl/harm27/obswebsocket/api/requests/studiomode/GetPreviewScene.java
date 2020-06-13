package nl.harm27.obswebsocket.api.requests.studiomode;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.complex.SceneItem;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

import java.util.List;

/**
 * Get the name of the currently previewed scene and its list of sources. Will return an error if Studio Mode is not enabled.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetPreviewScene">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class GetPreviewScene {
    private GetPreviewScene() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_PREVIEW_SCENE, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetPreviewScene.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("name")
        private String name;
        @JsonProperty("sources")
        private List<SceneItem> sources;

        /**
         * The name of the active preview scene.
         */
        public String getName() {
            return name;
        }

        /**
         * The sources from this scene.
         */
        public List<SceneItem> getSources() {
            return sources;
        }
    }
}