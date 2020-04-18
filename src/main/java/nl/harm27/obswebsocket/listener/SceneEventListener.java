package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.events.scenes.SwitchScenes;

import java.util.EnumMap;
import java.util.Map;

public abstract class SceneEventListener implements EventListener {
    @Override
    public final Map<EventType, Class<?>> getSupportedEvents() {
        Map<EventType, Class<?>> supportedEvents = new EnumMap<>(EventType.class);
        supportedEvents.put(EventType.SWITCH_SCENES, SwitchScenes.class);
        return supportedEvents;
    }

    @Override
    public final void callEvent(BaseEvent baseEvent) {
        switch (baseEvent.getEventType()) {
            case SWITCH_SCENES:
                switchScenes((SwitchScenes) baseEvent);
                break;
            default:
                throw new IllegalStateException("Unexpected EventType for SceneEventListener.");
        }
    }

    /**
     * Implement this method to process SwitchScenes events.
     *
     * @param switchScenes The received event.
     */
    public void switchScenes(SwitchScenes switchScenes) {

    }
}
