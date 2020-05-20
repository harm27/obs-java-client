package nl.harm27.obswebsocket.api.requests.streaming;

import com.google.gson.annotations.SerializedName;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

import java.time.Duration;
import java.util.Optional;

import static nl.harm27.obswebsocket.api.TimeUtil.parseDuration;

/**
 * Get current streaming and recording status.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#GetStreamingStatus">OBS WebSocket Documentation</a>
 * @since v0.3
 */
public class GetStreamingStatus {
    private GetStreamingStatus() {
    }

    public static class Request extends BaseRequest {
        public Request(String messageId) {
            super(RequestType.GET_STREAMING_STATUS, messageId);
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return GetStreamingStatus.class.getSimpleName();
        }
    }

    public static class Response extends BaseResponse {
        @SerializedName("streaming")
        private boolean streaming;
        @SerializedName("recording")
        private boolean recording;
        @SerializedName("stream-timecode")
        private String streamTimecode;
        @SerializedName("rec-timecode")
        private String recordingTimecode;
        @SerializedName("preview-only")
        private boolean previewOnly;

        /**
         * Current streaming status.
         */
        public boolean isStreaming() {
            return streaming;
        }

        /**
         * Current recording status.
         */
        public boolean isRecording() {
            return recording;
        }

        /**
         * Time elapsed between now and stream start (only present if OBS Studio is streaming) as string.
         */
        public Optional<String> getStreamTimecode() {
            return Optional.ofNullable(streamTimecode);
        }

        /**
         * Time elapsed between now and recording start (only present if OBS Studio is recording) as string.
         */
        public Optional<String> getRecordingTimecode() {
            return Optional.ofNullable(recordingTimecode);
        }

        /**
         * Time elapsed between now and stream start (only present if OBS Studio is streaming) as duration.
         */
        public Optional<Duration> getStreamDuration() {
            if (streamTimecode != null)
                return parseDuration(streamTimecode);
            return Optional.empty();
        }

        /**
         * Time elapsed between now and recording start (only present if OBS Studio is recording) as duration.
         */
        public Optional<Duration> getRecordingDuration() {
            if (recordingTimecode != null)
                return parseDuration(recordingTimecode);
            return Optional.empty();
        }

        /**
         * Always false. Retrocompatibility with OBSRemote.
         */
        public boolean isPreviewOnly() {
            return previewOnly;
        }
    }
}