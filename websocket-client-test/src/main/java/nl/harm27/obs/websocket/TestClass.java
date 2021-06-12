package nl.harm27.obs.websocket;

import nl.harm27.obs.websocket.api.base.BaseRequest;
import nl.harm27.obs.websocket.api.base.BaseResponse;
import nl.harm27.obs.websocket.api.requests.general.ExecuteBatch;
import nl.harm27.obs.websocket.api.requests.general.GetStats;
import nl.harm27.obs.websocket.api.requests.general.GetVideoInfo;
import nl.harm27.obs.websocket.authentication.AuthenticationResult;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestClass {
    private static final Logger LOGGER = Logger.getLogger(TestClass.class.getName());
    private final OBSWebSocket obsWebSocket;

    private TestClass() {
        obsWebSocket = new OBSWebSocket.Builder().setIp("localhost").setPort(4444).setPassword("test1234").setAuthenticationResultConsumer(this::enable).build();
    }

    public static void main(String[] args) {
        new TestClass();
        LOGGER.info("Press \"ENTER\" to continue...");
        var scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void enable(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful()) {
            var generalRequestSender = obsWebSocket.getRequestSenderManager().getGeneralRequestSender();
            GetVideoInfo.Request getVideoInfo = generalRequestSender.getVideoInfo().batchMessage();
            GetStats.Request getStats = generalRequestSender.getStats().batchMessage();
            List<BaseRequest> requests = Arrays.asList(getVideoInfo, getStats);
            var builder = generalRequestSender.executeBatch();
            builder.setRequests(requests);
            builder.sendMessage(this::batchResponse);
        }
    }

    private void batchResponse(ExecuteBatch.Response response) {
        List<BaseResponse> results = response.getResults();
        for (BaseResponse baseResponse : results) {
            if (LOGGER.isLoggable(Level.INFO))
                LOGGER.info(baseResponse.toString());
        }
    }
}
