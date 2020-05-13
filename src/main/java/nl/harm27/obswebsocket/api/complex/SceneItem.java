package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#sceneitem">OBS WebSocket Documentation</a>
 */
public class SceneItem {
    @SerializedName("cy")
    private Number cy;
    @SerializedName("cx")
    private Number cx;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("render")
    private boolean render;
    @SerializedName("locked")
    private boolean locked;
    @SerializedName("source_cx")
    private Number sourceCx;
    @SerializedName("source_cy")
    private Number sourceCy;
    @SerializedName("type")
    private SourceType type;
    @SerializedName("volume")
    private Number volume;
    @SerializedName("x")
    private Number x;
    @SerializedName("y")
    private Number y;
    @SerializedName("parentGroupName")
    private String parentGroupName;
    @SerializedName("groupChildren")
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
     * Set the name of a scene item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Scene item ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the scene item ID.
     */
    public void setId(int id) {
        this.id = id;
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
