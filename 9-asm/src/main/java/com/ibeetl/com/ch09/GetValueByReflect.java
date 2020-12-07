package com.ibeetl.com.ch09;

import java.lang.reflect.Field;

public class GetValueByReflect {
    private GetValueByReflect() {

    }

    public static Object value(Object bean, String property) {
        try {
            Field field = bean.getClass().getDeclaredField(property);
            field.setAccessible(true);
            return field.get(bean);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            // Do nothing
        }
        return null;

    }
}
