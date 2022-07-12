package com.biboheart.dexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DrugModel {
    @ExcelProperty(index = 26)
    private String reservedField;
    @ExcelProperty(index = 25)
    private String saleUnit;
}
