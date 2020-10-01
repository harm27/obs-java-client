package nl.harm27.obswebsocket.authentication;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.base.BaseResponse;
import nl.harm27.obswebsocket.api.base.Status;
import nl.harm27.obswebsocket.api.requests.general.Authenticate;
import nl.harm27.obswebsocket.api.requests.general.GetAuthRequired;
import nl.harm27.obswebsocket.sender.GeneralRequestSender;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static nl.harm27.obswebsocket.authentication.AuthenticationResult.*;

public class AuthenticationHandler {
    private final OBSWebSocket obsWebSocket;
    private final String password;
    private final List<Consumer<AuthenticationResult>> authenticationResultConsumers;
    private final GeneralRequestSender generalRequestSender;
    private AuthenticationResult authenticationResult;

    public AuthenticationHandler(OBSWebSocket obsWebSocket, String password) {
        this.obsWebSocket = obsWebSocket;
        this.generalRequestSender = obsWebSocket.getRequestSenderManager().getGeneralRequestSender();
        this.password = password;
        this.authenticationResult = UN_AVAILABLE;
        authenticationResultConsumers = new ArrayList<>();
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void addAuthenticationResultConsumer(Consumer<AuthenticationResult> authenticationResultConsumer) {
        authenticationResultConsumers.add(authenticationResultConsumer);
    }

    private void setAuthenticationResult(AuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
        if (authenticationResult.isComplete())
            authenticationResultConsumers.forEach(consumer -> CompletableFuture.runAsync(() -> consumer.accept(authenticationResult)));
    }

    public void checkAuthenticationRequired() {
        setAuthenticationResult(AUTHENTICATION_REQUIRED_CHECKING);
        generalRequestSender.getAuthRequired().sendMessage(this::processGetAuthRequiredResponse);
    }

    private void processGetAuthRequiredResponse(BaseResponse baseResponse) {
        if (!(baseResponse instanceof GetAuthRequired.Response))
            return;

        GetAuthRequired.Response response = (GetAuthRequired.Response) baseResponse;
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
        MessageDigest digesterSecretString = MessageDigest.getInstance("SHA-256");
        digesterSecretString.update((password + salt).getBytes(StandardCharsets.UTF_8));
        String secret = Base64.getEncoder().encodeToString(digesterSecretString.digest());

        MessageDigest digesterAuthResponse = MessageDigest.getInstance("SHA-256");
        digesterAuthResponse.update((secret + challenge).getBytes(StandardCharsets.UTF_8));
        String authResponse = Base64.getEncoder().encodeToString(digesterAuthResponse.digest());

        Authenticate.Builder builder = generalRequestSender.authenticate();
        builder.setAuth(authResponse);
        builder.sendMessage(this::processAuthenticateResponse);
    }

    private void processAuthenticateResponse(BaseResponse baseResponse) {
        if (!(baseResponse instanceof Authenticate.Response))
            return;

        Authenticate.Response response = (Authenticate.Response) baseResponse;
        if (Status.OK.equals(response.getStatus()))
            setAuthenticationResult(AUTHENTICATION_SUCCESS);
        else {
            setAuthenticationResult(AUTHENTICATION_FAILED);
            obsWebSocket.notifyShutdown();
        }
    }
}
