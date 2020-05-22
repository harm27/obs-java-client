package nl.harm27.obswebsocket.api.complex;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#obsstats">OBS WebSocket Documentation</a>
 */
public class OBSStats {
    @JsonProperty("fps")
    private double fps;
    @JsonProperty("render-total-frames")
    private int renderTotalFrames;
    @JsonProperty("render-missed-frames")
    private int renderMissedFrames;
    @JsonProperty("output-total-frames")
    private int outputTotalFrames;
    @JsonProperty("output-skipped-frames")
    private int outputSkippedFrames;
    @JsonProperty("average-frame-time")
    private double averageFrameTime;
    @JsonProperty("cpu-usage")
    private double cpuUsage;
    @JsonProperty("memory-usage")
    private double memoryUsage;
    @JsonProperty("free-disk-space")
    private double freeDiskSpace;

    /**
     * Current framerate.
     */
    public double getFps() {
        return fps;
    }

    /**
     * Number of frames rendered.
     */
    public int getRenderTotalFrames() {
        return renderTotalFrames;
    }

    /**
     * Number of frames missed due to rendering lag.
     */
    public int getRenderMissedFrames() {
        return renderMissedFrames;
    }

    /**
     * Number of frames outputted.
     */
    public int getOutputTotalFrames() {
        return outputTotalFrames;
    }

    /**
     * Number of frames skipped due to encoding lag.
     */
    public int getOutputSkippedFrames() {
        return outputSkippedFrames;
    }

    /**
     * Average frame render time (in milliseconds).
     */
    public double getAverageFrameTime() {
        return averageFrameTime;
    }

    /**
     * Current CPU usage (percentage).
     */
    public double getCpuUsage() {
        return cpuUsage;
    }

    /**
     * Current RAM usage (in megabytes).
     */
    public double getMemoryUsage() {
        return memoryUsage;
    }

    /**
     * Free recording disk space (in megabytes).
     */
    public double getFreeDiskSpace() {
        return freeDiskSpace;
    }
}
