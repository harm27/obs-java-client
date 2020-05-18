package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.complex.StreamSettings;
import nl.harm27.obswebsocket.api.events.replaybuffer.ReplayStarting;
import nl.harm27.obswebsocket.api.requests.streaming.SetStreamSettings;
import nl.harm27.obswebsocket.authentication.AuthenticationResult;
import nl.harm27.obswebsocket.listener.ReplayBufferEventListener;

import java.util.Scanner;

public class TestClass {
    private final OBSWebSocket obsWebSocket;

    private TestClass() {
        obsWebSocket = new OBSWebSocket.Builder().setIp("localhost").setPort(4444).setPassword("test1234").setAuthenticationResultConsumer(this::enable).build();
        obsWebSocket.registerListener(new ReplayBufferEventListener() {
            @Override
            public void replayStarting(ReplayStarting replayStarting) {
                System.out.println("Replay Starting");
            }
        });
    }

    public static void main(String[] args) {
        new TestClass();
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void enable(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful())
            obsWebSocket.getStreamingRequestSender().setStreamSettings("rtmp_common", new StreamSettings(), false, this::response);
    }

    private void response(SetStreamSettings.Response response) {
        System.out.println("Completed");
    }
}
