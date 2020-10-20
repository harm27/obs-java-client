package nl.harm27.obs.websocket.authentication;

public enum AuthenticationResult {
    UN_AVAILABLE(false, false),
    AUTHENTICATION_REQUIRED(false, false),
    AUTHENTICATION_NOT_REQUIRED(true, true),
    AUTHENTICATION_PASSWORD_MISSING(false, true),
    AUTHENTICATION_REQUIRED_CHECKING(false, false),
    AUTHENTICATION_SUCCESS(true, true),
    AUTHENTICATION_FAILED(false, true);

    private final boolean successful;
    private final boolean complete;

    AuthenticationResult(boolean successful, boolean complete) {
        this.successful = successful;
        this.complete = complete;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public boolean isComplete() {
        return complete;
    }
}
