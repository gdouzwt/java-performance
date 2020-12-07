package com.ibeetl.code.ch10.processor.annotation;

public final class JsonWriterUtils {
    private JsonWriterUtils() {

    }

    public static String quoteKey(String value) {
        if (value == null) {
            return value;
        }
        return "\"" + value + "\":";
    }

    public static String quoteValue(String value) {
        if (value == null) {
            return value;
        }
        return "\"" + value + "\"";
    }
}
