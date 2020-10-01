package nl.harm27.obswebsocket.processor;

public class InvalidMethodException extends RuntimeException {
    public InvalidMethodException(String requestName) {
        super(String.format("Request %s is not supported by this WebSocket version.", requestName));
    }
}
