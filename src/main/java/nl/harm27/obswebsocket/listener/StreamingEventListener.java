package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.events.streaming.StreamStarting;

import java.util.EnumMap;
import java.util.Map;

import static nl.harm27.obswebsocket.api.events.EventType.STREAM_STARTING;

/**
 * The EventListener for the events that are part of the Streaming category.
 */
public abstract class StreamingEventListener implements EventListener {
    @Override
    public final Map<EventType, Class<?>> getSupportedEvents() {
        Map<EventType, Class<?>> supportedEvents = new EnumMap<>(EventType.class);
        supportedEvents.put(STREAM_STARTING, StreamStarting.class);
        return supportedEvents;
    }

    @Override
    public final void callEvent(BaseEvent baseEvent) {
        switch (baseEvent.getEventType()) {
            case STREAM_STARTING:
                streamStarting((StreamStarting) baseEvent);
                break;
            default:
                throw new IllegalStateException("Unexpected EventType for StreamingEventListener.");
        }
    }

    /**
     * Implement this method to process StreamStarting events.
     *
     * @param streamStarting The received event.
     */
    public void streamStarting(StreamStarting streamStarting) {
    }
}
