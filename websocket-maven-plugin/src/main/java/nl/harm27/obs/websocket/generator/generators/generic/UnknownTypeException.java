package nl.harm27.obs.websocket.generator.generators.generic;

public class UnknownTypeException extends Exception {
    public UnknownTypeException(String type) {
        super(String.format("Found unknown type: %s", type));
    }
}
