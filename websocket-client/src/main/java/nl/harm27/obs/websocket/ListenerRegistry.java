package nl.harm27.obs.websocket;


import nl.harm27.obs.websocket.api.base.EventType;
import nl.harm27.obs.websocket.listener.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerRegistry {
    private final Map<EventListener, List<EventType>> eventListenersMap;

    public ListenerRegistry() {
        eventListenersMap = new HashMap<>();
    }

    public void registerListener(EventListener eventListener) {
        eventListenersMap.put(eventListener, eventListener.getSupportedEvents());
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

