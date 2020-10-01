package nl.harm27.obswebsocket.generator.validator;

public enum ValidationResult {
    MISSING_TYPEDEFS(false),
    MISSING_EVENTS(false),
    MISSING_REQUESTS(false),
    MISSING_EVENT_CATEGORY(false),
    MISSING_REQUEST_CATEGORY(false),
    UNKNOWN_EVENT_CATEGORY(false),
    UNKNOWN_REQUEST_CATEGORY(false),
    VALID(true);

    private final boolean valid;

    ValidationResult(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
