package com.ibeetl.code.ch10.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public abstract class AbstractJsonWriterGenerator {

    static final String VALID_RETURN_TYPE_NAME = String.class.getName();
    static final String GENERATE_CLASS_PREFIX = "Jsr269_";

    ProcessingEnvironment processingEnv;
    Messager messager;
    Elements elementUtils;
    Types typeUtils;

    AbstractJsonWriterGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
    }

    public abstract void generate(TypeElement mapperElement);

    /**
     * 判断是否为有效的类
     * 
     * @param mapperElement
     * @return
     */
    public boolean checkValid(TypeElement mapperElement) {
        return mapperElement.getEnclosedElements().size() == 1;
    }

    public boolean checkValidWrite(ExecutableElement writeMethodElement) {
        return VALID_RETURN_TYPE_NAME.equals(writeMethodElement.getReturnType().toString())// 返回类型是String
                && writeMethodElement.getParameters().size() == 1;
    }

    public Map<String, String> getVariableElementFrom(TypeElement typeElement) {
        Map<String, VariableElement> variables = new HashMap<>();
        Map<String, ExecutableElement> methods = new HashMap<>();
        List<? extends Element> elements = typeElement.getEnclosedElements();
        int eleSize = elements.size();
        Element curEle = null;
        for (int i = 0; i < eleSize; i++) {
            curEle = elements.get(i);
            if (ElementKind.FIELD.equals(curEle.getKind())) {
                variables.put(curEle.getSimpleName().toString(), (VariableElement) curEle);
            } else if (ElementKind.METHOD.equals(curEle.getKind())) {
                methods.put(curEle.getSimpleName().toString(), (ExecutableElement) curEle);
            }
        }
        Map<String, String> ess = new HashMap<>();
        Set<Entry<String, VariableElement>> entrySets = variables.entrySet();
        ExecutableElement getterEle;
        for (Entry<String, VariableElement> entry : entrySets) {
            getterEle = methods.get(ProcessorUtils.createGetterMethodName(entry.getKey()));
            ess.put(entry.getValue().getSimpleName().toString(),
                    getterEle.getSimpleName().toString());
        }
        return ess;
    }
}
