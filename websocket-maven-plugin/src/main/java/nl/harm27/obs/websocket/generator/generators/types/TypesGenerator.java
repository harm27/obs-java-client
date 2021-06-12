package nl.harm27.obs.websocket.generator.generators.types;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JPackage;
import nl.harm27.obs.websocket.generator.datamodel.types.TypeDefinition;
import nl.harm27.obs.websocket.generator.generators.generic.TypeManager;
import nl.harm27.obs.websocket.generator.generators.generic.UnknownTypeException;

import java.util.List;

public class TypesGenerator {
    private final JPackage typesPackageModel;
    private final TypeManager typeManager;
    private final JCodeModel codeModel;
    private final JPackage basePackageModel;

    public TypesGenerator(TypeManager typeManager, JCodeModel codeModel, String apiPackageName) {
        this.typeManager = typeManager;
        this.codeModel = codeModel;
        typesPackageModel = codeModel._package(String.format("%s.types", apiPackageName));
        basePackageModel = codeModel._package(String.format("%s.base", apiPackageName));
    }

    public void generate(List<TypeDefinition> typeDefinitions) throws JCodeModelException, UnknownTypeException {
        var sharedClassesGenerator = new SharedClassesGenerator(codeModel, typeManager, basePackageModel);
        sharedClassesGenerator.generate();

        for (TypeDefinition typeDefinition : typeDefinitions) {
            var typeGenerator = new TypeGenerator(typeManager, typesPackageModel, typeDefinition);
            typeGenerator.generate();
        }
    }

}
