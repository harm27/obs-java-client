package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.events.other.Exiting;
import nl.harm27.obswebsocket.listener.GeneralEventListener;

import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        OBSWebSocket obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");
        obsWebSocket.registerListener(new GeneralEventListener() {
            @Override
            public void exiting(Exiting exiting) {
                System.out.println("Test");
            }
        });
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
