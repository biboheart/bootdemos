package com.biboheart.dhttpclient.service.model;

public enum InsuranceTypeEnum {
    INSURANCE_TYPE_310("310", "职工基本医疗保险"),
    INSURANCE_TYPE_340("340", "离休人员医疗保障"),
    INSURANCE_TYPE_350("350", "一至六级残疾军人医疗补助"),
    INSURANCE_TYPE_360("360", "老红军医疗保障"),
    INSURANCE_TYPE_380("380", "新型农村合作医疗"),
    INSURANCE_TYPE_390("390", "城乡居民基本医疗保险"),
    INSURANCE_TYPE_391("391", "城镇居民基本医疗保险"),
    ;
    private final String code;
    private final String name;

    InsuranceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static InsuranceTypeEnum getInsuranceType(String code) {
        InsuranceTypeEnum[] typeEnums = InsuranceTypeEnum.values();
        for (InsuranceTypeEnum typeEnum : typeEnums) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return InsuranceTypeEnum.INSURANCE_TYPE_310;
    }
}
