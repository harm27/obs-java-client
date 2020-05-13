package nl.harm27.obswebsocket.api.events;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.Optional;

import static nl.harm27.obswebsocket.api.TimeUtil.parseDuration;

/**
 * Events are broadcast by the server to each connected client when a recognized action occurs within OBS.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#events">OBS WebSocket Documentation</a>
 */
public abstract class BaseEvent {
    @SerializedName("update-type")
    private EventType eventType;

    @SerializedName("stream-timecode")
    private String streamTimecode;

    @SerializedName("rec-timecode")
    private String recordingTimecode;

    /**
     * The type of event.
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Time elapsed between now and stream start (only present if OBS Studio is streaming) as string.
     */
    public Optional<String> getStreamTimecode() {
        return Optional.ofNullable(streamTimecode);
    }

    /**
     * Time elapsed between now and recording start (only present if OBS Studio is recording) as string.
     */
    public Optional<String> getRecordingTimecode() {
        return Optional.ofNullable(recordingTimecode);
    }

    /**
     * Time elapsed between now and stream start (only present if OBS Studio is streaming) as duration.
     */
    public Optional<Duration> getStreamDuration() {
        if (streamTimecode != null)
            return parseDuration(streamTimecode);
        return Optional.empty();
    }

    /**
     * Time elapsed between now and recording start (only present if OBS Studio is recording) as duration.
     */
    public Optional<Duration> getRecordingDuration() {
        if (recordingTimecode != null)
            return parseDuration(recordingTimecode);
        return Optional.empty();
    }
}
