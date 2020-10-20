package nl.harm27.obs.websocket.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.harm27.obs.websocket.generator.datamodel.OBSWebSocket;
import nl.harm27.obs.websocket.generator.generators.Generator;
import nl.harm27.obs.websocket.generator.validator.JSONValidator;
import nl.harm27.obs.websocket.generator.validator.ValidationResult;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ObsWebSocketGeneratorMojo extends AbstractMojo {
    /**
     * The url where the json is coming from.
     */
    @Parameter(property = "generate.url", required = true)
    private URL url;

    @Parameter(property = "generate.debugLogging", defaultValue = "false")
    private boolean debugLogging;

    @Parameter(property = "generate.apiPackageName")
    private String apiPackageName;

    @Parameter(property = "generate.listenerPackageName")
    private String listenerPackageName;

    @Parameter(property = "generate.senderPackageName")
    private String senderPackageName;

    @Parameter(property = "generate.targetFolder")
    private File targetFolder;

    @Override
    public void execute() throws MojoFailureException {
        String content = downloadCommentsJson();

        ObjectMapper objectMapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            info("Parsing comments.json file.");
            if (!validateJSON(objectMapper.readTree(content))) {
                throw new MojoFailureException("Failed to parse JSON due to missing or unknown elements.");
            }
            generateClasses(objectMapper.readValue(content, OBSWebSocket.class));
            info("Parsing comments.json file completed.");
        } catch (JsonProcessingException e) {
            throw new MojoFailureException("Failed to parse JSON.", e);
        }
    }

    private void generateClasses(OBSWebSocket obsWebSocket) {
        Generator generator = new Generator(targetFolder, apiPackageName, listenerPackageName, senderPackageName);
        try {
            generator.generateClasses(obsWebSocket);
        } catch (Exception e) {
            getLog().error("Failed to generate classes.", e);
        }
    }

    private boolean validateJSON(JsonNode rootNode) {
        ValidationResult validationResult = new JSONValidator(rootNode, getLog()).validate();
        switch (validationResult) {
            case MISSING_TYPEDEFS:
                getLog().error("Validation failed because the typedefs element is missing.");
                break;
            case MISSING_EVENTS:
                getLog().error("Validation failed because the events element is missing.");
                break;
            case MISSING_REQUESTS:
                getLog().error("Validation failed because the requests element is missing.");
                break;
            case MISSING_EVENT_CATEGORY:
                getLog().error("Validation failed because there is a missing event category.");
                break;
            case MISSING_REQUEST_CATEGORY:
                getLog().error("Validation failed because there is a missing request category.");
                break;
            case UNKNOWN_EVENT_CATEGORY:
                getLog().error("Validation failed because there is a unknown event category.");
                break;
            case UNKNOWN_REQUEST_CATEGORY:
                getLog().error("Validation failed because there is a unknown request category.");
                break;
            default:
                getLog().info("Validation was successful.");
        }
        return validationResult.isValid();
    }

    private String downloadCommentsJson() throws MojoFailureException {
        getLog().debug("Downloading comments.json.");
        String content;
        try (InputStream in = url.openStream()) {
            content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MojoFailureException("Failed to download comments.json.", e);
        }
        info("Completed downloading comments.json.");
        return content;
    }

    private void info(String text) {
        if (debugLogging)
            getLog().info(text);
    }
}
