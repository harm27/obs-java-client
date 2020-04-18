package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;

import java.util.Map;

public interface EventListener {
    Map<EventType, Class<?>> getSupportedEvents();

    void callEvent(BaseEvent baseEvent);
}
