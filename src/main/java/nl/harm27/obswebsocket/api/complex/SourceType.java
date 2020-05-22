package nl.harm27.obswebsocket.api.complex;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum SourceType {
    @JsonProperty("input")
    INPUT,
    @JsonProperty("filter")
    FILTER,
    @JsonProperty("transition")
    TRANSITION,
    @JsonProperty("scene")
    SCENE,
    @JsonProperty("unknown")
    UNKNOWN
}
