package nl.harm27.obs.websocket.generator.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obs.websocket.generator.datamodel.events.EventsDefinition;
import nl.harm27.obs.websocket.generator.datamodel.requests.RequestsDefinition;
import nl.harm27.obs.websocket.generator.datamodel.types.TypeDefinition;

import java.util.List;

public class OBSWebSocket {
    @JsonProperty("typedefs")
    private List<TypeDefinition> typeDefinitions;
    @JsonProperty("events")
    private EventsDefinition eventsDefinition;
    @JsonProperty("requests")
    private RequestsDefinition requestsDefinition;

    public List<TypeDefinition> getTypeDefinitions() {
        return typeDefinitions;
    }

    public EventsDefinition getEventsDefinition() {
        return eventsDefinition;
    }

    public RequestsDefinition getRequestsDefinition() {
        return requestsDefinition;
    }
}
