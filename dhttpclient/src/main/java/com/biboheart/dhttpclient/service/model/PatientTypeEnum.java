package com.biboheart.dhttpclient.service.model;

public enum PatientTypeEnum {
    PATIENT_TYPE_14("14", "公务员在职"),
    PATIENT_TYPE_15("15", "事业在职"),
    PATIENT_TYPE_16("16", "企业在职"),
    PATIENT_TYPE_17("17", "在职（外省人员专用）"),
    PATIENT_TYPE_27("27", "灵活就业"),
    PATIENT_TYPE_24("24", "公务员退休"),
    PATIENT_TYPE_25("25", "事业退休"),
    PATIENT_TYPE_26("26", "企业退休"),
    PATIENT_TYPE_21("21", "退休（外省人员专用）"),
    PATIENT_TYPE_91("91", "其他"),
    ;
    private final String code;
    private final String name;

    PatientTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static PatientTypeEnum getPatientType(String code) {
        PatientTypeEnum[] typeEnums = PatientTypeEnum.values();
        for (PatientTypeEnum typeEnum : typeEnums) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return PatientTypeEnum.PATIENT_TYPE_91;
    }
}
