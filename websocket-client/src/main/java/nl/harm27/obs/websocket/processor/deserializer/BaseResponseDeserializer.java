package nl.harm27.obs.websocket.processor.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import nl.harm27.obs.websocket.api.base.BaseResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseResponseDeserializer extends StdDeserializer<BaseResponse> {
    private final Map<String, Class<?>> messageResponseTypes;

    public BaseResponseDeserializer() {
        super((Class<?>) null);
        messageResponseTypes = new HashMap<>();
    }

    @Override
    public BaseResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode tree = context.readValue(parser, JsonNode.class);
        String messageId = tree.get("message-id").asText();
        Class<?> classForId = messageResponseTypes.remove(messageId);
        if (classForId == null)
            throw new IOException("Missing message-id in message-id to class mapping.");

        JsonParser traverse = tree.traverse();
        traverse.nextToken();
        return (BaseResponse) context.readValue(traverse, classForId);
    }

    public void registerMessageIdClassMapping(String messageId, Class<?> responseType) {
        messageResponseTypes.put(messageId, responseType);
    }
}
