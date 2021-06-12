package nl.harm27.obs.websocket.generator.datamodel.shared;

import java.util.*;

public abstract class Definition {
    protected List<ConvertedProperty> convertProperties(List<Property> properties) {
        if (properties == null)
            return new ArrayList<>();

        if (getName().equalsIgnoreCase("ExecuteBatch")) {
            Optional<Property> first = properties.stream().filter(property -> property.getName().equalsIgnoreCase("results") || property.getName().equalsIgnoreCase("requests")).findFirst();
            if (first.isPresent()) {
                Property property = first.get();
                String type = property.getName().equalsIgnoreCase("results") ? "BaseResponse" : "BaseRequest";
                return new ArrayList<>(Collections.singletonList(new ConvertedProperty(property, type)));
            }
        }

        List<ConvertedProperty> convertedProperties = new ArrayList<>();
        for (Property property : properties) {
            String name = property.getName();
            if (name.contains("."))
                updateMultiLevelProperty(convertedProperties, property);
            else
                updateSingleLevelProperty(convertedProperties, property);
        }
        return convertedProperties;
    }

    protected abstract String getName();

    private void updateSingleLevelProperty(List<ConvertedProperty> convertedProperties, Property property) {
        ConvertedProperty convertedProperty = getConvertedProperty(convertedProperties, property.getName());
        configureConvertedProperty(property, convertedProperty);
    }

    private void configureConvertedProperty(Property property, ConvertedProperty convertedProperty) {
        convertedProperty.setArray(property.isArray());
        convertedProperty.setDescription(property.getDescription());
        convertedProperty.setOptional(property.isOptional());

        if (convertedProperty.isSingleLevel())
            convertedProperty.setType(property.getType());
    }

    private ConvertedProperty getConvertedProperty(List<ConvertedProperty> convertedProperties, String propertyName) {
        Optional<ConvertedProperty> exists = convertedProperties.stream().filter(convertedProperty -> convertedProperty.getName().equalsIgnoreCase(propertyName)).findFirst();
        if (exists.isPresent())
            return exists.get();

        ConvertedProperty convertedProperty = new ConvertedProperty(propertyName);
        convertedProperties.add(convertedProperty);
        return convertedProperty;
    }

    private void updateMultiLevelProperty(List<ConvertedProperty> convertedProperties, Property property) {
        List<String> nameParts = Arrays.asList(property.getName().split("\\."));
        createOrUpdateMultiLevelProperty(convertedProperties, nameParts, property);
    }

    private void createOrUpdateMultiLevelProperty(List<ConvertedProperty> convertedProperties, List<String> nameParts, Property property) {
        ConvertedProperty convertedProperty = getConvertedProperty(convertedProperties, nameParts.get(0));
        if (nameParts.size() == 1)
            configureConvertedProperty(property, convertedProperty);
        else
            createOrUpdateMultiLevelProperty(convertedProperty.getProperties(), nameParts.subList(1, nameParts.size()), property);
    }
}
