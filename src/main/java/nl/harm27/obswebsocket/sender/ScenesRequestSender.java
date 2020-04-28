package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.complex.SceneItem;
import nl.harm27.obswebsocket.api.requests.scenes.GetCurrentScene;
import nl.harm27.obswebsocket.api.requests.scenes.GetSceneList;
import nl.harm27.obswebsocket.api.requests.scenes.ReorderSceneItems;
import nl.harm27.obswebsocket.api.requests.scenes.SetCurrentScene;

import java.util.List;
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
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetCurrentScene">OBS WebSocket Documentation</a>
     * @since v0.3
     */
    public void setCurrentScene(String sceneName, Consumer<SetCurrentScene.Response> responseConsumer) {
        sendRequest(new SetCurrentScene.Request(getNextMessageId(), sceneName),
                baseResponse -> responseConsumer.accept((SetCurrentScene.Response) baseResponse));
    }

    /**
     * Get the current scene's name and source items.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetCurrentScene">OBS WebSocket Documentation</a>
     * @since v0.3
     */
    public void getCurrentScene(Consumer<GetCurrentScene.Response> responseConsumer) {
        sendRequest(new GetCurrentScene.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetCurrentScene.Response) baseResponse));
    }

    /**
     * Get a list of scenes in the currently active profile.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetSceneList">OBS WebSocket Documentation</a>
     * @since v0.3
     */
    public void getSceneList(Consumer<GetSceneList.Response> responseConsumer) {
        sendRequest(new GetSceneList.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetSceneList.Response) baseResponse));
    }

    /**
     * Changes the order of scene items in the requested scene.
     *
     * @param scene Name of the scene to reorder (defaults to current).
     * @param items Ordered list of objects with name and/or id specified. Id preferred due to uniqueness per scene.
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#ReorderSceneItems">OBS WebSocket Documentation</a>
     * @since v4.5.0
     */
    public void reorderSceneItems(String scene, List<SceneItem> items, Consumer<ReorderSceneItems.Response> responseConsumer) {
        ReorderSceneItems.Request request = new ReorderSceneItems.Request(getNextMessageId(), items);
        if (scene != null)
            request.setScene(scene);

        sendRequest(request,
                baseResponse -> responseConsumer.accept((ReorderSceneItems.Response) baseResponse));
    }
}
