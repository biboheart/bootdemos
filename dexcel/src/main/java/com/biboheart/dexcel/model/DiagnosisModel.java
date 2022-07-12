package com.biboheart.dexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DiagnosisModel {
    @ExcelProperty(index = 1)
    private String insuranceSn;
    @ExcelProperty(index = 8)
    private String countryNumber;
}
