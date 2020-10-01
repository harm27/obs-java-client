package nl.harm27.obswebsocket.generator.datamodel.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;

    public String getType() {
        return type.replace(" (optional)", "").replace(" (Optional)", "");
    }

    public boolean isOptional() {
        return type.contains("(optional)") || type.contains("(Optional)");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
