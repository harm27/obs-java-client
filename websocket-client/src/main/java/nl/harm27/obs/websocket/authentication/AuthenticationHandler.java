package nl.harm27.obs.websocket.authentication;

import nl.harm27.obs.websocket.OBSWebSocket;
import nl.harm27.obs.websocket.api.base.BaseResponse;
import nl.harm27.obs.websocket.api.base.Status;
import nl.harm27.obs.websocket.api.requests.general.Authenticate;
import nl.harm27.obs.websocket.api.requests.general.GetAuthRequired;
import nl.harm27.obs.websocket.sender.GeneralRequestSender;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static nl.harm27.obs.websocket.authentication.AuthenticationResult.*;

public class AuthenticationHandler {
    private final OBSWebSocket obsWebSocket;
    private final String password;
    private final List<Consumer<AuthenticationResult>> authenticationResultConsumers;
    private GeneralRequestSender generalRequestSender;
    private AuthenticationResult authenticationResult;

    public AuthenticationHandler(OBSWebSocket obsWebSocket, String password) {
        this.obsWebSocket = obsWebSocket;
        this.password = password;
        this.authenticationResult = UN_AVAILABLE;
        authenticationResultConsumers = new ArrayList<>();
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    private void setAuthenticationResult(AuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
        if (authenticationResult.isComplete())
            authenticationResultConsumers.forEach(consumer -> CompletableFuture.runAsync(() -> consumer.accept(authenticationResult)));
    }

    public void addAuthenticationResultConsumer(Consumer<AuthenticationResult> authenticationResultConsumer) {
        authenticationResultConsumers.add(authenticationResultConsumer);
    }

    public void checkAuthenticationRequired() {
        setAuthenticationResult(AUTHENTICATION_REQUIRED_CHECKING);
        generalRequestSender.getAuthRequired().sendMessage(this::processGetAuthRequiredResponse);
    }

    private void processGetAuthRequiredResponse(BaseResponse baseResponse) {
        if (!(baseResponse instanceof GetAuthRequired.Response))
            return;

        var response = (GetAuthRequired.Response) baseResponse;
        if (!response.isAuthRequired()) {
            setAuthenticationResult(AUTHENTICATION_NOT_REQUIRED);
            return;
        }

        if (password == null) {
            obsWebSocket.notifyShutdown();
            setAuthenticationResult(AUTHENTICATION_PASSWORD_MISSING);
            return;
        }

        setAuthenticationResult(AUTHENTICATION_REQUIRED);

        try {
            Optional<String> challenge = response.getChallenge();
            Optional<String> salt = response.getSalt();
            if (challenge.isPresent() && salt.isPresent())
                sendAuthenticationRequest(challenge.get(), salt.get(), password);
            else
                throw new IllegalStateException("Salt or challenge is missing from OBS Websocket response.");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Needed Algorithm not found", e);
        }
    }

    private void sendAuthenticationRequest(String challenge, String salt, String password) throws NoSuchAlgorithmException {
        var digesterSecretString = MessageDigest.getInstance("SHA-256");
        digesterSecretString.update((password + salt).getBytes(StandardCharsets.UTF_8));
        var secret = Base64.getEncoder().encodeToString(digesterSecretString.digest());

        var digesterAuthResponse = MessageDigest.getInstance("SHA-256");
        digesterAuthResponse.update((secret + challenge).getBytes(StandardCharsets.UTF_8));
        var authResponse = Base64.getEncoder().encodeToString(digesterAuthResponse.digest());

        var builder = generalRequestSender.authenticate();
        builder.setAuth(authResponse);
        builder.sendMessage(this::processAuthenticateResponse);
    }

    private void processAuthenticateResponse(BaseResponse baseResponse) {
        if (!(baseResponse instanceof Authenticate.Response))
            return;

        var response = (Authenticate.Response) baseResponse;
        if (Status.OK.equals(response.getStatus()))
            setAuthenticationResult(AUTHENTICATION_SUCCESS);
        else {
            setAuthenticationResult(AUTHENTICATION_FAILED);
            obsWebSocket.notifyShutdown();
        }
    }

    public void setGeneralRequestSender(GeneralRequestSender generalRequestSender) {
        this.generalRequestSender = generalRequestSender;
    }
}
