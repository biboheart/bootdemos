package com.biboheart.ditems.enums;

import com.beust.jcommander.internal.Lists;

import javax.validation.constraints.Null;
import java.util.List;

public enum ItemsTypeEnum {
    ITE_WESTERN_MEDICINE("11", "西药"),
    ITE_CHINESE_PATENT("12", "中成药"),
    ITE_CHINESE_HERBAL("13", "中草药"),
    ITE_ETHNIC_MEDICINE("14", "民族药"),
    ITE_REAGENT("15", "试剂"),
    ITE_CHINESE_PRESCRIPTION("16", "中药方"),
    ITE_HEALTH_PRODUCTS("17", "保健品"),
    ITE_MATERIAL_SCIENCE("21", "材料"),
    ITE_APPARATUS("22", "器械"),
    ITE_INSPECTION("31", "检查"),
    ITE_LABORATORY("32", "检检"),
    ITE_OPERATION("41", "手术"),
    ITE_ANAESTHESIA("42", "麻醉"),
    ITE_SAMPLE("51", "样本"),
    ITE_INSPECTION_METHOD("52", "检查方法"),
    ITE_ADMINISTRATION_ROUTE("53", "给药途径"),
    ITE_REGISTER("81", "挂号"),
    ITE_SERVE("91", "服务"),
    ITE_OTHER("99", "其他"),
    ;
    private final String code;
    private final String desc;

    ItemsTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ItemsTypeEnum getConverterType(String code) {
        ItemsTypeEnum[] itemsTypeEnums = ItemsTypeEnum.values();
        for (ItemsTypeEnum itemsTypeEnum : itemsTypeEnums) {
            if (itemsTypeEnum.getCode().equals(code)) {
                return itemsTypeEnum;
            }
        }
        return ITE_OTHER;
    }

    public static List<EnumDto> toList() {
        List<EnumDto> list = Lists.newArrayList();
        for (ItemsTypeEnum value : ItemsTypeEnum.values()) {
            list.add(new EnumDto(value.getCode(), value.getDesc()));
        }
        return list;
    }
}
