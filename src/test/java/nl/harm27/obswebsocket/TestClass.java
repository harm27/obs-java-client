package nl.harm27.obswebsocket;

import nl.harm27.obswebsocket.api.complex.SceneItem;
import nl.harm27.obswebsocket.api.requests.scenes.GetCurrentScene;
import nl.harm27.obswebsocket.api.requests.scenes.ReorderSceneItems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static nl.harm27.obswebsocket.authentication.AuthenticationResult.*;

public class TestClass {
    private static final Object lockingMonitor = new Object();
    private final OBSWebSocket obsWebSocket;
    private boolean completed = false;

    private TestClass() {
        obsWebSocket = new OBSWebSocket("localhost", 4444, "test1234");
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
        obsWebSocket.getScenesRequestSender().getCurrentScene(this::response);
    }

    private void response(GetCurrentScene.Response response) {
        System.out.println("GetCurrentScene 1");
        List<SceneItem> items = response.getSources();
        Collections.reverse(items);
        obsWebSocket.getScenesRequestSender().reorderSceneItems(null, items, this::response);
    }

    private void response(ReorderSceneItems.Response response) {
        System.out.println("ReorderSceneItems 1");
        obsWebSocket.getScenesRequestSender().getCurrentScene(this::response2);
    }

    private void response2(GetCurrentScene.Response response) {
        System.out.println("GetCurrentScene 2");
        List<SceneItem> items = response.getSources();
        Collections.reverse(items);
        obsWebSocket.getScenesRequestSender().reorderSceneItems(null, items, this::response2);
    }

    private void response2(ReorderSceneItems.Response response) {
        System.out.println("ReorderSceneItems 2");
    }
}
