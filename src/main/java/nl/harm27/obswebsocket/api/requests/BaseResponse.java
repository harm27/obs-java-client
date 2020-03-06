package nl.harm27.obswebsocket.api.requests;

import com.google.gson.annotations.SerializedName;

public abstract class BaseResponse {
    @SerializedName("message-id")
    private String messageId;

    private Status status;

    private String error;

    public String getMessageId() {
        return messageId;
    }

    public Status getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
