package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

public enum BoundsType {
    @SerializedName("OBS_BOUNDS_STRETCH")
    OBS_BOUNDS_STRETCH,
    @SerializedName("OBS_BOUNDS_SCALE_INNER")
    OBS_BOUNDS_SCALE_INNER,
    @SerializedName("OBS_BOUNDS_SCALE_OUTER")
    OBS_BOUNDS_SCALE_OUTER,
    @SerializedName("OBS_BOUNDS_SCALE_TO_WIDTH")
    OBS_BOUNDS_SCALE_TO_WIDTH,
    @SerializedName("OBS_BOUNDS_SCALE_TO_HEIGHT")
    OBS_BOUNDS_SCALE_TO_HEIGHT,
    @SerializedName("OBS_BOUNDS_MAX_ONLY")
    OBS_BOUNDS_MAX_ONLY,
    @SerializedName("OBS_BOUNDS_NONE")
    OBS_BOUNDS_NONE
}
