package com.example;

import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("com.example.PrintElementInfo")
public class PrintElementInfoProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.WARNING, "process PrintElementInfoProcessor");
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                messager.printMessage(Diagnostic.Kind.WARNING,
                        "process " + element.getSimpleName());
                messager.printMessage(Diagnostic.Kind.WARNING, element.getSimpleName());
                messager.printMessage(Diagnostic.Kind.WARNING, element.getKind().name());
                messager.printMessage(Diagnostic.Kind.WARNING, element.asType().toString());
                printChildElement((TypeElement) element);
            }
        }
        return true;
    }

    public void printChildElement(TypeElement typeElement) {
        List<? extends Element> childElements = typeElement.getEnclosedElements();
        for (Element child : childElements) {
            if (ElementKind.FIELD.equals(child.getKind())) {
                printVaribleElement((VariableElement) child);
            }
        }
    }

    private void printVaribleElement(VariableElement variable) {
        String description = new StringBuilder("参数名称：").append(variable.getSimpleName())
                .append("元素种类：").append(variable.getKind().name()).append("参数元素的类型：")
                .append(variable.asType().toString()).append("参数元素的类型分类：")
                .append(variable.asType().getKind().name()).toString();
        messager.printMessage(Diagnostic.Kind.WARNING, description);

    }
}
