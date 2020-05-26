package nl.harm27.obswebsocket.api.events.streaming;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.events.BaseEvent;

/**
 * A request to stop streaming has been issued.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#StreamStopping">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class StreamStopping extends BaseEvent {
    @JsonProperty("preview-only")
    private boolean previewOnly;

    /**
     * Always false (retrocompatibility).
     */
    public boolean isPreviewOnly() {
        return previewOnly;
    }
}