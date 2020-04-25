package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.events.general.Heartbeat;
import nl.harm27.obswebsocket.api.events.other.Exiting;

import java.util.EnumMap;
import java.util.Map;

import static nl.harm27.obswebsocket.api.events.EventType.EXITING;
import static nl.harm27.obswebsocket.api.events.EventType.HEARTBEAT;

/**
 * The EventListener for the events that are part of the General or Other category.
 */
public abstract class GeneralEventListener implements EventListener {
    @Override
    public final Map<EventType, Class<?>> getSupportedEvents() {
        Map<EventType, Class<?>> supportedEvents = new EnumMap<>(EventType.class);
        supportedEvents.put(EXITING, Exiting.class);
        supportedEvents.put(HEARTBEAT, Heartbeat.class);
        return supportedEvents;
    }

    @Override
    public final void callEvent(BaseEvent baseEvent) {
        switch (baseEvent.getEventType()) {
            case EXITING:
                exiting((Exiting) baseEvent);
                break;
            case HEARTBEAT:
                heartbeat((Heartbeat) baseEvent);
                break;
            default:
                throw new IllegalStateException("Unexpected EventType for GeneralEventListener.");
        }
    }

    /**
     * Implement this method to process Exiting events.
     *
     * @param exiting The received event.
     */
    public void exiting(Exiting exiting) {
    }

    /**
     * Implement this method to process Heartbeat events.
     *
     * @param heartbeat The received event.
     */
    public void heartbeat(Heartbeat heartbeat) {
    }
}
