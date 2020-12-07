package com.ibeetl.code.ch12;

import java.io.IOException;

import javax.lang.model.element.Modifier;

import com.ibeetl.code.ch10.processor.annotation.JsonWriterConstants;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class UserJsonWriterGeneratorTest {
    public static void main(String[] args) throws IOException {
        MethodSpec mapper = MethodSpec.methodBuilder("write").addModifiers(Modifier.PUBLIC)
                .returns(String.class).addParameter(User.class, "user")
                .addStatement("$T sb = new $T()", StringBuilder.class, StringBuilder.class)
                .addStatement("sb.append($T.$N)", JsonWriterConstants.class, "OBJECT_PREFIX")
                .addStatement("sb.append($S)", "\"name\"").addStatement("sb.append($S)", "\"12\"")
                .addStatement("sb.append($T.$N)", JsonWriterConstants.class, "OBJECT_SUFFIX")
                .addStatement("return sb.toString()").build();

        TypeSpec jsonWriter = TypeSpec.classBuilder("Jsr269_UserJsonMapper")
                .addSuperinterface(UserJsonMapper.class).addModifiers(Modifier.PUBLIC)
                .addMethod(mapper).build();

        JavaFile javaFile = JavaFile.builder("com.ibeetl.com.ch12", jsonWriter).build();

        javaFile.writeTo(System.out);
    }
}
