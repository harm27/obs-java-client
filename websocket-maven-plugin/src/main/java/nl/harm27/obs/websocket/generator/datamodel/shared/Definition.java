package nl.harm27.obs.websocket.generator.datamodel.shared;

import java.util.ArrayList;
import java.util.List;

public abstract class Definition {
    protected List<Property> filteredProperties(List<Property> properties) {
        if (properties == null)
            return new ArrayList<>();

        List<Property> filteredProperties = new ArrayList<>();
        for (Property property : properties) {
            if (!containsProperty(property, properties))
                filteredProperties.add(property);
        }
        return filteredProperties;
    }

    private boolean containsProperty(Property property, List<Property> properties) {
        for (Property subProperty : properties) {
            if (subProperty.getName().equalsIgnoreCase(property.getName()))
                continue;

            if (subProperty.getName().startsWith(property.getName()))
                return true;
        }
        return false;
    }
}
