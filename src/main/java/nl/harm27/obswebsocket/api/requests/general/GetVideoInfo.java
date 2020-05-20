package nl.harm27.obswebsocket.api.requests.general;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Get basic OBS video information
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetVideoInfo">OBS WebSocket Documentation</a>
 * @since v4.6.0
 */
public class GetVideoInfo {
    private GetVideoInfo() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_VIDEO_INFO, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetVideoInfo.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("baseWidth")
        private int baseWidth;
        @SerializedName("baseHeight")
        private int baseHeight;
        @SerializedName("outputWidth")
        private int outputWidth;
        @SerializedName("outputHeight")
        private int outputHeight;
        @SerializedName("scaleType")
        private String scaleType;
        @SerializedName("fps")
        private double fps;
        @SerializedName("videoFormat")
        private String videoFormat;
        @SerializedName("colorSpace")
        private String colorSpace;
        @SerializedName("colorRange")
        private String colorRange;

        /**
         * Base (canvas) width
         */
        public int getBaseWidth() {
            return baseWidth;
        }

        /**
         * Base (canvas) height
         */
        public int getBaseHeight() {
            return baseHeight;
        }

        /**
         * Output width
         */
        public int getOutputWidth() {
            return outputWidth;
        }

        /**
         * Output height
         */
        public int getOutputHeight() {
            return outputHeight;
        }

        /**
         * Scaling method used if output size differs from base size
         */
        public String getScaleType() {
            return scaleType;
        }

        /**
         * Frames rendered per second
         */
        public double getFps() {
            return fps;
        }

        /**
         * Video color format
         */
        public String getVideoFormat() {
            return videoFormat;
        }

        /**
         * Color space for YUV
         */
        public String getColorSpace() {
            return colorSpace;
        }

        /**
         * Color range (full or partial)
         */
        public String getColorRange() {
            return colorRange;
        }
    }
}