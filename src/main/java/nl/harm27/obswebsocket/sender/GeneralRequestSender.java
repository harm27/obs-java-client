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
}
