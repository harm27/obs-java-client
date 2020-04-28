package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;

public class RequestSenderManager {
    private final GeneralRequestSender generalRequestSender;
    private final ScenesRequestSender scenesRequestSender;

    public RequestSenderManager(OBSWebSocket obsWebSocket) {
        generalRequestSender = new GeneralRequestSender(obsWebSocket);
        scenesRequestSender = new ScenesRequestSender(obsWebSocket);
    }

    public GeneralRequestSender getGeneralRequestSender() {
        return generalRequestSender;
    }

    public ScenesRequestSender getScenesRequestSender() {
        return scenesRequestSender;
    }
}
