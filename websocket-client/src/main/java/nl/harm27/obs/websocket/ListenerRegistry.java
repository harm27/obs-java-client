package nl.harm27.obs.websocket;


import nl.harm27.obs.websocket.api.base.EventType;
import nl.harm27.obs.websocket.listener.EventListener;

import java.util.*;

public class ListenerRegistry {
    private final Map<EventListener, List<EventType>> eventListenersMap;
    private final Map<EventType, Class<?>> requestedEventTypes;

    public ListenerRegistry() {
        eventListenersMap = new HashMap<>();
        requestedEventTypes = new EnumMap<>(EventType.class);
    }

    public void registerListener(EventListener eventListener) {
        Map<EventType, Class<?>> supportedEvents = eventListener.getSupportedEvents();
        eventListenersMap.put(eventListener, new ArrayList<>(supportedEvents.keySet()));
        for (Map.Entry<EventType, Class<?>> supportedEvent : supportedEvents.entrySet()) {
            requestedEventTypes.putIfAbsent(supportedEvent.getKey(), supportedEvent.getValue());
        }
    }

    public Class<?> getClassForEvent(EventType eventType) {
        return requestedEventTypes.get(eventType);
    }

    public List<EventListener> getListenersForEventType(EventType eventType) {
        List<EventListener> eventListeners = new ArrayList<>();
        for (Map.Entry<EventListener, List<EventType>> entry : eventListenersMap.entrySet()) {
            if (entry.getValue().contains(eventType))
                eventListeners.add(entry.getKey());
        }
        return eventListeners;
    }
}

