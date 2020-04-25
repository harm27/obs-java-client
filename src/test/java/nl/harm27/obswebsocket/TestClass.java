package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.events.general.Heartbeat;
import nl.harm27.obswebsocket.api.requests.general.GetVersion;
import nl.harm27.obswebsocket.api.requests.general.SetHeartbeat;
import nl.harm27.obswebsocket.listener.GeneralEventListener;

import java.util.Arrays;
import java.util.Scanner;

import static nl.harm27.obswebsocket.authentication.AuthenticationResult.*;

public class TestClass {
    private static final Object lockingMonitor = new Object();
    private final OBSWebSocket obsWebSocket;

    private TestClass() {
        obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");
        obsWebSocket.registerListener(new GeneralEventListener() {
            @Override
            public void heartbeat(Heartbeat heartbeat) {
                System.out.println(String.format("Test %b", heartbeat.getPulse()));
                obsWebSocket.getGeneralRequestSender().getVersion(this::getVersion);
            }

            private void getVersion(GetVersion.Response response) {
                System.out.println(String.format("OBS Studio: %s Websocket: %s",
                        response.getObsStudioVersion(), response.getObsWebsocketVersion()));
                obsWebSocket.getGeneralRequestSender().setHeartbeat(false, this::disableHeartbeat);
            }

            private void disableHeartbeat(SetHeartbeat.Response response) {
                System.out.println("Heartbeat Disabled");
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        TestClass testClass = new TestClass();
        synchronized (lockingMonitor) {
            while (!testClass.isAuthenticationReady())
                lockingMonitor.wait(200);
        }

        if (testClass.isAuthenticationSuccess())
            testClass.enable();

        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private boolean isAuthenticationSuccess() {
        return AUTHENTICATION_SUCCESS.equals(obsWebSocket.getAuthenticationResult());
    }

    private boolean isAuthenticationReady() {
        return Arrays.asList(AUTHENTICATION_NOT_REQUIRED, AUTHENTICATION_FAILED, AUTHENTICATION_PASSWORD_MISSING,
                AUTHENTICATION_SUCCESS).contains(obsWebSocket.getAuthenticationResult());
    }

    private void enable() {
        obsWebSocket.getGeneralRequestSender().setHeartbeat(true, this::enableHeartbeat);
    }

    private void enableHeartbeat(SetHeartbeat.Response response) {
        System.out.println("Heartbeat Enabled");
    }
}
