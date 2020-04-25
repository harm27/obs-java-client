package nl.harm27.obswebsocket.authentication;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.Status;
import nl.harm27.obswebsocket.api.requests.general.Authenticate;
import nl.harm27.obswebsocket.api.requests.general.GetAuthRequired;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import static nl.harm27.obswebsocket.authentication.AuthenticationResult.*;

public class AuthenticationHandler {
    private final OBSWebSocket obsWebSocket;
    private final String password;
    private AuthenticationResult authenticationResult;

    public AuthenticationHandler(OBSWebSocket obsWebSocket, String password) {
        this.obsWebSocket = obsWebSocket;
        this.password = password;
        authenticationResult = UN_AVAILABLE;
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void checkAuthenticationRequired() {
        authenticationResult = AUTHENTICATION_REQUIRED_CHECKING;
        obsWebSocket.sendMessage(new GetAuthRequired.Request(obsWebSocket.getMessageId()), this::processGetAuthRequiredResponse);
    }

    private void processGetAuthRequiredResponse(BaseResponse baseResponse) {
        if (!(baseResponse instanceof GetAuthRequired.Response))
            return;

        GetAuthRequired.Response response = (GetAuthRequired.Response) baseResponse;
        if (!response.isAuthRequired()) {
            authenticationResult = AUTHENTICATION_NOT_REQUIRED;
            return;
        }

        if (password == null) {
            obsWebSocket.notifyShutdown();
            authenticationResult = AUTHENTICATION_PASSWORD_MISSING;
            return;
        }

        authenticationResult = AUTHENTICATION_REQUIRED;

        try {
            Optional<String> challenge = response.getChallenge();
            Optional<String> salt = response.getSalt();
            if (challenge.isPresent() && salt.isPresent())
                sendAuthenticationRequest(challenge.get(), salt.get(), password);
            else
                throw new IllegalStateException("Salt or challenge is missing from OBS Websocket response.");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Needed Algorigthm not found", e);
        }
    }

    private void sendAuthenticationRequest(String challenge, String salt, String password) throws NoSuchAlgorithmException {
        MessageDigest digesterSecretString = MessageDigest.getInstance("SHA-256");
        digesterSecretString.update((password + salt).getBytes(StandardCharsets.UTF_8));
        String secret = Base64.getEncoder().encodeToString(digesterSecretString.digest());

        MessageDigest digesterAuthResponse = MessageDigest.getInstance("SHA-256");
        digesterAuthResponse.update((secret + challenge).getBytes(StandardCharsets.UTF_8));
        String authResponse = Base64.getEncoder().encodeToString(digesterAuthResponse.digest());

        obsWebSocket.sendMessage(new Authenticate.Request(obsWebSocket.getMessageId(), authResponse), this::processAuthenticateResponse);
    }

    private void processAuthenticateResponse(BaseResponse baseResponse) {
        if (!(baseResponse instanceof Authenticate.Response))
            return;

        Authenticate.Response response = (Authenticate.Response) baseResponse;
        if (response.getStatus().equals(Status.OK))
            authenticationResult = AUTHENTICATION_SUCCESS;
        else {
            authenticationResult = AUTHENTICATION_FAILED;
            obsWebSocket.notifyShutdown();
        }
    }
}
