package com.ibeetl.code.ch12;

import java.util.HashMap;
import java.util.Map;

public class MapperTools {
    private static final String CLASS_PREFIX = "Jsr269_";
	/** Key为定义的接口，value为生成的接口实现示例*/
    private static Map<Class<?>, Object> mappers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static synchronized  <T> T getMapper(Class<T> mapperClass) {
        if (mappers.containsKey(mapperClass)) {
            return (T) mappers.get(mapperClass);
        }
        String generatedClassName = new StringBuilder(mapperClass.getPackage().getName())
                .append(".").append(CLASS_PREFIX).append(mapperClass.getSimpleName()).toString();
        try {
            Class<?> clazz = mapperClass.getClassLoader().loadClass(generatedClassName);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            mappers.put(mapperClass, obj);
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException("获取辅助类错误",e);
        }
    }
}
