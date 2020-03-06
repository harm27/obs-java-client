package nl.harm27.obswebsocket.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import nl.harm27.obswebsocket.api.requests.BaseResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class MessageReceiver {
    private Map<String, Class<?>> messageResponseTypes;
    private Map<String, Consumer<BaseResponse>> messageCallbacks;
    private Gson gson;

    public MessageReceiver() {
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
