package com.biboheart.dhttpclient.adapter.converts;

import com.biboheart.dhttpclient.adapter.Converter;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;

public class ReplaceConverter implements Converter {
    @Override
    public <T> T convert(Object input, String auxiliary, Class<T> valueType) {
        if (null == auxiliary || "".equals(auxiliary)) {
            return TypeConverter.convert(input, valueType);
        }
        String val = TypeConverter.convert(input, String.class);
        return TypeConverter.convert(auxiliary.replaceAll("\\{\\[value]}", val), valueType);
    }
}
