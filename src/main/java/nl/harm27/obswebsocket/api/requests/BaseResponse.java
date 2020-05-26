package nl.harm27.obswebsocket.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response on the requests that are sent by the client.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#requests>OBS WebSocket Documentation</a>
 */
public abstract class BaseResponse {
    @JsonProperty("message-id")
    private String messageId;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("error")
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
