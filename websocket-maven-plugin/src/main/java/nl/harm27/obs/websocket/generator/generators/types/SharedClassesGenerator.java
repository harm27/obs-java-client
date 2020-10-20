package nl.harm27.obs.websocket.generator.generators.types;

import com.helger.jcodemodel.*;
import nl.harm27.obs.websocket.generator.generators.generic.GenericGenerator;
import nl.harm27.obs.websocket.generator.generators.generic.TypeManager;

import java.time.Duration;
import java.util.regex.Pattern;

public class SharedClassesGenerator extends GenericGenerator {
    private final JCodeModel codeModel;
    private final TypeManager typeManager;
    private final JPackage basePackageModel;

    public SharedClassesGenerator(JCodeModel codeModel, TypeManager typeManager, JPackage basePackageModel) {
        this.codeModel = codeModel;
        this.typeManager = typeManager;
        this.basePackageModel = basePackageModel;
    }

    public void generate() throws JCodeModelException {
        generateTimeUtil();
    }

    private void generateTimeUtil() throws JCodeModelException {
        JDefinedClass timeUtilClass = basePackageModel._class(JMod.PUBLIC | JMod.FINAL, "TimeUtil");
        timeUtilClass.constructor(JMod.PRIVATE);

        JMethod parseDurationMethod = timeUtilClass.method(JMod.PUBLIC | JMod.STATIC, typeManager.getOptionalForType(Duration.class), "parseDuration");
        JVar timecodeVar = parseDurationMethod.param(String.class, "timecode");
        JBlock body = parseDurationMethod.body();

        JVar arrayPart1 = getArrayPart(body, "arrayPart1", timecodeVar.invoke("split"), ":", 3);
        JVar arrayPart2 = getArrayPart(body, "arrayPart2", arrayPart1.component(2).invoke("split"), ".", 2);

        JMethod isNotNumeric = getIsNotNumericMethod(timeUtilClass);
        JVar hours = getDurationPart(isNotNumeric, body, arrayPart1, "hours", 0);
        JVar minutes = getDurationPart(isNotNumeric, body, arrayPart1, "minutes", 1);
        JVar seconds = getDurationPart(isNotNumeric, body, arrayPart2, "seconds", 0);
        JVar millis = getDurationPart(isNotNumeric, body, arrayPart2, "millis", 1);

        JInvocation durationElements = codeModel.ref(Duration.class).staticInvoke("ofHours").arg(getParseLong(hours));
        durationElements = parseDurationPart(durationElements, "plusMinutes", minutes);
        durationElements = parseDurationPart(durationElements, "plusSeconds", seconds);
        durationElements = parseDurationPart(durationElements, "plusMillis", millis);

        body._return(typeManager.getOptionalReturnForField(durationElements));

        typeManager.registerTimeUtil(timeUtilClass);
    }

    private JMethod getIsNotNumericMethod(JDefinedClass timeUtilClass) {
        JFieldVar numericFormatPattern = timeUtilClass.field(JMod.PRIVATE_FINAL | JMod.STATIC, Pattern.class, "NUMERIC_FORMAT_PATTERN");
        numericFormatPattern.init(codeModel.ref(Pattern.class).staticInvoke("compile").arg("-?\\d+(\\.\\d+)?"));

        JMethod isNotNumeric = timeUtilClass.method(JMod.PRIVATE | JMod.STATIC, boolean.class, "isNotNumeric");
        JVar elementVar = isNotNumeric.param(String.class, "element");
        isNotNumeric.body()._return(JOp.not(numericFormatPattern.invoke("matcher").arg(elementVar).invoke("matches")));
        return isNotNumeric;
    }

    private JInvocation getParseLong(JVar durationElement) {
        return codeModel.ref(Long.class).staticInvoke("parseLong").arg(durationElement);
    }

    private JInvocation parseDurationPart(JInvocation previous, String method, JVar minutes) {
        return previous.invoke(method).arg(getParseLong(minutes));
    }

    private JVar getDurationPart(JMethod isNotNumeric, JBlock body, JVar arrayPart, String variableName, int position) {
        JVar hours = body.decl(codeModel.ref(String.class), variableName, arrayPart.component(position));
        body._if(JExpr.invoke(isNotNumeric).arg(hours))._then()._return(typeManager.getEmptyOptional());
        return hours;
    }

    private JVar getArrayPart(JBlock body, String variableName, JInvocation split, String splitCharacter, int i) {
        JVar arrayPart = body.decl(codeModel.ref(String[].class), variableName, split.arg(codeModel.ref(Pattern.class).staticInvoke("quote").arg(splitCharacter)));
        body._if(arrayPart.ref("length").ne(JExpr.lit(i)))._then()._return(typeManager.getEmptyOptional());
        return arrayPart;
    }
}
