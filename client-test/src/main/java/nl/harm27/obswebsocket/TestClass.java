package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.requests.studiomode.GetStudioModeStatus;
import nl.harm27.obswebsocket.authentication.AuthenticationResult;

import java.util.Scanner;

public class TestClass {
    private final OBSWebSocket obsWebSocket;

    private TestClass() {
        obsWebSocket = new OBSWebSocket.Builder().setIp("localhost").setPort(4444).setPassword("test1234").setAuthenticationResultConsumer(this::enable).build();
    }

    public static void main(String[] args) {
        new TestClass();
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void enable(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful())
            obsWebSocket.getRequestSenderManager().getStudioModeRequestSender().getStudioModeStatus().sendMessage(this::response);
    }

    private void response(GetStudioModeStatus.Response response) {
        System.out.println("Completed");
    }
}
