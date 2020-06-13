package nl.harm27.obswebsocket.api.requests.studiomode;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.harm27.obswebsocket.api.requests.BaseRequest;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.api.requests.RequestType;

/**
 * Transitions the currently previewed scene to the main output. Will return an error if Studio Mode is not enabled.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#TransitionToProgram">OBS WebSocket Documentation</a>
 * @since v4.1.0
 */
public class TransitionToProgram {
    private TransitionToProgram() {
    }

    public static class Request extends BaseRequest {
        @JsonProperty("with-transition")
        private Transition withTransition;

        public Request(String messageId) {
            super(RequestType.TRANSITION_TO_PROGRAM, messageId);
        }

        /**
         * Change the active transition before switching scenes. Defaults to the active transition.
         */
        public void setWithTransition(Transition withTransition) {
            this.withTransition = withTransition;
        }

        @Override
        public Class<?> getResponseType() {
            return Response.class;
        }

        @Override
        public String getRequestName() {
            return TransitionToProgram.class.getSimpleName();
        }
    }

    public static class Transition {
        @JsonProperty("name")
        private final String name;
        @JsonProperty("duration")
        private int duration;

        /**
         * @param name Name of the transition.
         */
        public Transition(String name) {
            this.name = name;
        }

        /**
         * @param name     Name of the transition.
         * @param duration Transition duration (in milliseconds).
         */
        public Transition(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }
    }

    public static class Response extends BaseResponse {
    }
}