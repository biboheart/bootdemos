package com.biboheart.dexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DrugModel {
    @ExcelProperty(index = 1)
    private String insuranceSn;
    @ExcelProperty(index = 2)
    private String reimbursementType;
    @ExcelProperty(index = 3)
    private String name;
    @ExcelProperty(index = 9)
    private String commonName;
    @ExcelProperty(index = 5)
    private String form;
    @ExcelProperty(index = 15)
    private String placeOrigin;
    @ExcelProperty(index = 11)
    private String specifications;
    @ExcelProperty(index = 12)
    private String packageUnit;
}
