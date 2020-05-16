package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.events.streaming.StreamStarted;
import nl.harm27.obswebsocket.api.events.streaming.StreamStarting;
import nl.harm27.obswebsocket.api.events.streaming.StreamStopping;

import java.util.EnumMap;
import java.util.Map;

import static nl.harm27.obswebsocket.api.events.EventType.*;

/**
 * The EventListener for the events that are part of the Streaming category.
 */
public abstract class StreamingEventListener implements EventListener {
    @Override
    public final Map<EventType, Class<?>> getSupportedEvents() {
        Map<EventType, Class<?>> supportedEvents = new EnumMap<>(EventType.class);
        supportedEvents.put(STREAM_STARTING, StreamStarting.class);
        supportedEvents.put(STREAM_STARTED, StreamStarted.class);
        supportedEvents.put(STREAM_STOPPING, StreamStopping.class);
        return supportedEvents;
    }

    @Override
    public final void callEvent(BaseEvent baseEvent) {
        switch (baseEvent.getEventType()) {
            case STREAM_STARTING:
                streamStarting((StreamStarting) baseEvent);
                break;
            case STREAM_STARTED:
                streamStarted((StreamStarted) baseEvent);
                break;
            case STREAM_STOPPING:
                streamStopping((StreamStopping) baseEvent);
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

    /**
     * Implement this method to process StreamStarted events.
     *
     * @param streamStarted The received event.
     */
    public void streamStarted(StreamStarted streamStarted) {
    }

    /**
     * Implement this method to process StreamStopping events.
     *
     * @param streamStopping The received event.
     */
    public void streamStopping(StreamStopping streamStopping) {
    }
}
