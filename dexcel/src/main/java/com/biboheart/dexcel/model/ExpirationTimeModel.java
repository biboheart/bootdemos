package com.biboheart.dexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExpirationTimeModel {
    @ExcelProperty(index = 0)
    private String reservedField; // 坐标编号
    @ExcelProperty(index = 4)
    private String batchNumber; // 批号
    @ExcelProperty(index = 7)
    private String expirationTime; // 包装单位数量
}
