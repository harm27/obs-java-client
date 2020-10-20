package nl.harm27.obs.websocket.processor;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SendingException extends RuntimeException {
    public SendingException(JsonProcessingException e) {
        super(e);
    }
}
