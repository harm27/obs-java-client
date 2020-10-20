package nl.harm27.obs.websocket.generator.generators;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.writer.JCMWriter;
import nl.harm27.obs.websocket.generator.datamodel.OBSWebSocket;
import nl.harm27.obs.websocket.generator.generators.events.EventsGenerator;
import nl.harm27.obs.websocket.generator.generators.generic.TypeManager;
import nl.harm27.obs.websocket.generator.generators.generic.UnknownTypeException;
import nl.harm27.obs.websocket.generator.generators.requests.RequestsGenerator;
import nl.harm27.obs.websocket.generator.generators.types.TypesGenerator;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

public class Generator {
    private final String apiPackageName;
    private final String listenerPackageName;
    private final String senderPackageName;
    private final File targetFolder;
    private final TypeManager typeManager;
    private final JCodeModel codeModel;

    public Generator(File targetFolder, String apiPackageName, String listenerPackageName, String senderPackageName) {
        this.apiPackageName = apiPackageName;
        this.listenerPackageName = listenerPackageName;
        this.senderPackageName = senderPackageName;
        this.targetFolder = targetFolder;
        codeModel = new JCodeModel();
        typeManager = new TypeManager(codeModel, codeModel._package(format("%s.constants", apiPackageName)));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void generateClasses(OBSWebSocket obsWebSocket) throws JCodeModelException, IOException, UnknownTypeException {
        TypesGenerator typesGenerator = new TypesGenerator(typeManager, codeModel, apiPackageName);
        typesGenerator.generate(obsWebSocket.getTypeDefinitions());

        EventsGenerator eventsGenerator = new EventsGenerator(codeModel, typeManager, listenerPackageName, apiPackageName);
        eventsGenerator.generate(obsWebSocket.getEventsDefinition());

        RequestsGenerator requestsGenerator = new RequestsGenerator(codeModel, typeManager, senderPackageName, apiPackageName);
        requestsGenerator.generate(obsWebSocket.getRequestsDefinition());

        if (!targetFolder.exists())
            targetFolder.mkdirs();

        new JCMWriter(codeModel).build(targetFolder);
    }
}
