package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.events.replaybuffer.ReplayStarted;
import nl.harm27.obswebsocket.api.events.replaybuffer.ReplayStarting;
import nl.harm27.obswebsocket.api.events.replaybuffer.ReplayStopped;
import nl.harm27.obswebsocket.api.events.replaybuffer.ReplayStopping;

import java.util.EnumMap;
import java.util.Map;

import static nl.harm27.obswebsocket.api.events.EventType.*;

/**
 * The EventListener for the events that are part of the Replay Buffer category.
 */
public abstract class ReplayBufferEventListener implements EventListener {
    @Override
    public final Map<EventType, Class<?>> getSupportedEvents() {
        Map<EventType, Class<?>> supportedEvents = new EnumMap<>(EventType.class);
        supportedEvents.put(REPLAY_STARTING, ReplayStarting.class);
        supportedEvents.put(REPLAY_STARTED, ReplayStarted.class);
        supportedEvents.put(REPLAY_STOPPING, ReplayStopping.class);
        supportedEvents.put(REPLAY_STOPPED, ReplayStopped.class);
        return supportedEvents;
    }

    @Override
    public final void callEvent(BaseEvent baseEvent) {
        switch (baseEvent.getEventType()) {
            case REPLAY_STARTING:
                replayStarting((ReplayStarting) baseEvent);
                break;
            case REPLAY_STARTED:
                replayStarted((ReplayStarted) baseEvent);
                break;
            case REPLAY_STOPPING:
                replayStopping((ReplayStopping) baseEvent);
                break;
            case REPLAY_STOPPED:
                replayStopped((ReplayStopped) baseEvent);
                break;
            default:
                throw new IllegalStateException("Unexpected EventType for ReplayBufferEventListener.");
        }
    }

    /**
     * Implement this method to process ReplayStarting events.
     *
     * @param replayStarting The received event.
     */
    public void replayStarting(ReplayStarting replayStarting) {

    }

    /**
     * Implement this method to process ReplayStarted events.
     *
     * @param replayStarted The received event.
     */
    public void replayStarted(ReplayStarted replayStarted) {
    }

    /**
     * Implement this method to process ReplayStopping events.
     *
     * @param replayStopping The received event.
     */
    public void replayStopping(ReplayStopping replayStopping) {
    }

    /**
     * Implement this method to process ReplayStopped events.
     *
     * @param replayStopped The received event.
     */
    public void replayStopped(ReplayStopped replayStopped) {
    }
}
