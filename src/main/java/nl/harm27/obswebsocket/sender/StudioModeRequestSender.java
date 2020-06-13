package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.studiomode.*;

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

    /**
     * Set the active preview scene. Will return an error if Studio Mode is not enabled.
     *
     * @param sceneName The name of the scene to preview.
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#SetPreviewScene">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void setPreviewScene(String sceneName, Consumer<SetPreviewScene.Response> responseConsumer) {
        sendRequest(new SetPreviewScene.Request(getNextMessageId(), sceneName),
                baseResponse -> responseConsumer.accept((SetPreviewScene.Response) baseResponse));
    }

    /**
     * Transitions the currently previewed scene to the main output. Will return an error if Studio Mode is not enabled.
     *
     * @param transition Change the active transition before switching scenes. Defaults to the active transition.
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#TransitionToProgram">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void transitionToProgram(TransitionToProgram.Transition transition, Consumer<TransitionToProgram.Response> responseConsumer) {
        TransitionToProgram.Request baseRequest = new TransitionToProgram.Request(getNextMessageId());
        if (transition != null)
            baseRequest.setWithTransition(transition);

        sendRequest(baseRequest,
                baseResponse -> responseConsumer.accept((TransitionToProgram.Response) baseResponse));
    }

    /**
     * Enables Studio Mode.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#EnableStudioMode">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void enableStudioMode(Consumer<EnableStudioMode.Response> responseConsumer) {
        sendRequest(new EnableStudioMode.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((EnableStudioMode.Response) baseResponse));
    }

    /**
     * Disables Studio Mode.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#DisableStudioMode">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void disableStudioMode(Consumer<DisableStudioMode.Response> responseConsumer) {
        sendRequest(new DisableStudioMode.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((DisableStudioMode.Response) baseResponse));
    }

    /**
     * Toggles Studio Mode.
     *
     * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#ToggleStudioMode">OBS WebSocket Documentation</a>
     * @since v4.1.0
     */
    public void toggleStudioMode(Consumer<ToggleStudioMode.Response> responseConsumer) {
        sendRequest(new ToggleStudioMode.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((ToggleStudioMode.Response) baseResponse));
    }
}
