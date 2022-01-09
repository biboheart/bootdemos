package com.biboheart.dhttpclient.adapter.converts;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dhttpclient.adapter.Converter;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;

public class MillisToTimeConverter implements Converter {
    @Override
    public <T> T convert(Object input, String auxiliary, Class<T> valueType) {
        Long val = TypeConverter.convert(input, Long.class);
        if (CheckUtils.isEmpty(val)) {
            return null;
        }
        return TypeConverter.convert(TimeUtils.formatDateWithMilis(auxiliary, val), valueType);
    }
}
