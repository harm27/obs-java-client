package nl.harm27.obswebsocket.processor;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SendingException extends RuntimeException {
    public SendingException(JsonProcessingException e) {
        super(e);
    }
}
