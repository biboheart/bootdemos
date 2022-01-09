package com.biboheart.dhttpclient.adapter.converts;

import com.biboheart.dhttpclient.adapter.Converter;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;

public class StaticConverter implements Converter {
    @Override
    public <T> T convert(Object input, String auxiliary, Class<T> valueType) {
        if (null == input && (null == auxiliary || "".equals(auxiliary))) {
            return null;
        }
        if (null == input) {
            input = auxiliary;
        }
        return TypeConverter.convert(input, valueType);
    }
}
