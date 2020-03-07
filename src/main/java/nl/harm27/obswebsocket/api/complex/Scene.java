package nl.harm27.obswebsocket.api.complex;

import java.util.List;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#scene">OBS WebSocket Documentation</a>
 */
public class Scene {
    private String name;
    private List<SceneItem> sources;

    /**
     * Name of the scene.
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