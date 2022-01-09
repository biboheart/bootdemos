package com.biboheart.dhttpclient.adapter.converts;

import com.biboheart.dhttpclient.adapter.Converter;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;
import com.biboheart.dhttpclient.adapter.support.ExchangeUtils;

import java.util.Map;

public class RightRadixPointConverter implements Converter {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Object input, String auxiliary, Class<T> valueType) {
        if (null == input) {
            return null;
        }
        if (null == auxiliary) {
            return TypeConverter.convert(input, valueType);
        }
        String val = TypeConverter.convert(input, String.class);
        Map<String, Object> params = TypeConverter.convert(auxiliary, Map.class);
        Integer moveLen = TypeConverter.convert(params.get("move"), Integer.class);
        Integer fractional = TypeConverter.convert(params.get("fractional"), Integer.class);
        Double dval = ExchangeUtils.moveRadixPoint(val, moveLen, fractional);
        if (null == dval) {
            return null;
        }
        return TypeConverter.convert(dval.longValue(), valueType);
    }
}
