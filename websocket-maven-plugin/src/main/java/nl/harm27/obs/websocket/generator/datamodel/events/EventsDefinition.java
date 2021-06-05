package nl.harm27.obs.websocket.generator.datamodel.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventsDefinition {
    @JsonProperty("scenes")
    private List<Event> sceneEvents;
    @JsonProperty("transitions")
    private List<Event> transitionEvents;
    @JsonProperty("profiles")
    private List<Event> profileEvents;
    @JsonProperty("streaming")
    private List<Event> streamingEvents;
    @JsonProperty("recording")
    private List<Event> recordingEvents;
    @JsonProperty("replay buffer")
    private List<Event> replayBufferEvents;
    @JsonProperty("other")
    private List<Event> otherEvents;
    @JsonProperty("general")
    private List<Event> generalEvents;
    @JsonProperty("sources")
    private List<Event> sourceEvents;
    @JsonProperty("media")
    private List<Event> mediaEvents;
    @JsonProperty("scene items")
    private List<Event> sceneItemEvents;
    @JsonProperty("studio mode")
    private List<Event> studioModeEvents;
    @JsonProperty("virtual cam")
    private List<Event> virtualCamEvents;

    public Map<String, List<Event>> getEventsMap() {
        return getEvents().collect(Collectors.toMap(events -> events.get(0).getCategory(), Function.identity()));
    }

    public List<String> getEventNames() {
        return getEvents().flatMap(List::stream).map(Event::getName).collect(Collectors.toList());
    }

    private Stream<List<Event>> getEvents() {
        return Stream.of(sceneEvents, transitionEvents, profileEvents, streamingEvents,
                recordingEvents, replayBufferEvents, otherEvents, generalEvents,
                sourceEvents, mediaEvents, sceneItemEvents, studioModeEvents, virtualCamEvents);
    }
}
