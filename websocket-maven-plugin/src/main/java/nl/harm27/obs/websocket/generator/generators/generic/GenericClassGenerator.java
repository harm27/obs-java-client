package nl.harm27.obs.websocket.generator.generators.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helger.jcodemodel.*;
import nl.harm27.obs.websocket.generator.datamodel.shared.Property;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static nl.harm27.obs.websocket.generator.generators.generic.StringUtil.*;

public abstract class GenericClassGenerator extends GenericGenerator {
    protected final TypeManager typeManager;

    protected GenericClassGenerator(TypeManager typeManager) {
        this.typeManager = typeManager;
    }

    protected void generateProperty(JDefinedClass targetClass, Property property, FunctionType functionType) throws JCodeModelException, UnknownTypeException {
        List<String> propertyPath = Arrays.stream(property.getName().split("\\.")).filter(element -> !element.equalsIgnoreCase("*")).collect(Collectors.toList());
        if (propertyPath.size() > 1) {
            generateSubClassForProperty(targetClass, propertyPath, property, functionType);
        } else {
            generateFieldForProperty(targetClass, functionType, new Field(property.getName(), property.getType(), property.getDescription(), property.isOptional()));
        }
    }

    protected JFieldVar generateFieldForProperty(JDefinedClass targetClass, FunctionType functionType, Field field) throws JCodeModelException, UnknownTypeException {
        AbstractJType fieldType;
        AbstractJType typeClass = field.getTypeClass();
        if (typeClass != null)
            fieldType = typeClass;
        else
            fieldType = typeManager.getType(targetClass, field.getFullName(), field.getType(), field.getDescription());

        JFieldVar fieldVar = targetClass.field(JMod.PRIVATE, fieldType, generateValidFieldName(field.getName()));
        fieldVar.annotate(JsonProperty.class).param(field.getAnnotationName());

        if (functionType.hasGetter()) {
            JMethod getterMethod = generateGetter(targetClass, fieldType, fieldVar, field);
            generateAdditionalGetters(targetClass, getterMethod);
        }

        if (functionType.hasSetter())
            generateSetter(targetClass, fieldType, fieldVar, field);

        return fieldVar;
    }

    private void generateAdditionalGetters(JDefinedClass targetClass, JMethod getterMethod) {
        AbstractJClass matchClass = findParentClass(targetClass);
        if (matchClass == null)
            return;

        if ("GetVersion".equalsIgnoreCase(matchClass.name()) &&
                ("getAvailableRequests".equalsIgnoreCase(getterMethod.name()) ||
                        "getSupportedImageExportFormats".equalsIgnoreCase(getterMethod.name()))) {
            convertStringToList(targetClass, getterMethod);
        }
    }


    private void convertStringToList(JDefinedClass targetClass, JMethod getterMethod) {
        JMethod method = targetClass.method(JMod.PUBLIC, typeManager.getListPrimitiveType("string"), String.format("%sAsList", getterMethod.name()));
        method.body()._return(typeManager.getArraysAsList(JExpr._this().invoke(getterMethod).invoke("split").arg("\\.")));
    }

    private void generateSetter(JDefinedClass targetClass, AbstractJType fieldType, JFieldVar fieldVar, Field field) {
        JMethod method = targetClass.method(JMod.PUBLIC, typeManager.getVoidType(), generateFieldMethodName(field.getName(), "set"));
        method.javadoc().add(field.getDescription());

        JVar param = method.param(fieldType, generateValidFieldName(field.getName()));
        method.body().add(JExpr._this().ref(fieldVar).assign(param));
    }

    private JMethod generateGetter(JDefinedClass targetClass, AbstractJType fieldType, JFieldVar fieldVar, Field field) {
        String methodPrefix = "get";
        if (typeManager.isBoolean(fieldType))
            methodPrefix = "is";

        AbstractJType returnType = fieldType;
        if (field.isOptional())
            returnType = typeManager.getOptionalForType(fieldType);

        JMethod method = targetClass.method(JMod.PUBLIC, returnType, generateFieldMethodName(field.getName(), methodPrefix));
        method.javadoc().add(field.getDescription());

        if (field.isOptional())
            method.body()._return(typeManager.getOptionalReturnForField(fieldVar));
        else
            method.body()._return(fieldVar);

        return method;
    }

    private void generateSubClassForProperty(JDefinedClass targetClass, List<String> propertyPath, Property property, FunctionType functionType) throws JCodeModelException, UnknownTypeException {
        String name = propertyPath.get(propertyPath.size() - 1);

        JDefinedClass subClass = targetClass;
        for (String element : propertyPath) {
            if (element.equalsIgnoreCase(name))
                continue;

            subClass = findSubClass(subClass, generateValidFieldName(element), functionType);
        }

        if (!subClass.fields().containsKey(name)) {
            generateFieldForProperty(subClass, functionType, new Field(name, property.getName(), property.getType(), property.getDescription(), property.isOptional()));
        }
    }

    private JDefinedClass findSubClass(JDefinedClass targetClass, String fieldName, FunctionType functionType) throws JCodeModelException, UnknownTypeException {
        String className = generateValidClassName(fieldName);
        JDefinedClass rootClass = getRootClass(targetClass);
        Optional<JDefinedClass> foundSubClass = rootClass.classes().stream().filter(sub -> className.equalsIgnoreCase(sub.name())).findFirst();

        JDefinedClass subClass;
        if (foundSubClass.isEmpty()) {
            subClass = rootClass._class(JMod.PUBLIC, className);
            generateFieldForProperty(targetClass, functionType, new Field(fieldName, subClass.name(), "", false));
        } else {
            subClass = foundSubClass.get();
        }

        return subClass;
    }
}
