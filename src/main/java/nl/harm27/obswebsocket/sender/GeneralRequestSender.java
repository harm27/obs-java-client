package nl.harm27.obswebsocket.sender;

import nl.harm27.obswebsocket.OBSWebSocket;
import nl.harm27.obswebsocket.api.requests.general.*;

import java.util.function.Consumer;

/**
 * The RequestSender for the requests that are part of the General category.
 */
public class GeneralRequestSender extends RequestSender {
    public GeneralRequestSender(OBSWebSocket obsWebSocket) {
        super(obsWebSocket);
    }

    /**
     * Returns the latest version of the plugin and the API.
     *
     * @since v0.3
     */
    public void getVersion(Consumer<GetVersion.Response> responseConsumer) {
        sendRequest(new GetVersion.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetVersion.Response) baseResponse));
    }

    /**
     * Enable/disable sending of the Heartbeat event
     *
     * @param enable Starts/Stops emitting heartbeat messages
     * @since v4.3.0
     */
    public void setHeartbeat(boolean enable, Consumer<SetHeartbeat.Response> responseConsumer) {
        sendRequest(new SetHeartbeat.Request(getNextMessageId(), enable),
                baseResponse -> responseConsumer.accept((SetHeartbeat.Response) baseResponse));
    }

    /**
     * Broadcast custom message to all connected WebSocket clients
     *
     * @param realm Identifier to be choosen by the client
     * @param data  User-defined data
     * @since v4.7.0
     */
    public void broadcastCustomMessage(String realm, Object data, Consumer<BroadcastCustomMessage.Response> responseConsumer) {
        sendRequest(new BroadcastCustomMessage.Request(getNextMessageId(), realm, data),
                baseResponse -> responseConsumer.accept((BroadcastCustomMessage.Response) baseResponse));
    }

    /**
     * Set the filename formatting string
     *
     * @param filenameFormatting Filename formatting string to set.
     * @since v4.3.0
     */
    public void setFilenameFormatting(String filenameFormatting, Consumer<SetFilenameFormatting.Response> responseConsumer) {
        sendRequest(new SetFilenameFormatting.Request(getNextMessageId(), filenameFormatting),
                baseResponse -> responseConsumer.accept((SetFilenameFormatting.Response) baseResponse));
    }

    /**
     * Get the filename formatting string
     *
     * @since v4.3.0
     */
    public void getFilenameFormatting(Consumer<GetFilenameFormatting.Response> responseConsumer) {
        sendRequest(new GetFilenameFormatting.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetFilenameFormatting.Response) baseResponse));
    }

    /**
     * Get OBS stats (almost the same info as provided in OBS' stats window)
     *
     * @since v4.6.0
     */
    public void getStats(Consumer<GetStats.Response> responseConsumer) {
        sendRequest(new GetStats.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetStats.Response) baseResponse));
    }

    /**
     * Get basic OBS video information
     *
     * @since v4.6.0
     */
    public void getVideoInfo(Consumer<GetVideoInfo.Response> responseConsumer) {
        sendRequest(new GetVideoInfo.Request(getNextMessageId()),
                baseResponse -> responseConsumer.accept((GetVideoInfo.Response) baseResponse));
    }

    /**
     * Open a projector window or create a projector on a monitor. Requires OBS v24.0.4 or newer.
     * All parameters for request are optional.
     *
     * @param type     Type of projector
     * @param monitor  Monitor to open the projector on. If -1 or omitted, opens a window.
     * @param geometry Size and position of the projector window (only if monitor is -1).
     *                 Encoded in Base64 using Qt's geometry encoding (https://doc.qt.io/qt-5/qwidget.html#saveGeometry).
     *                 Corresponds to OBS's saved projectors.
     * @param name     Name of the source or scene to be displayed (ignored for other projector types).
     * @since v4.8.0
     */
    public void openProjector(OpenProjector.Type type, Integer monitor, String geometry, String name, Consumer<OpenProjector.Response> responseConsumer) {
        OpenProjector.Request request = new OpenProjector.Request(getNextMessageId());
        if (type != null)
            request.setType(type);
        if (monitor != null)
            request.setMonitor(monitor);
        if (geometry != null)
            request.setGeometry(geometry);
        if (name != null)
            request.setName(name);

        sendRequest(request,
                baseResponse -> responseConsumer.accept((OpenProjector.Response) baseResponse));
    }
}
