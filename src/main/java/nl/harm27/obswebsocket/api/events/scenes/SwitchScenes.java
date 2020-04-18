package nl.harm27.obswebsocket.api.events.scenes;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.SceneItem;
import nl.harm27.obswebsocket.api.events.BaseEvent;

import java.util.List;

/**
 * Indicates a scene change.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#switchscenes">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class SwitchScenes extends BaseEvent {
    @SerializedName("scene-name")
    private String sceneName;
    private List<SceneItem> sources;

    /**
     * The new scene.
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * List of scene items in the new scene. Same specification as GetCurrentScene.
     */
    public List<SceneItem> getSources() {
        return sources;
    }
}