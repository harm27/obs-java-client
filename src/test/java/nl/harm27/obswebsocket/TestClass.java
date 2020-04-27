package nl.harm27.obswebsocket;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.requests.general.BroadcastCustomMessage;
import nl.harm27.obswebsocket.listener.GeneralEventListener;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static nl.harm27.obswebsocket.authentication.AuthenticationResult.*;

public class TestClass {
    private static final Object lockingMonitor = new Object();
    private final OBSWebSocket obsWebSocket;

    private TestClass() {
        obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");
        obsWebSocket.registerListener(new GeneralEventListener() {
            @Override
            public void broadcastCustomMessage(nl.harm27.obswebsocket.api.events.general.BroadcastCustomMessage broadcastCustomMessage) {
                System.out.println(broadcastCustomMessage.getRealm());
                for (Map.Entry<String, Object> entry : broadcastCustomMessage.getData().entrySet())
                    System.out.println(String.format("%s:%s", entry.getKey(), entry.getValue()));
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
        obsWebSocket.getGeneralRequestSender().broadcastCustomMessage("dummy", new TestData("dummy"), this::broadcasted);
    }

    private void broadcasted(BroadcastCustomMessage.Response response) {
        System.out.println("Broadcasted");
    }

    public static class TestData {
        @SerializedName("data")
        private final String data;

        public TestData(String data) {
            this.data = data;
        }
    }
}
