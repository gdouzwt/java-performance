package com.ibeetl.code.ch10.processor;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic.Kind;

import com.ibeetl.code.ch10.processor.annotation.JsonWriterConstants;
import com.ibeetl.code.ch10.processor.annotation.JsonWriterUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class JsonWriterJavapoetGenerator extends AbstractJsonWriterGenerator {

    public JsonWriterJavapoetGenerator(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    public void generate(TypeElement mapperElement) {
        if (checkValid(mapperElement)) {
            ExecutableElement writeMethodElement = (ExecutableElement) mapperElement
                    .getEnclosedElements().get(0);
            if (checkValidWrite(writeMethodElement)) {
                VariableElement parameter = writeMethodElement.getParameters().get(0);
                doGenerate(mapperElement, writeMethodElement, parameter);
            }

        }

    }

    public void doGenerate(TypeElement mapperElement, ExecutableElement writeMethodElement,
            VariableElement parameter) {

        //生成方法
        MethodSpec writeMethod = generateWriteMethod(writeMethodElement, parameter);

        String packageName = ProcessorUtils
                .getPackageName(mapperElement.getQualifiedName().toString());

        //构建类名
        String generatedMapperSimpleName = new StringBuilder(GENERATE_CLASS_PREFIX)
                .append(mapperElement.getSimpleName()).toString();
        //生成类
        TypeSpec jsonWriter = TypeSpec.classBuilder(generatedMapperSimpleName)
                .addSuperinterface(ClassName.get(mapperElement.asType()))
                .addModifiers(Modifier.PUBLIC).addMethod(writeMethod).build();

        JavaFile javaFile = JavaFile.builder(packageName, jsonWriter).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
            messager.printMessage(Kind.NOTE,
                    "生成class : " + packageName + "." + generatedMapperSimpleName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MethodSpec generateWriteMethod(ExecutableElement writeMethodElement,
            VariableElement parameter) {
        String parameterName = parameter.getSimpleName().toString();
        TypeElement typeForParameter = (TypeElement) typeUtils.asElement(parameter.asType());
        Map<String, String> varsInTypeForParameter = getVariableElementFrom(typeForParameter);
        TypeName parameterTypeName = ClassName.get(typeForParameter.asType());
        //生成方法
        MethodSpec.Builder methodBuilder = MethodSpec
                .methodBuilder(writeMethodElement.getSimpleName().toString())
                .addParameter(parameterTypeName, parameterName).addModifiers(Modifier.PUBLIC)
                .returns(String.class);
        // 方法生成通用头
        methodBuilder.addStatement("$T sb = new $T()", StringBuilder.class, StringBuilder.class)
                .addStatement("sb.append($T.$N)", JsonWriterConstants.class, "OBJECT_PREFIX");
        Set<Entry<String, String>> entrySet = varsInTypeForParameter.entrySet();
        int limit = entrySet.size() - 1;
        int i = 0;
        for (Entry<String, String> entry : entrySet) {
            methodBuilder.addStatement("sb.append($T.quoteKey($S))", JsonWriterUtils.class,
                    entry.getKey()).addStatement("sb.append($T.quoteValue($N.$N()))",
                            JsonWriterUtils.class, parameterName, entry.getValue());
            if ((i++) != limit) {
                methodBuilder.addStatement("sb.append($S)", ",");
            }
        }

        // 生成方法返回语句
        methodBuilder.addStatement("sb.append($T.$N)", JsonWriterConstants.class, "OBJECT_SUFFIX")
                .addStatement("return sb.toString()");

        return methodBuilder.build();
    }

}
