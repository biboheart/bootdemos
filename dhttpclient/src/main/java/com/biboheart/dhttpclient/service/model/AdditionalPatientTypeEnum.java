package com.biboheart.dhttpclient.service.model;

public enum AdditionalPatientTypeEnum {
    ADDITIONAL_PATIENT_TYPE_14("1", "一至六级残疾军人(原二等乙级伤残军人)"),
    ADDITIONAL_PATIENT_TYPE_15("2", "建国前老工人"),
    ;
    private final String code;
    private final String name;

    AdditionalPatientTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static AdditionalPatientTypeEnum getPatientType(String code) {
        AdditionalPatientTypeEnum[] typeEnums = AdditionalPatientTypeEnum.values();
        for (AdditionalPatientTypeEnum typeEnum : typeEnums) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}
