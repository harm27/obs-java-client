package nl.harm27.obswebsocket.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import nl.harm27.obswebsocket.api.events.BaseEvent;
import nl.harm27.obswebsocket.api.events.EventType;
import nl.harm27.obswebsocket.api.requests.BaseResponse;
import nl.harm27.obswebsocket.listener.EventListener;
import nl.harm27.obswebsocket.listener.ListenerRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class MessageReceiver {
    private final ListenerRegistry listenerRegistry;
    private Map<String, Class<?>> messageResponseTypes;
    private Map<String, Consumer<BaseResponse>> messageCallbacks;
    private Gson gson;

    public MessageReceiver(ListenerRegistry listenerRegistry) {
        this.listenerRegistry = listenerRegistry;
        messageResponseTypes = new HashMap<>();
        messageCallbacks = new HashMap<>();
        gson = new GsonBuilder().create();
    }

    public void receiveMessage(String data) {
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        if (jsonObject.has("message-id"))
            handleResponse(jsonObject.get("message-id").getAsString(), data);
        else if (jsonObject.has("update-type"))
            handleEvent(jsonObject.get("update-type").getAsString(), data);
    }

    private void handleEvent(String eventType, String data) {
        EventType foundEventType = getEventTypeFromString(eventType);
        if(foundEventType == null)
            return;

        Class<?> eventClass = listenerRegistry.getClassForEvent(foundEventType);
        if (eventClass == null)
            return;

        BaseEvent baseEvent = (BaseEvent) gson.fromJson(data, eventClass);
        for(EventListener eventListener : listenerRegistry.getListenersForEventType(baseEvent.getEventType())){
            CompletableFuture.runAsync(() -> eventListener.callEvent(baseEvent));
        }
    }

    private EventType getEventTypeFromString(String eventType) {
        String json = gson.toJson(eventType);
        return gson.fromJson(json, EventType.class);
    }

    private void handleResponse(String messageId, String data) {
        Class<?> responseType = messageResponseTypes.remove(messageId);
        BaseResponse baseResponse = (BaseResponse) gson.fromJson(data, responseType);

        Consumer<BaseResponse> callbackConsumer = messageCallbacks.remove(messageId);
        CompletableFuture.runAsync(() -> callbackConsumer.accept(baseResponse));
    }

    public void addMessage(String messageId, Class<?> responseType, Consumer<BaseResponse> responseConsumer) {
        messageResponseTypes.put(messageId, responseType);
        messageCallbacks.put(messageId, responseConsumer);
    }
}
