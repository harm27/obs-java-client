package nl.harm27.obswebsocket.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("ok")
    OK,
    @JsonProperty("error")
    ERROR
}
