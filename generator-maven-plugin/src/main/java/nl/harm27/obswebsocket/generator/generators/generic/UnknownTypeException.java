package nl.harm27.obswebsocket.generator.generators.generic;

public class UnknownTypeException extends Exception {
    public UnknownTypeException(String type) {
        super(String.format("Found unknown type: %s", type));
    }
}
