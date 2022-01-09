package com.biboheart.dhttpclient.service.model;

public enum ResultTypeEnum {
    PATIENT_INFO("patient_info", "患者信息")
    ;
    private final String name;
    private final String desc;

    ResultTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
