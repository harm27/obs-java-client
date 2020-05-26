package nl.harm27.obswebsocket.api.events.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.complex.OBSStats;
import nl.harm27.obswebsocket.api.events.BaseEvent;

import java.time.Duration;
import java.util.Optional;

/**
 * Emitted every 2 seconds after enabling it by calling SetHeartbeat.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#Heartbeat">OBS WebSocket Documentation</a>
 * @since v4.3.0
 */
public class Heartbeat extends BaseEvent {
    @JsonProperty("pulse")
    private boolean pulse;
    @JsonProperty("current-profile")
    private String currentProfile;
    @JsonProperty("current-scene")
    private String currentScene;
    @JsonProperty("streaming")
    private Boolean streaming;
    @JsonProperty("total-stream-time")
    private Integer totalStreamTime;
    @JsonProperty("total-stream-bytes")
    private Integer totalStreamBytes;
    @JsonProperty("total-stream-frames")
    private Integer totalStreamFrames;
    private Boolean recording;
    @JsonProperty("total-record-time")
    private Integer totalRecordTime;
    @JsonProperty("total-record-bytes")
    private Integer totalRecordBytes;
    @JsonProperty("total-record-frames")
    private Integer totalRecordFrames;
    @JsonProperty("stats")
    private OBSStats stats;

    /**
     * Toggles between every JSON message as an "I am alive" indicator.
     */
    public boolean getPulse() {
        return pulse;
    }

    /**
     * Current active profile.
     */
    public Optional<String> getCurrentProfile() {
        return Optional.ofNullable(currentProfile);
    }

    /**
     * Current active scene.
     */
    public Optional<String> getCurrentScene() {
        return Optional.ofNullable(currentScene);
    }

    /**
     * Current streaming state.
     */
    public Optional<Boolean> isStreaming() {
        return Optional.ofNullable(streaming);
    }

    /**
     * Total time (in seconds) since the stream started as int.
     */
    public Optional<Integer> getTotalStreamTime() {
        return Optional.ofNullable(totalStreamTime);
    }

    /**
     * Total time (in seconds) since the stream started as duration.
     */
    public Optional<Duration> getTotalStreamDuration() {
        if (totalStreamTime != null)
            return Optional.of(Duration.ofSeconds(totalStreamTime));
        return Optional.empty();
    }


    /**
     * Total bytes sent since the stream started.
     */
    public Optional<Integer> getTotalStreamBytes() {
        return Optional.ofNullable(totalStreamBytes);
    }

    /**
     * Total frames streamed since the stream started.
     */
    public Optional<Integer> getTotalStreamFrames() {
        return Optional.ofNullable(totalStreamFrames);
    }

    /**
     * Current recording state.
     */
    public Optional<Boolean> isRecording() {
        return Optional.ofNullable(recording);
    }

    /**
     * Total time (in seconds) since recording started as int.
     */
    public Optional<Integer> getTotalRecordTime() {
        return Optional.ofNullable(totalRecordTime);
    }

    /**
     * Total time (in seconds) since recording started as duration.
     */
    public Optional<Duration> getTotalRecordDuration() {
        if (totalRecordTime != null)
            return Optional.of(Duration.ofSeconds(totalRecordTime));
        return Optional.empty();
    }

    /**
     * Total bytes recorded since the recording started.
     */
    public Optional<Integer> getTotalRecordBytes() {
        return Optional.ofNullable(totalRecordBytes);
    }

    /**
     * Total frames recorded since the recording started.
     */
    public Optional<Integer> getTotalRecordFrames() {
        return Optional.ofNullable(totalRecordFrames);
    }

    /**
     * OBS Stats
     */
    public OBSStats getStats() {
        return stats;
    }
}