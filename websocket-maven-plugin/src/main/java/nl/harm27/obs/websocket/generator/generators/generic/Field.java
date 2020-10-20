package nl.harm27.obs.websocket.generator.generators.generic;

import com.helger.jcodemodel.AbstractJType;

public class Field {
    private final String name;
    private final String fullName;
    private final String annotationName;
    private final String type;
    private final String description;
    private final boolean optional;
    private final AbstractJType typeClass;

    public Field(String name, String type, String description, boolean optional) {
        this(null, name, name, name, type, description, optional);
    }

    public Field(AbstractJType typeClass, String name, String annotationName, String description) {
        this(typeClass, name, name, annotationName, null, description, false);
    }

    public Field(String name, String annotationName, String type, String description) {
        this(null, name, name, annotationName, type, description, false);
    }

    public Field(String name, String fullName, String type, String description, boolean optional) {
        this(null, name, fullName, name, type, description, optional);
    }

    public Field(AbstractJType typeClass, String name, String fullName, String annotationName, String type, String description, boolean optional) {
        this.typeClass = typeClass;
        this.name = name;
        this.fullName = fullName;
        this.annotationName = annotationName;
        this.type = type;
        this.description = description;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOptional() {
        return optional;
    }

    public String getFullName() {
        return fullName;
    }

    public AbstractJType getTypeClass() {
        return typeClass;
    }

    public String getAnnotationName() {
        return annotationName;
    }
}
