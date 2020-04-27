package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.scenes.SetCurrentScene;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the Scenes category.
 */
public class ScenesRequestSender extends RequestSender {
    public ScenesRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    /**
     * Switch to the specified scene.
     *
     * @param sceneName Name of the scene to switch to.
     * @since v0.3
     */
    public void setCurrentScene(String sceneName, Consumer<SetCurrentScene.Response> responseConsumer) {
        sendRequest(new SetCurrentScene.Request(getNextMessageId(), sceneName),
                baseResponse -> responseConsumer.accept((SetCurrentScene.Response) baseResponse));
    }
}
