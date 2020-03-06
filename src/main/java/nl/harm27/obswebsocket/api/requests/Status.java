package nl.harm27.obswebsocket.api.requests;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("ok")
    OK,
    @SerializedName("error")
    ERROR
}
