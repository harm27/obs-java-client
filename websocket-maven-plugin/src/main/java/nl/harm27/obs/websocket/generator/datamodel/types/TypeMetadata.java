package nl.harm27.obs.websocket.generator.datamodel.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeMetadata {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
