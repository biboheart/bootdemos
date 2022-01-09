package com.biboheart.dhttpclient.adapter.support;

import com.biboheart.brick.utils.JsonUtils;

public class TypeConverter {
    public static <T> T convert(Object input, Class<T> valueType) {
        if (null == input) {
            return null;
        }
        if (valueType.isInstance(input)) {
            return valueType.cast(input);
        }
        if (Long.class.isAssignableFrom(valueType)) {
            return valueType.cast(convertToLong(input));
        } else if (Integer.class.isAssignableFrom(valueType)) {
            return valueType.cast(convertToInteger(input));
        } else if (Double.class.isAssignableFrom(valueType)) {
            return valueType.cast(convertToDouble(input));
        } else if (String.class.isAssignableFrom(valueType)) {
            return valueType.cast(convertToString(input));
        } else if (input instanceof String){
            return valueType.cast(JsonUtils.json2obj(String.valueOf(input), valueType));
        } else {
            return null;
        }
    }

    public static Integer convertToInteger(Object input) {
        if (null == input) {
            return null;
        }
        if(!(input instanceof Long) && !(input instanceof Integer) && !(input instanceof String)) {
            return null;
        }
        if (input instanceof Integer) {
            return (Integer) input;
        } else {
            return Integer.parseInt(String.valueOf(input));
        }
    }

    public static Double convertToDouble(Object input) {
        if (null == input) {
            return null;
        }
        if(!(input instanceof Double) && !(input instanceof String)) {
            return null;
        }
        if (input instanceof Double) {
            return (Double) input;
        } else {
            return Double.parseDouble(String.valueOf(input));
        }
    }

    public static Long convertToLong(Object input) {
        if (null == input) {
            return null;
        }
        if(!(input instanceof Long) && !(input instanceof Integer) && !(input instanceof String)) {
            return null;
        }
        if (input instanceof Long) {
            return (Long) input;
        } else {
            return Long.parseLong(String.valueOf(input));
        }
    }

    public static String convertToString(Object input) {
        if (null == input) {
            return null;
        }
        if(!(input instanceof Long) && !(input instanceof Integer) && !(input instanceof String)) {
            return null;
        }
        if (input instanceof String) {
            return (String) input;
        } else {
            return String.valueOf(input);
        }
    }
}
