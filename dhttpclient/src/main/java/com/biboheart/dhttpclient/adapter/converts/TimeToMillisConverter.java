package com.biboheart.dhttpclient.adapter.converts;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dhttpclient.adapter.Converter;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;

public class TimeToMillisConverter implements Converter {
    @Override
    public <T> T convert(Object input, String auxiliary, Class<T> valueType) {
        String val = TypeConverter.convert(input, String.class);
        if (CheckUtils.isEmpty(val)) {
            return null;
        }
        return TypeConverter.convert(TimeUtils.getDateMillis(val, auxiliary), valueType);
    }
}
