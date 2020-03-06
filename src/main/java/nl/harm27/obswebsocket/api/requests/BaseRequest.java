package nl.harm27.obswebsocket.api.requests;

import com.google.gson.annotations.SerializedName;

public abstract class BaseRequest {
    @SerializedName("request-type")
    private final RequestType requestType;

    @SerializedName("message-id")
    private final String messageId;

    public BaseRequest(RequestType type, String messageId) {
        this.requestType = type;
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public abstract Class<?> getResponseType();
}
