package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.events.scenes.ScenesChanged;
import nl.harm27.obswebsocket.listener.ScenesEventListener;

import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        OBSWebSocket obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");
        obsWebSocket.registerListener(new ScenesEventListener() {
            @Override
            public void scenesChanged(ScenesChanged scenesChanged) {
                System.out.println("Test");
            }
        });
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
