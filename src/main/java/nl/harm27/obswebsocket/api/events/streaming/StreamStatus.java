package nl.harm27.obswebsocket.api.events.streaming;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nl.harm27.obswebsocket.api.complex.OBSStats;
import nl.harm27.obswebsocket.api.events.BaseEvent;

/**
 * Emit every 2 seconds.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StreamStatus">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class StreamStatus extends BaseEvent {
    @JsonProperty("streaming")
    private boolean streaming;
    @JsonProperty("recording")
    private boolean recording;
    @JsonProperty("replay-buffer-active")
    private boolean replayBufferActive;
    @JsonProperty("bytes-per-sec")
    private int bytesPerSec;
    @JsonProperty("kbits-per-sec")
    private int kbitsPerSec;
    @JsonProperty("strain")
    private double strain;
    @JsonProperty("total-stream-time")
    private int totalStreamTime;
    @JsonProperty("num-total-frames")
    private int numTotalFrames;
    @JsonProperty("num-dropped-frames")
    private int numDroppedFrames;
    @JsonUnwrapped
    private OBSStats stats;
    @JsonProperty("preview-only")
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
     * Gets data from the OBSStats.
     */
    public OBSStats getStats() {
        return stats;
    }

    /**
     * Always false (retrocompatibility).
     */
    public boolean isPreviewOnly() {
        return previewOnly;
    }


}