package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#output">OBS WebSocket Documentation</a>
 */
public class Output {
    private String name;
    private String type;
    private int width;
    private int height;
    private Object flags;
    @SerializedName("falags.rawValue")
    private int flagsRawValue;
    @SerializedName("flags.audio")
    private boolean flagsAudio;
    @SerializedName("flags.video")
    private boolean flagsVideo;
    @SerializedName("flags.encoded")
    private boolean flagsEncoded;
    @SerializedName("flags.multiTrack")
    private boolean flagsMultiTrack;
    @SerializedName("flags.service")
    private boolean flagsService;
    private Object settings;
    private boolean active;
    private boolean reconnecting;
    private double congestion;
    private int totalFrames;
    private int droppedFrames;
    private int totalBytes;

    /**
     * Output name.
     */
    public String getName() {
        return name;
    }

    /**
     * Output type/kind.
     */
    public String getType() {
        return type;
    }

    /**
     * Video output width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Video output height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Output flags.
     */
    public Object getFlags() {
        return flags;
    }

    /**
     * Raw flags value.
     */
    public int getFlagsRawValue() {
        return flagsRawValue;
    }

    /**
     * Output uses audio.
     */
    public boolean isFlagsAudio() {
        return flagsAudio;
    }

    /**
     * Output uses video.
     */
    public boolean isFlagsVideo() {
        return flagsVideo;
    }

    /**
     * Output is encoded.
     */
    public boolean isFlagsEncoded() {
        return flagsEncoded;
    }

    /**
     * Output uses several audio tracks.
     */
    public boolean isFlagsMultiTrack() {
        return flagsMultiTrack;
    }

    /**
     * Output uses a service.
     */
    public boolean isFlagsService() {
        return flagsService;
    }

    public Object getSettings() {
        return settings;
    }

    /**
     * Output status (active or not).
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Output reconnection status (reconnecting or not).
     */
    public boolean isReconnecting() {
        return reconnecting;
    }

    /**
     * Output congestion.
     */
    public double getCongestion() {
        return congestion;
    }

    /**
     * Number of frames sent.
     */
    public int getTotalFrames() {
        return totalFrames;
    }

    /**
     * Number of frames dropped.
     */
    public int getDroppedFrames() {
        return droppedFrames;
    }

    /**
     * Total bytes sent.
     */
    public int getTotalBytes() {
        return totalBytes;
    }
}
