package nl.harm27.obswebsocket.api.complex;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#output">OBS WebSocket Documentation</a>
 */
public class Output {
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;
    @JsonProperty("flags")
    private Object flags;
    @JsonProperty("flags.rawValue")
    private int flagsRawValue;
    @JsonProperty("flags.audio")
    private boolean flagsAudio;
    @JsonProperty("flags.video")
    private boolean flagsVideo;
    @JsonProperty("flags.encoded")
    private boolean flagsEncoded;
    @JsonProperty("flags.multiTrack")
    private boolean flagsMultiTrack;
    @JsonProperty("flags.service")
    private boolean flagsService;
    @JsonProperty("settings")
    private Object settings;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("reconnecting")
    private boolean reconnecting;
    @JsonProperty("congestion")
    private double congestion;
    @JsonProperty("totalFrames")
    private int totalFrames;
    @JsonProperty("droppedFrames")
    private int droppedFrames;
    @JsonProperty("totalBytes")
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
