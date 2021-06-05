package nl.harm27.obs.websocket.generator.validator;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static nl.harm27.obs.websocket.generator.validator.ValidationResult.*;

public class JSONValidator {
    private static final List<String> eventCategories =
            Arrays.asList("scenes", "transitions", "profiles", "streaming", "recording", "replay buffer", "other", "general", "sources", "media", "scene items", "studio mode", "virtual cam");
    private static final List<String> requestCategories =
            Arrays.asList("general", "media control", "sources", "outputs", "profiles", "recording", "replay buffer", "scene collections", "scene items", "scenes", "streaming", "studio mode", "transitions", "virtual cam");
    private final JsonNode rootNode;
    private final Log log;

    public JSONValidator(JsonNode rootNode, Log log) {
        this.rootNode = rootNode;
        this.log = log;
    }

    public ValidationResult validate() {
        ValidationResult validationResult = validateRootElements();
        if (!validationResult.isValid())
            return validationResult;

        validationResult = validateEvents();
        if (!validationResult.isValid())
            return validationResult;

        return validateRequests();
    }

    private ValidationResult validateRootElements() {
        if (!rootNode.has("typedefs"))
            return MISSING_TYPEDEFS;
        else if (!rootNode.has("events"))
            return MISSING_EVENTS;
        else if (!rootNode.has("requests"))
            return MISSING_REQUESTS;
        else
            return VALID;
    }

    private ValidationResult validateEvents() {
        List<String> categoriesFromJson = getCategories(rootNode.get("events"));

        List<String> categoryNotInJson = eventCategories.stream().
                filter(category -> categoriesFromJson.stream().noneMatch(category::equalsIgnoreCase)).
                collect(Collectors.toList());
        if (!categoryNotInJson.isEmpty()) {
            log.error(String.format("Missing event categories: %s", String.join(",", categoryNotInJson)));
            return MISSING_EVENT_CATEGORY;
        }

        List<String> categoryInJson = categoriesFromJson.stream().
                filter(category -> eventCategories.stream().noneMatch(category::equalsIgnoreCase)).
                collect(Collectors.toList());
        if (!categoryInJson.isEmpty()) {
            log.error(String.format("Unknown event categories: %s", String.join(",", categoryInJson)));
            return UNKNOWN_EVENT_CATEGORY;
        }

        return VALID;
    }

    private ValidationResult validateRequests() {
        List<String> categoriesFromJson = getCategories(rootNode.get("requests"));

        List<String> categoryNotInJson = requestCategories.stream().
                filter(category -> categoriesFromJson.stream().noneMatch(category::equalsIgnoreCase)).
                collect(Collectors.toList());
        if (!categoryNotInJson.isEmpty()) {
            log.error(String.format("Missing request categories: %s", String.join(",", categoryNotInJson)));
            return MISSING_REQUEST_CATEGORY;
        }

        List<String> categoryInJson = categoriesFromJson.stream().
                filter(category -> requestCategories.stream().noneMatch(category::equalsIgnoreCase)).
                collect(Collectors.toList());
        if (!categoryInJson.isEmpty()) {
            log.error(String.format("Unknown request categories: %s", String.join(",", categoryInJson)));
            return UNKNOWN_REQUEST_CATEGORY;
        }

        return VALID;
    }

    private List<String> getCategories(JsonNode node) {
        Iterator<String> iterator = node.fieldNames();
        List<String> categories = new ArrayList<>();
        while (iterator.hasNext())
            categories.add(iterator.next());
        return categories;
    }
}
