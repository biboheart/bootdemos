package com.biboheart.dhttpclient.adapter;

import com.biboheart.dhttpclient.adapter.converts.*;
import com.biboheart.dhttpclient.adapter.structure.ConverterType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConverterLocator {
    private final Map<ConverterType, Converter> converterMap = new HashMap<>();

    public ConverterLocator() {
        this.init();
    }

    public Converter get(ConverterType type) {
        return converterMap.get(type);
    }

    public Converter get(String type) {
        return converterMap.get(ConverterType.getConverterType(type));
    }

    private void init() {
        converterMap.put(ConverterType.CONVERTER_STATIC, new StaticConverter());
        converterMap.put(ConverterType.CONVERTER_REPLACE, new ReplaceConverter());
        converterMap.put(ConverterType.CONVERTER_ENUM, new EnumConverter());
        converterMap.put(ConverterType.CONVERTER_RIGHT_RADIX_POINT, new RightRadixPointConverter());
        converterMap.put(ConverterType.CONVERTER_LEFT_RADIX_POINT, new LeftRadixPointConverter());
        converterMap.put(ConverterType.CONVERTER_TIME_MILLIS, new TimeToMillisConverter());
        converterMap.put(ConverterType.CONVERTER_MILLIS_TIME, new MillisToTimeConverter());
    }
}
