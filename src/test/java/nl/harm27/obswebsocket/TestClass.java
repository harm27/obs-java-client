package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.complex.StreamSettings;
import nl.harm27.obswebsocket.api.events.replaybuffer.ReplayStarting;
import nl.harm27.obswebsocket.api.requests.streaming.SetStreamSettings;
import nl.harm27.obswebsocket.listener.ReplayBufferEventListener;

import java.util.Arrays;
import java.util.Scanner;

import static nl.harm27.obswebsocket.authentication.AuthenticationResult.*;

public class TestClass {
    private static final Object lockingMonitor = new Object();
    private final OBSWebSocket obsWebSocket;

    private TestClass() {
        obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");
        obsWebSocket.registerListener(new ReplayBufferEventListener() {
            @Override
            public void replayStarting(ReplayStarting replayStarting) {
                System.out.println("Replay Starting");
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
        obsWebSocket.getStreamingRequestSender().setStreamSettings("rtmp_common", new StreamSettings(), false, this::response);
    }

    private void response(SetStreamSettings.Response response) {
        System.out.println("Completed");
    }
}
