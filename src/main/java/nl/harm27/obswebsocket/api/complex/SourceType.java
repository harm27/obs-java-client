package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

public enum SourceType {
    @SerializedName("input")
    INPUT,
    @SerializedName("filter")
    FILTER,
    @SerializedName("transition")
    TRANSITION,
    @SerializedName("scene")
    SCENE,
    @SerializedName("unknown")
    UNKNOWN
}
