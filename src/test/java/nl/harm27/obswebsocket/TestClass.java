package nl.harm27.obswebsocket;

import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        OBSWebSocket obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");

        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
