package nl.harm27.obswebsocket.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Requests are sent by the client.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#requests>OBS WebSocket Documentation</a>
 */
public abstract class BaseRequest {
    @JsonProperty("request-type")
    private final RequestType requestType;

    @JsonProperty("message-id")
    private final String messageId;

    /**
     * @param type      String name of the request type.
     * @param messageId Client defined identifier for the message, will be echoed in the response.
     */
    public BaseRequest(RequestType type, String messageId) {
        this.requestType = type;
        this.messageId = messageId;
    }

    /**
     * Client defined identifier for the message, will be echoed in the response.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Response type class to map the response to the correct datatype.
     */
    public abstract Class<?> getResponseType();

    /**
     * Says if request needs to wait for authentication.
     */
    public boolean isAuthenticationRequired() {
        return true;
    }

    /**
     * Says what the name is of this class.
     */
    public abstract String getRequestName();
}
