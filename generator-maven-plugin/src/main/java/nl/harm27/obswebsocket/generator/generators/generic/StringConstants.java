package nl.harm27.obswebsocket.generator.generators.generic;

import java.util.Arrays;
import java.util.List;

public final class StringConstants {
    public static final String BROADCAST_CUSTOM_MESSAGE_FILTER = "broadcastcustommessage";
    public static final String BASE_REQUEST_GET_RESPONSE_TYPE_METHOD_JAVADOC = "Response type class to map the response to the correct datatype.";
    public static final String BASE_REQUEST_NAME_METHODE_JAVADOC = "Says what the name is of this class.";
    public static final String BASE_REQUEST_AUTHENTICATION_REQUIRED_METHOD = "Says if request needs to wait for authentication.";
    public static final String BASE_BUILDER_SEND_MESSAGE_JAVADOC = "Send the message to the OBS Studio instance.";
    public static final String BASE_EVENT_JAVADOC = "Events are broadcast by the server to each connected client when a recognized action occurs within OBS.";
    public static final String BASE_EVENT_TYPE_JAVADOC = "Enum constants for names of the event types.";
    public static final String BASE_EVENT_STREAM_JAVADOC = "Time elapsed between now and stream start (only present if OBS Studio is streaming) as string.";
    public static final String BASE_EVENT_RECORDING_JAVADOC = "Time elapsed between now and recording start (only present if OBS Studio is recording) as string.";
    public static final String EVENT_TYPE_METHOD_JAVADOC = "The received event.";
    public static final String BASE_REQUEST_JAVADOC = "Requests that are sent by the client.";
    public static final String BASE_REQUEST_TYPE_JAVADOC = "Enum constants for names of the request types.";
    public static final String BASE_REQUEST_MESSAGE_ID_JAVADOC = "Client defined identifier for the message, will be echoed in the response.";
    public static final String BASE_RESPONSE_JAVADOC = "Once a request is sent, the server will return a response.";
    public static final String BASE_RESPONSE_MESSAGE_ID_JAVADOC = "The client defined identifier specified in the request.";
    public static final String BASE_RESPONSE_STATUS_JAVADOC = "Response status, will be one of the following: ok, error";
    public static final String BASE_RESPONSE_ERROR_JAVADOC = "An error message accompanying an error status.";
    public static final String BASE_BUILDER_JAVADOC = "Builders helps creating new requests.";
    public static final String REQUESTS_URL_PART = "Requests";
    public static final String ERROR_FIELD_NAME = "error";
    public static final String SOURCE_TYPE_FIELD = "sourceType";
    public static final String STRING_TYPE = "string";
    public static final String MESSAGE_ID_FIELD = "messageId";
    protected static final List<String> SOURCE_TYPE_VALUES = Arrays.asList("input", "filter", "transition", "scene", "unknown");
    protected static final List<String> BOUNDING_BOX_TYPE_VALUES = Arrays.asList("OBS_BOUNDS_STRETCH", "OBS_BOUNDS_SCALE_INNER", "OBS_BOUNDS_SCALE_OUTER", "OBS_BOUNDS_SCALE_TO_WIDTH", "OBS_BOUNDS_SCALE_TO_HEIGHT", "OBS_BOUNDS_MAX_ONLY", "OBS_BOUNDS_NONE");
    protected static final List<String> PROJECTOR_TYPE_FIELD = Arrays.asList("Preview", "Source", "Scene", "StudioProgram", "Multiview");
    protected static final List<String> MEDIA_STATE_FIELD = Arrays.asList("none", "playing", "opening", "buffering", "paused", "stopped", "ended", ERROR_FIELD_NAME, "unknown");
    protected static final List<String> TEXT_ALIGNMENT_FIELD = Arrays.asList("left", "center", "right");
    protected static final List<String> TEXT_VERTICAL_ALIGNMENT_FIELD = Arrays.asList("top", "center", "bottom");
    protected static final List<String> MOVEMENT_TYPE_FIELD = Arrays.asList("up", "down", "top", "bottom");
    protected static final List<String> MONiTOR_TYPE_FIELD = Arrays.asList("none", "monitorOnly", "monitorAndOutput");
    protected static final List<String> SOURCE_TYPE_CLASSES = Arrays.asList("SourceCreated", "SourceDestroyed", "SourceRenamed", "GetSourceSettings", "SetSourceSettings");

    private StringConstants() {
    }
}
