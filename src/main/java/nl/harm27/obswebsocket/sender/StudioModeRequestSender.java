package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.studiomode.GetPreviewScene;
import nl.harm27.obswebsocket.api.requests.studiomode.GetStudioModeStatus;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the Studio Mode category.
 */
public class StudioModeRequestSender extends RequestSender {
    public StudioModeRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    /**
     * Indicates if Studio Mode is currently enabled.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStudioModeStatus">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void getStudioModeStatus(Consumer<GetStudioModeStatus.Response> responseConsumer) {
        sendRequest(new GetStudioModeStatus.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetStudioModeStatus.Response) baseResponse));
    }

    /**
     * Get the name of the currently previewed scene and its list of sources. Will return an error if Studio Mode is not enabled.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetPreviewScene">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void getPreviewScene(Consumer<GetPreviewScene.Response> responseConsumer) {
        sendRequest(new GetPreviewScene.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetPreviewScene.Response) baseResponse));
    }
}
