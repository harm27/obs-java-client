package nl.harm27.obswebsocket.api.requests.general;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.complex.OBSStats;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Get OBS stats (almost the same info as provided in OBS' stats window)
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStats">OBS WebSocket Documentation</a>
 * @since v4.6.0
 */
public class GetStats {
    private GetStats() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_STATS, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetStats.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("stats")
        private OBSStats stats;

        /**
         * OBS stats
         */
        public OBSStats getStats() {
            return stats;
        }
    }
}