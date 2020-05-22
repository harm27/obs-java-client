package nl.harm27.obswebsocket.api.complex;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BoundsType {
    @JsonProperty("OBS_BOUNDS_STRETCH")
    OBS_BOUNDS_STRETCH,
    @JsonProperty("OBS_BOUNDS_SCALE_INNER")
    OBS_BOUNDS_SCALE_INNER,
    @JsonProperty("OBS_BOUNDS_SCALE_OUTER")
    OBS_BOUNDS_SCALE_OUTER,
    @JsonProperty("OBS_BOUNDS_SCALE_TO_WIDTH")
    OBS_BOUNDS_SCALE_TO_WIDTH,
    @JsonProperty("OBS_BOUNDS_SCALE_TO_HEIGHT")
    OBS_BOUNDS_SCALE_TO_HEIGHT,
    @JsonProperty("OBS_BOUNDS_MAX_ONLY")
    OBS_BOUNDS_MAX_ONLY,
    @JsonProperty("OBS_BOUNDS_NONE")
    OBS_BOUNDS_NONE
}
