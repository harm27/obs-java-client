package nl.harm27.obs.websocket;

import nl.harm27.obs.websocket.sender.*;

public class RequestSenderManager {
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

    public RequestSenderManager(OBSWebSocket obsWebSocket) {
        generalRequestSender = new GeneralRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        mediaControlRequestSender = new MediaControlRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        outputsRequestSender = new OutputsRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        profilesRequestSender = new ProfilesRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        recordingRequestSender = new RecordingRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        replayBufferRequestSender = new ReplayBufferRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        sceneCollectionsRequestSender = new SceneCollectionsRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        sceneItemsRequestSender = new SceneItemsRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        scenesRequestSender = new ScenesRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        sourcesRequestSender = new SourcesRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        streamingRequestSender = new StreamingRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        studioModeRequestSender = new StudioModeRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
        transitionsRequestSender = new TransitionsRequestSender(obsWebSocket::sendMessage, obsWebSocket::getMessageId);
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
