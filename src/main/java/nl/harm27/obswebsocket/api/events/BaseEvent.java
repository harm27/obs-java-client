package nl.harm27.obswebsocket.api.events;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.Optional;
import java.util.regex.Pattern;

import static nl.harm27.obswebsocket.OBSWebSocket.NUMERIC_FORMAT_PATTERN;

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
            return parseDuration(streamTimecode);
        return Optional.empty();
    }

    protected Optional<Duration> parseDuration(String timecode) {
        String[] arrayPart1 = timecode.split(":");
        if (arrayPart1.length != 3)
            return Optional.empty();
        String[] arrayPart2 = arrayPart1[2].split(Pattern.quote("."));
        if (arrayPart2.length != 2)
            return Optional.empty();

        String hours = arrayPart1[0];
        if (isNotNumeric(hours))
            return Optional.empty();

        String minutes = arrayPart1[1];
        if (isNotNumeric(minutes))
            return Optional.empty();

        String seconds = arrayPart2[0];
        if (isNotNumeric(seconds))
            return Optional.empty();

        String millis = arrayPart2[1];
        if (isNotNumeric(millis))
            return Optional.empty();

        return Optional.ofNullable(Duration.ofHours(Long.parseLong(hours))
                .plusMinutes(Long.parseLong(minutes))
                .plusSeconds(Long.parseLong(seconds))
                .plusMillis(Long.parseLong(millis)));
    }

    private boolean isNotNumeric(String numberString) {
        return !NUMERIC_FORMAT_PATTERN.matcher(numberString).matches();
    }
}
