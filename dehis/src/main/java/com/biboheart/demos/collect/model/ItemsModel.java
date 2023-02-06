package com.biboheart.demos.collect.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ItemsModel {
    @ExcelProperty("MED_LIST_CODG")
    private String countryNumber;
    @ExcelProperty("MCS_NAME")
    private String countryName;
}
