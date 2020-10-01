package nl.harm27.obswebsocket.generator.generators.types;

import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JPackage;
import nl.harm27.obswebsocket.generator.datamodel.shared.Property;
import nl.harm27.obswebsocket.generator.datamodel.types.TypeDefinition;
import nl.harm27.obswebsocket.generator.generators.generic.FunctionType;
import nl.harm27.obswebsocket.generator.generators.generic.GenericClassGenerator;
import nl.harm27.obswebsocket.generator.generators.generic.TypeManager;
import nl.harm27.obswebsocket.generator.generators.generic.UnknownTypeException;

public class TypeGenerator extends GenericClassGenerator {
    private final JPackage typesPackageModel;
    private final TypeDefinition typeDefinition;
    private final String typeName;

    public TypeGenerator(TypeManager typeManager, JPackage typesPackageModel, TypeDefinition typeDefinition) {
        super(typeManager);
        this.typesPackageModel = typesPackageModel;
        this.typeDefinition = typeDefinition;
        this.typeName = typeDefinition.getName();
    }

    public void generate() throws JCodeModelException, UnknownTypeException {
        JDefinedClass typeClass = typesPackageModel._class(typeName);
        generateJavadocForClass(typeClass.javadoc(), typeDefinition.getDescription(), typeName);
        typeManager.addApiType(typeName, typeClass);

        for (Property property : typeDefinition.getProperties()) {
            generateProperty(typeClass, property, FunctionType.BOTH);
        }
    }
}
