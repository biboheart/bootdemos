package com.biboheart.dexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DrugFullModel {
    @ExcelProperty(index = 0)
    private String insuranceSn;
    @ExcelProperty(index = 15)
    private String standardSn;
    @ExcelProperty(index = 23)
    private String typeSn;
    @ExcelProperty(index = 2)
    private String name;
    @ExcelProperty(index = 3)
    private String commonName;
    @ExcelProperty(index = 5)
    private String form;
    @ExcelProperty(index = 7)
    private String specifications;
    @ExcelProperty(index = 14)
    private String approvalNumber;
    @ExcelProperty(index = 12)
    private String placeOrigin;
    @ExcelProperty(index = 10)
    private String basicUnit;
    @ExcelProperty(index = 11)
    private String packageUnit;
    @ExcelProperty(index = 9)
    private String conversionCoefficient;
}
