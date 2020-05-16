package nl.harm27.obswebsocket.api.events.streaming;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.events.BaseEvent;

/**
 * Emit every 2 seconds.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StreamStatus">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class StreamStatus extends BaseEvent {
    @SerializedName("streaming")
    private boolean streaming;
    @SerializedName("recording")
    private boolean recording;
    @SerializedName("replay-buffer-active")
    private boolean replayBufferActive;
    @SerializedName("bytes-per-sec")
    private int bytesPerSec;
    @SerializedName("kbits-per-sec")
    private int kbitsPerSec;
    @SerializedName("strain")
    private double strain;
    @SerializedName("total-stream-time")
    private int totalStreamTime;
    @SerializedName("num-total-frames")
    private int numTotalFrames;
    @SerializedName("num-dropped-frames")
    private int numDroppedFrames;
    @SerializedName("fps")
    private double fps;
    @SerializedName("render-total-frames")
    private int renderTotalFrames;
    @SerializedName("render-missed-frames")
    private int renderMissedFrames;
    @SerializedName("output-total-frames")
    private int outputTotalFrames;
    @SerializedName("output-skipped-frames")
    private int outputSkippedFrames;
    @SerializedName("average-frame-time")
    private double averageFrameTime;
    @SerializedName("cpu-usage")
    private double cpuUsage;
    @SerializedName("memory-usage")
    private double memoryUsage;
    @SerializedName("free-disk-space")
    private double freeDiskSpace;
    @SerializedName("preview-only")
    private boolean previewOnly;

    /**
     * Current streaming state.
     */
    public boolean isStreaming() {
        return streaming;
    }

    /**
     * Current recording state.
     */
    public boolean isRecording() {
        return recording;
    }

    /**
     * Replay Buffer status
     */
    public boolean isReplayBufferActive() {
        return replayBufferActive;
    }

    /**
     * Amount of data per second (in bytes) transmitted by the stream encoder.
     */
    public int getBytesPerSec() {
        return bytesPerSec;
    }

    /**
     * Amount of data per second (in kilobits) transmitted by the stream encoder.
     */
    public int getKbitsPerSec() {
        return kbitsPerSec;
    }

    /**
     * Percentage of dropped frames.
     */
    public double getStrain() {
        return strain;
    }

    /**
     * Total time (in seconds) since the stream started.
     */
    public int getTotalStreamTime() {
        return totalStreamTime;
    }

    /**
     * Total number of frames transmitted since the stream started.
     */
    public int getNumTotalFrames() {
        return numTotalFrames;
    }

    /**
     * Number of frames dropped by the encoder since the stream started.
     */
    public int getNumDroppedFrames() {
        return numDroppedFrames;
    }

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

    /**
     * Always false (retrocompatibility).
     */
    public boolean isPreviewOnly() {
        return previewOnly;
    }
}