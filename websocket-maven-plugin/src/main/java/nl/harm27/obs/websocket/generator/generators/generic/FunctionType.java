package nl.harm27.obs.websocket.generator.generators.generic;

public enum FunctionType {
    GETTER(false, true),
    SETTER(true, false),
    BOTH(true, true);

    private final boolean setter;
    private final boolean getter;

    FunctionType(boolean setter, boolean getter) {
        this.setter = setter;
        this.getter = getter;
    }

    public boolean hasSetter() {
        return setter;
    }

    public boolean hasGetter() {
        return getter;
    }
}
