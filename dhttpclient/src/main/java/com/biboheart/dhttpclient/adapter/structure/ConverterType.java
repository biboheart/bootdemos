package com.biboheart.dhttpclient.adapter.structure;

import com.beust.jcommander.internal.Lists;

import java.util.List;

public enum ConverterType {
    CONVERTER_STATIC("static", "静态"),
    CONVERTER_REPLACE("replace", "替换"),
    CONVERTER_ENUM("enum", "枚举"),
    CONVERTER_RIGHT_RADIX_POINT("rightRadixPoint", "小数点右移"),
    CONVERTER_LEFT_RADIX_POINT("leftRadixPoint", "小数点左移"),
    CONVERTER_TIME_MILLIS("timeToMillis", "时间转时间戳"),
    CONVERTER_MILLIS_TIME("millisToTime", "时间戳转时间");

    private final String code;
    private final String desc;

    ConverterType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ConverterType getConverterType(String name) {
        ConverterType[] converterTypes = ConverterType.values();
        for (ConverterType converterType : converterTypes) {
            if (converterType.getCode().equals(name)) {
                return converterType;
            }
        }
        return ConverterType.CONVERTER_STATIC;
    }

    public static List<EnumDto> toList() {
        List<EnumDto> list = Lists.newArrayList();
        for (ConverterType value : ConverterType.values()) {
            list.add(new EnumDto(value.getCode(), value.getDesc()));
        }
        return list;
    }
}
