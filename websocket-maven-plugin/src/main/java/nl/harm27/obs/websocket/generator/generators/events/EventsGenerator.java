package nl.harm27.obs.websocket.generator.generators.events;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JPackage;
import nl.harm27.obs.websocket.generator.datamodel.events.Event;
import nl.harm27.obs.websocket.generator.datamodel.events.EventsDefinition;
import nl.harm27.obs.websocket.generator.generators.generic.TypeManager;
import nl.harm27.obs.websocket.generator.generators.generic.UnknownTypeException;

import java.util.List;
import java.util.Map;

public class EventsGenerator {
    private final JPackage listenerPackageModel;
    private final JPackage eventsPackageModel;
    private final JPackage basePackageModel;
    private final TypeManager typeManager;

    public EventsGenerator(JCodeModel codeModel, TypeManager typeManager, String listenerPackageName, String apiPackageName) {
        this.typeManager = typeManager;
        listenerPackageModel = codeModel._package(listenerPackageName);
        eventsPackageModel = codeModel._package(String.format("%s.events", apiPackageName));
        basePackageModel = codeModel._package(String.format("%s.base", apiPackageName));
    }

    public void generate(EventsDefinition eventsDefinition) throws JCodeModelException, UnknownTypeException {
        var eventsBaseGenerator = new EventsBaseGenerator(basePackageModel, listenerPackageModel, typeManager, eventsDefinition.getEventNames());
        eventsBaseGenerator.generate();

        for (Map.Entry<String, List<Event>> eventCategory : eventsDefinition.getEventsMap().entrySet()) {
            var eventCategoryGenerator = new EventCategoryGenerator(listenerPackageModel, eventsPackageModel, eventsBaseGenerator, typeManager, eventCategory.getKey(), eventCategory.getValue());
            eventCategoryGenerator.generate();
        }
    }

}
