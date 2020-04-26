package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;

public class RequestSenderManager {
    private final GeneralRequestSender generalRequestSender;

    public RequestSenderManager(OBSWebSocket obsWebSocket) {
        generalRequestSender = new GeneralRequestSender(obsWebSocket);
    }

    public GeneralRequestSender getGeneralRequestSender() {
        return generalRequestSender;
    }
}
