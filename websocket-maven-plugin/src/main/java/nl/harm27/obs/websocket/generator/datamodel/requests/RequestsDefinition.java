package nl.harm27.obs.websocket.generator.datamodel.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestsDefinition {
    @JsonProperty("general")
    private List<Request> generalRequests;
    @JsonProperty("media control")
    private List<Request> mediaControlRequests;
    @JsonProperty("sources")
    private List<Request> sourceRequests;
    @JsonProperty("outputs")
    private List<Request> outputRequests;
    @JsonProperty("profiles")
    private List<Request> profileRequests;
    @JsonProperty("recording")
    private List<Request> recordingRequests;
    @JsonProperty("replay buffer")
    private List<Request> replayBufferRequests;
    @JsonProperty("scene collections")
    private List<Request> sceneCollectionRequests;
    @JsonProperty("scene items")
    private List<Request> sceneItemRequests;
    @JsonProperty("scenes")
    private List<Request> sceneRequests;
    @JsonProperty("streaming")
    private List<Request> streamingRequests;
    @JsonProperty("studio mode")
    private List<Request> studioModeRequests;
    @JsonProperty("transitions")
    private List<Request> transitionRequests;
    @JsonProperty("virtual cam")
    private List<Request> virtualCamRequests;

    public Map<String, List<Request>> getRequestsMap() {
        return getRequests().collect(Collectors.toMap(requests -> requests.get(0).getCategory(), Function.identity()));
    }

    public List<String> getRequestNames() {
        return getRequests().flatMap(List::stream).map(Request::getName).collect(Collectors.toList());
    }

    private Stream<List<Request>> getRequests() {
        return Stream.of(generalRequests, mediaControlRequests, sourceRequests, outputRequests,
                profileRequests, recordingRequests, replayBufferRequests, sceneCollectionRequests,
                sceneItemRequests, sceneRequests, streamingRequests, studioModeRequests, transitionRequests, virtualCamRequests);
    }
}
