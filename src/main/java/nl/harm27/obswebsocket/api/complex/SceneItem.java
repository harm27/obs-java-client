package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#sceneitem">OBS WebSocket Documentation</a>
 */
public class SceneItem {
    private Number cy;
    private Number cx;
    private String name;
    private int id;
    private boolean render;
    private boolean locked;
    @SerializedName("source_cx")
    private Number sourceCx;
    @SerializedName("source_cy")
    private Number sourceCy;
    private SourceType type;
    private Number volume;
    private Number x;
    private Number y;
    private String parentGroupName;
    private List<SceneItem> groupChildren;

    public Number getCy() {
        return cy;
    }

    public Number getCx() {
        return cx;
    }

    /**
     * The name of this Scene Item.
     */
    public String getName() {
        return name;
    }

    /**
     * Scene item ID
     */
    public int getId() {
        return id;
    }

    /**
     * Whether or not this Scene Item is set to "visible".
     */
    public boolean getRender() {
        return render;
    }

    /**
     * Whether or not this Scene Item is locked and can't be moved around
     */
    public boolean isLocked() {
        return locked;
    }

    public Number getSourceCx() {
        return sourceCx;
    }

    public Number getSourceCy() {
        return sourceCy;
    }

    /**
     * Source type. Value is one of the following: "input", "filter", "transition", "scene" or "unknown"
     */
    public SourceType getType() {
        return type;
    }

    public Number getVolume() {
        return volume;
    }

    public Number getX() {
        return x;
    }

    public Number getY() {
        return y;
    }

    /**
     * Name of the item's parent (if this item belongs to a group) (optional)
     */
    public Optional<String> getParentGroupName() {
        return Optional.ofNullable(parentGroupName);
    }

    /**
     * List of children (if this item is a group) (optional)
     */
    public Optional<List<SceneItem>> getGroupChildren() {
        return Optional.ofNullable(groupChildren);
    }
}
