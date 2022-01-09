package com.biboheart.dhttpclient.adapter.converts;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.ListUtils;
import com.biboheart.dhttpclient.adapter.Converter;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EnumConverter implements Converter {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Object input, String auxiliary, Class<T> valueType) {
        if (CheckUtils.isEmpty(auxiliary)) {
            return null;
        }
        Map<String, Object> enumMap = TypeConverter.convert(auxiliary, Map.class);
        String key = null == input ? "def" : String.valueOf(input);
        List<String> keyList = ListUtils.string2ListString(key, ",");
        Set<String> vals = new HashSet<>();
        for (String keyItem : keyList) {
            if (!enumMap.containsKey(keyItem)) {
                keyItem = "def";
            }
            Object temp = enumMap.get(keyItem);
            if (null == temp) {
                continue;
            }
            vals.add(String.valueOf(temp));
        }
        if (CheckUtils.isEmpty(vals)) {
            input = null;
        } else {
            input = String.join(",", vals);
        }
        return TypeConverter.convert(input, valueType);
    }
}
