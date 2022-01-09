package com.biboheart.dhttpclient.service.model;

public enum NationEnum {
        NATION_ENUM_01("01", "汉族"),
        NATION_ENUM_02("02", "蒙古族"),
        NATION_ENUM_03("03", "回族"),
        NATION_ENUM_04("04", "藏族"),
        NATION_ENUM_05("05", "维吾尔族"),
        NATION_ENUM_06("06", "苗族"),
        NATION_ENUM_07("07", "彝族"),
        NATION_ENUM_08("08", "壮族"),
        NATION_ENUM_09("09", "布依族"),
        NATION_ENUM_10("10", "朝鲜族"),
        NATION_ENUM_11("11", "满族"),
        NATION_ENUM_12("12", "侗族"),
        NATION_ENUM_13("13", "瑶族"),
        NATION_ENUM_14("14", "白族"),
        NATION_ENUM_15("15", "土家族"),
        NATION_ENUM_16("16", "哈尼族"),
        NATION_ENUM_17("17", "哈萨克族"),
        NATION_ENUM_18("18", "傣族"),
        NATION_ENUM_19("19", "黎族"),
        NATION_ENUM_20("20", "傈僳族"),
        NATION_ENUM_21("21", "佤族"),
        NATION_ENUM_22("22", "畲族"),
        NATION_ENUM_23("23", "高山族"),
        NATION_ENUM_24("24", "拉祜族"),
        NATION_ENUM_25("25", "水族"),
        NATION_ENUM_26("26", "东乡族"),
        NATION_ENUM_27("27", "纳西族"),
        NATION_ENUM_28("28", "景颇族"),
        NATION_ENUM_29("29", "柯尔克孜"),
        NATION_ENUM_30("30", "土族"),
        NATION_ENUM_31("31", "达斡尔族"),
        NATION_ENUM_32("32", "仫佬族"),
        NATION_ENUM_33("33", "羌族"),
        NATION_ENUM_34("34", "布朗族"),
        NATION_ENUM_35("35", "撒拉族"),
        NATION_ENUM_36("36", "毛南族"),
        NATION_ENUM_37("37", "仡佬族"),
        NATION_ENUM_38("38", "锡伯族"),
        NATION_ENUM_39("39", "阿昌族"),
        NATION_ENUM_40("40", "普米族"),
        NATION_ENUM_41("41", "塔吉克族"),
        NATION_ENUM_42("42", "怒族"),
        NATION_ENUM_43("43", "乌孜别克族"),
        NATION_ENUM_44("44", "俄罗斯族"),
        NATION_ENUM_45("45", "鄂温克族"),
        NATION_ENUM_46("46", "崩龙族"),
        NATION_ENUM_47("47", "保安族"),
        NATION_ENUM_48("48", "裕固族"),
        NATION_ENUM_49("49", "京族"),
        NATION_ENUM_50("50", "塔塔尔族"),
        NATION_ENUM_51("51", "独龙族"),
        NATION_ENUM_52("52", "鄂伦春族"),
        NATION_ENUM_53("53", "赫哲族"),
        NATION_ENUM_54("54", "门巴族"),
        NATION_ENUM_55("55", "珞巴族"),
        NATION_ENUM_56("56", "基诺族"),
        NATION_ENUM_99("99", "其他"),
    ;
    private final String code;
    private final String name;

    NationEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static NationEnum getNation(String code) {
        NationEnum[] nationEnums = NationEnum.values();
        for (NationEnum nationEnum : nationEnums) {
            if (nationEnum.getCode().equals(code)) {
                return nationEnum;
            }
        }
        return NationEnum.NATION_ENUM_99;
    }
}
