package com.ibeetl.code.ch10.processor;

import org.objectweb.asm.tree.FieldNode;

public final class ProcessorUtils {
    private ProcessorUtils() {
    }

    static String getGetterMethodName(FieldNode fieldNode) {
        return createGetterMethodName(fieldNode.name);
    }

    static String createGetterMethodName(String fieldName) {
    	//一个简单的通过fieldName获得getter方法，注意:此方法并没有完全实现Java规范
        return new StringBuilder("get").append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();
    }

    static String getInternalName(final String className) {
        return className.replace('.', '/');
    }

    static String getPackageName(final String className) {
        return className.substring(0, className.lastIndexOf('.'));
    }
}
