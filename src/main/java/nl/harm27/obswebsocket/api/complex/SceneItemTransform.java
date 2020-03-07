package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#sceneitemtransform">OBS WebSocket Documentation</a>
 */
public class SceneItemTransform {
    @SerializedName("position.x")
    private int positionX;
    @SerializedName("position.y")
    private int positionY;
    @SerializedName("position.alignment")
    private int positionAlignment;
    private double rotation;
    @SerializedName("scale.x")
    private double scaleX;
    @SerializedName("scale.y")
    private double scaleY;
    @SerializedName("crop.top")
    private int cropTop;
    @SerializedName("crop.right")
    private int cropRight;
    @SerializedName("crop.bottom")
    private int cropBottom;
    @SerializedName("crop.left")
    private int cropLeft;
    private boolean visible;
    private boolean locked;
    @SerializedName("bounds.type")
    private BoundsType boundsType;
    @SerializedName("bounds.alignment")
    private int boundsAlignment;
    @SerializedName("bounds.x")
    private double boundsX;
    @SerializedName("bounds.y")
    private double boundsY;
    private int sourceWidth;
    private int sourceHeight;
    private double width;
    private double height;
    private String parentGroupName;
    private List<SceneItemTransform> groupChildren;

    /**
     * The x position of the scene item from the left.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * The y position of the scene item from the top.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * The point on the scene item that the item is manipulated from.
     */
    public int getPositionAlignment() {
        return positionAlignment;
    }

    /**
     * The clockwise rotation of the scene item in degrees around the point of alignment.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * The x-scale factor of the scene item.
     */
    public double getScaleX() {
        return scaleX;
    }

    /**
     * The y-scale factor of the scene item.
     */
    public double getScaleY() {
        return scaleY;
    }

    /**
     * The number of pixels cropped off the top of the scene item before scaling.
     */
    public int getCropTop() {
        return cropTop;
    }

    /**
     * The number of pixels cropped off the right of the scene item before scaling.
     */
    public int getCropRight() {
        return cropRight;
    }

    /**
     * The number of pixels cropped off the bottom of the scene item before scaling.
     */
    public int getCropBottom() {
        return cropBottom;
    }

    /**
     * The number of pixels cropped off the left of the scene item before scaling.
     */
    public int getCropLeft() {
        return cropLeft;
    }

    /**
     * If the scene item is visible.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * If the scene item is locked in position.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Type of bounding box. Can be "OBS_BOUNDS_STRETCH", "OBS_BOUNDS_SCALE_INNER", "OBS_BOUNDS_SCALE_OUTER",
     * "OBS_BOUNDS_SCALE_TO_WIDTH", "OBS_BOUNDS_SCALE_TO_HEIGHT", "OBS_BOUNDS_MAX_ONLY" or "OBS_BOUNDS_NONE".
     */
    public BoundsType getBoundsType() {
        return boundsType;
    }

    /**
     * Alignment of the bounding box.
     */
    public int getBoundsAlignment() {
        return boundsAlignment;
    }

    /**
     * Width of the bounding box.
     */
    public double getBoundsX() {
        return boundsX;
    }

    /**
     * Height of the bounding box.
     */
    public double getBoundsY() {
        return boundsY;
    }

    /**
     * Base width (without scaling) of the source.
     */
    public int getSourceWidth() {
        return sourceWidth;
    }

    /**
     * Base source (without scaling) of the source.
     */
    public int getSourceHeight() {
        return sourceHeight;
    }

    /**
     * Scene item width (base source width multiplied by the horizontal scaling factor).
     */
    public double getWidth() {
        return width;
    }

    /**
     * Scene item height (base source height multiplied by the vertical scaling factor).
     */
    public double getHeight() {
        return height;
    }

    /**
     * Name of the item's parent (if this item belongs to a group).
     */
    public Optional<String> getParentGroupName() {
        return Optional.ofNullable(parentGroupName);
    }

    /**
     * List of children (if this item is a group).
     */
    public Optional<List<SceneItemTransform>> getGroupChildren() {
        return Optional.ofNullable(groupChildren);
    }
}
