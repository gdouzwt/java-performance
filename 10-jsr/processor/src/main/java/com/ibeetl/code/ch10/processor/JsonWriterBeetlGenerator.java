package com.ibeetl.code.ch10.processor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import com.squareup.javapoet.ClassName;

public class JsonWriterBeetlGenerator extends AbstractJsonWriterGenerator {

    public JsonWriterBeetlGenerator(ProcessingEnvironment processingEnv) {
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
        //包名
        String packageName = ProcessorUtils
                .getPackageName(mapperElement.getQualifiedName().toString());
        //构建类名
        String generateClassSimpleName = new StringBuilder(GENERATE_CLASS_PREFIX)
                .append(mapperElement.getSimpleName()).toString();
        String generatedClassName = new StringBuilder(packageName).append(".")
                .append(generateClassSimpleName).toString();
        //接口名称
        String interfaceName = ClassName.get(mapperElement.asType()).toString();

        String parameterName = parameter.getSimpleName().toString();
        TypeElement typeForParameter = (TypeElement) typeUtils.asElement(parameter.asType());
        Map<String, String> varsInTypeForParameter = getVariableElementFrom(typeForParameter);
        String parameterTypeName = ClassName.get(typeForParameter.asType()).toString();

        //初始化代码
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
		GroupTemplate gt = new GroupTemplate();
		gt.getConf().setStatementStart("@");
		gt.getConf().setStatementEnd(null);
		gt.getConf().build();
		//获取模板
		Template t = gt.getTemplate("mapper.btl");
        t.binding("packageName", packageName);
        t.binding("interfaceName", interfaceName);
        t.binding("generateClassSimpleName", generateClassSimpleName);
        t.binding("writeMethodName", writeMethodElement.getSimpleName().toString());
        t.binding("parameterTypeQualifiedName", typeForParameter.getQualifiedName().toString());
        t.binding("parameterTypeName", parameterTypeName);
        t.binding("parameterName", parameterName);
        t.binding("varsInTypeForParameter", varsInTypeForParameter);
        //渲染结果
        String str = t.render();
        OutputStream fout = null;
        try {
            JavaFileObject builderFile = processingEnv.getFiler()
                    .createSourceFile(generatedClassName);
            fout = builderFile.openOutputStream();
            fout.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            messager.printMessage(Kind.ERROR, "写入文件错误！");
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {

                }
            }
        }
    }

}
