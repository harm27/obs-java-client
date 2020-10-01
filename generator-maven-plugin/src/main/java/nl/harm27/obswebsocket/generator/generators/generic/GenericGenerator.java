package nl.harm27.obswebsocket.generator.generators.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helger.jcodemodel.*;

import java.util.List;

import static nl.harm27.obswebsocket.generator.generators.generic.StringUtil.generateEnumValue;
import static nl.harm27.obswebsocket.generator.generators.generic.StringUtil.generateValidClassName;

public abstract class GenericGenerator {
    private static final String DOCUMENTATION_REFERENCE = "<a href=\"https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#{link}\">OBS WebSocket Documentation</a>";

    protected JDefinedClass generateEnum(JPackage packageModel, String enumName, List<String> enumConstants, String description) throws JCodeModelException {
        String validEnumName = generateValidClassName(enumName);
        JDefinedClass enumClass = packageModel._getClass(validEnumName);
        if (enumClass != null)
            return enumClass;

        enumClass = packageModel._enum(validEnumName);
        enumClass.javadoc().add(description);
        for (String enumConstant : enumConstants) {
            JEnumConstant jEnumConstant = enumClass.enumConstant(generateEnumValue(enumConstant));
            jEnumConstant.annotate(JsonProperty.class).param(enumConstant);
        }
        return enumClass;
    }

    protected void generateJavadocForClass(JDocComment javadoc, String description, String className) {
        generateJavadocForClass(javadoc, description, className, null, null);
    }

    protected void generateJavadocForClass(JDocComment javadoc, String description, String className, String since, String deprecated) {
        javadoc.add(description);
        javadoc.addTag(JDocComment.TAG_SEE).add(DOCUMENTATION_REFERENCE.replace("{link}", className.toLowerCase()));

        if (since != null)
            javadoc.addTag(JDocComment.TAG_SINCE).add(since);

        if (deprecated != null)
            javadoc.addTag(JDocComment.TAG_DEPRECATED).add(deprecated);
    }

    protected JDefinedClass getRootClass(IJClassContainer<?> targetClass) {
        IJClassContainer<?> parentContainer = targetClass.parentContainer();
        if (parentContainer.isPackage()) {
            return (JDefinedClass) targetClass;
        }
        return getRootClass(parentContainer);
    }

    protected AbstractJClass findParentClass(JDefinedClass targetClass) {
        if ("Builder".equalsIgnoreCase(targetClass.name()) || "Response".equalsIgnoreCase(targetClass.name())) {
            return targetClass.outer();
        }
        return targetClass;
    }
}
