package nl.harm27.obswebsocket.api.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Response on the requests that are sent by the client.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#requests>OBS WebSocket Documentation</a>
 */
public abstract class BaseResponse {
    @SerializedName("message-id")
    private String messageId;

    private Status status;

    private String error;

    /**
     * The client defined identifier specified in the request.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Response status, will be one of the following: ok, error
     */
    public Status getStatus() {
        return status;
    }

    /**
     * An error message accompanying an error status.
     */
    public String getError() {
        return error;
    }
}
