package nl.harm27.obs.websocket;

import nl.harm27.obs.websocket.api.base.BaseRequest;
import nl.harm27.obs.websocket.api.base.BaseResponse;
import nl.harm27.obs.websocket.authentication.AuthenticationHandler;
import nl.harm27.obs.websocket.processor.MessageReceiver;
import nl.harm27.obs.websocket.processor.MessageSender;
import nl.harm27.obs.websocket.sender.*;
import nl.harm27.obs.websocket.websocket.OBSWebSocketClient;

import java.util.function.Consumer;

public class RequestSenderManager {
    private final MessageSender messageSender;
    private final MessageReceiver messageReceiver;

    private final GeneralRequestSender generalRequestSender;
    private final MediaControlRequestSender mediaControlRequestSender;
    private final OutputsRequestSender outputsRequestSender;
    private final ProfilesRequestSender profilesRequestSender;
    private final RecordingRequestSender recordingRequestSender;
    private final ReplayBufferRequestSender replayBufferRequestSender;
    private final SceneCollectionsRequestSender sceneCollectionsRequestSender;
    private final SceneItemsRequestSender sceneItemsRequestSender;
    private final ScenesRequestSender scenesRequestSender;
    private final SourcesRequestSender sourcesRequestSender;
    private final StreamingRequestSender streamingRequestSender;
    private final StudioModeRequestSender studioModeRequestSender;
    private final TransitionsRequestSender transitionsRequestSender;

    private int lastMessageId = 0;

    public RequestSenderManager(OBSWebSocketClient obsWebSocketClient, AuthenticationHandler authenticationHandler, ListenerRegistry listenerRegistry) {
        generalRequestSender = new GeneralRequestSender(this::sendMessage, this::getMessageId);
        mediaControlRequestSender = new MediaControlRequestSender(this::sendMessage, this::getMessageId);
        outputsRequestSender = new OutputsRequestSender(this::sendMessage, this::getMessageId);
        profilesRequestSender = new ProfilesRequestSender(this::sendMessage, this::getMessageId);
        recordingRequestSender = new RecordingRequestSender(this::sendMessage, this::getMessageId);
        replayBufferRequestSender = new ReplayBufferRequestSender(this::sendMessage, this::getMessageId);
        sceneCollectionsRequestSender = new SceneCollectionsRequestSender(this::sendMessage, this::getMessageId);
        sceneItemsRequestSender = new SceneItemsRequestSender(this::sendMessage, this::getMessageId);
        scenesRequestSender = new ScenesRequestSender(this::sendMessage, this::getMessageId);
        sourcesRequestSender = new SourcesRequestSender(this::sendMessage, this::getMessageId);
        streamingRequestSender = new StreamingRequestSender(this::sendMessage, this::getMessageId);
        studioModeRequestSender = new StudioModeRequestSender(this::sendMessage, this::getMessageId);
        transitionsRequestSender = new TransitionsRequestSender(this::sendMessage, this::getMessageId);
        messageSender = new MessageSender(this, obsWebSocketClient, authenticationHandler);
        messageReceiver = new MessageReceiver(listenerRegistry);
        obsWebSocketClient.connect(messageSender, messageReceiver);
    }

    private synchronized String getMessageId() {
        lastMessageId++;
        return String.valueOf(lastMessageId);
    }

    private void sendMessage(BaseRequest request, Consumer<BaseResponse> responseConsumer) {
        messageReceiver.addMessage(request.getMessageId(), request.getResponseType(), responseConsumer);
        messageSender.sendMessage(request);
    }

    public GeneralRequestSender getGeneralRequestSender() {
        return generalRequestSender;
    }

    public MediaControlRequestSender getMediaControlRequestSender() {
        return mediaControlRequestSender;
    }

    public OutputsRequestSender getOutputsRequestSender() {
        return outputsRequestSender;
    }

    public ProfilesRequestSender getProfilesRequestSender() {
        return profilesRequestSender;
    }

    public RecordingRequestSender getRecordingRequestSender() {
        return recordingRequestSender;
    }

    public ReplayBufferRequestSender getReplayBufferRequestSender() {
        return replayBufferRequestSender;
    }

    public SceneCollectionsRequestSender getSceneCollectionsRequestSender() {
        return sceneCollectionsRequestSender;
    }

    public SceneItemsRequestSender getSceneItemsRequestSender() {
        return sceneItemsRequestSender;
    }

    public ScenesRequestSender getScenesRequestSender() {
        return scenesRequestSender;
    }

    public SourcesRequestSender getSourcesRequestSender() {
        return sourcesRequestSender;
    }

    public StreamingRequestSender getStreamingRequestSender() {
        return streamingRequestSender;
    }

    public StudioModeRequestSender getStudioModeRequestSender() {
        return studioModeRequestSender;
    }

    public TransitionsRequestSender getTransitionsRequestSender() {
        return transitionsRequestSender;
    }
}
