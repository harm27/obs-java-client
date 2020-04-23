package nl.harm27.obswebsocket.authentication;

public enum AuthenticationResult {
    UN_AVAILABLE,
    AUTHENTICATION_REQUIRED,
    AUTHENTICATION_NOT_REQUIRED,
    AUTHENTICATION_PASSWORD_MISSING,
    AUTHENTICATION_REQUIRED_CHECKING,
    AUTHENTICATION_SUCCESS,
    AUTHENTICATION_FAILED
}