package nl.harm27.obswebsocket.listener;

import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.events.scenes.SceneCollectionChanged;
import nl.harm27.obswebsocket.api.events.scenes.ScenesChanged;
import nl.harm27.obswebsocket.api.events.scenes.SwitchScenes;

import java.util.EnumMap;
import java.util.Map;

import static nl.harm27.obswebsocket.api.events.EventType.*;

/**
 * The EventListener for the events that are part of the Scenes categorie.
 */
public abstract class ScenesEventListener implements EventListener {
    @Override
    public final Map<EventType, Class<?>> getSupportedEvents() {
        Map<EventType, Class<?>> supportedEvents = new EnumMap<>(EventType.class);
        supportedEvents.put(SWITCH_SCENES, SwitchScenes.class);
        supportedEvents.put(SCENES_CHANGED, ScenesChanged.class);
        supportedEvents.put(SCENE_COLLECTION_CHANGED, SceneCollectionChanged.class);
        return supportedEvents;
    }

    @Override
    public final void callEvent(BaseEvent baseEvent) {
        switch (baseEvent.getEventType()) {
            case SWITCH_SCENES:
                switchScenes((SwitchScenes) baseEvent);
                break;
            case SCENES_CHANGED:
                scenesChanged((ScenesChanged) baseEvent);
                break;
            case SCENE_COLLECTION_CHANGED:
                sceneCollectionChanged((SceneCollectionChanged) baseEvent);
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

    /**
     * Implement this method to process ScenesChanged events.
     *
     * @param scenesChanged The received event.
     */
    public void scenesChanged(ScenesChanged scenesChanged) {
    }

    /**
     * Implement this method to process SceneCollectionChanged events.
     *
     * @param sceneCollectionChanged The received event.
     */
    public void sceneCollectionChanged(SceneCollectionChanged sceneCollectionChanged) {
    }
}
