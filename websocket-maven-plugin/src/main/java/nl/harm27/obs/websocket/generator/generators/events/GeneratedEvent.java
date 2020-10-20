package nl.harm27.obs.websocket.generator.generators.events;

import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMethod;
import nl.harm27.obs.websocket.generator.datamodel.events.Event;

public class GeneratedEvent {
    private final Event event;
    private final JDefinedClass eventClass;
    private JMethod eventListenerMethod;

    public GeneratedEvent(Event event, JDefinedClass eventClass) {
        this.event = event;
        this.eventClass = eventClass;
    }

    public void setMethod(JMethod eventListenerMethod) {
        this.eventListenerMethod = eventListenerMethod;
    }

    public Event getEvent() {
        return event;
    }

    public JDefinedClass getEventClass() {
        return eventClass;
    }

    public String getName() {
        return event.getName();
    }

    public JMethod getEventListenerMethod() {
        return eventListenerMethod;
    }

    public IJExpression getDotClass() {
        return eventClass.dotclass();
    }

    public String getDescription() {
        return event.getDescription();
    }

    public String getSince() {
        return event.getSince();
    }

    public String getDeprecated() {
        return event.getDeprecated();
    }
}
