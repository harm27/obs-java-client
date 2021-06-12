package nl.harm27.obs.websocket.processor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import nl.harm27.obs.websocket.ListenerRegistry;
import nl.harm27.obs.websocket.api.base.BaseEvent;
import nl.harm27.obs.websocket.api.base.BaseResponse;
import nl.harm27.obs.websocket.listener.EventListener;
import nl.harm27.obs.websocket.processor.deserializer.BaseResponseDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageReceiver {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final ListenerRegistry listenerRegistry;
    private final Map<String, Consumer<BaseResponse>> messageCallbacks;
    private final ObjectMapper objectMapper;
    private BaseResponseDeserializer baseResponseDeserializer;

    public MessageReceiver(ListenerRegistry listenerRegistry) {
        this.listenerRegistry = listenerRegistry;
        messageCallbacks = new HashMap<>();
        objectMapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
                setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).
                setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                registerModule(getDeserializers());
    }

    private Module getDeserializers() {
        var simpleModule = new SimpleModule();
        baseResponseDeserializer = new BaseResponseDeserializer();
        simpleModule.addDeserializer(BaseResponse.class, baseResponseDeserializer);
        return simpleModule;
    }

    public void receiveMessage(String data) {
        try {
            var jsonNode = objectMapper.readTree(data);
            if (jsonNode.has("message-id"))
                handleResponse(data);
            else if (jsonNode.has("update-type"))
                handleEvent(data);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Failed to parse object", e);
        }
    }

    private void handleEvent(String data) throws JsonProcessingException {
        var baseEvent = objectMapper.readValue(data, BaseEvent.class);
        for (EventListener eventListener : listenerRegistry.getListenersForEventType(baseEvent.getUpdateType())) {
            CompletableFuture.runAsync(() -> eventListener.callEvent(baseEvent));
        }
    }

    private void handleResponse(String data) throws JsonProcessingException {
        var baseResponse = objectMapper.readValue(data, BaseResponse.class);
        Consumer<BaseResponse> callbackConsumer = messageCallbacks.remove(baseResponse.getMessageId());
        CompletableFuture.runAsync(() -> callbackConsumer.accept(baseResponse));
    }

    public void addMessage(String messageId, Class<?> responseType, Consumer<BaseResponse> responseConsumer) {
        baseResponseDeserializer.registerMessageIdClassMapping(messageId, responseType);
        if (responseConsumer != null)
            messageCallbacks.put(messageId, responseConsumer);
    }
}
