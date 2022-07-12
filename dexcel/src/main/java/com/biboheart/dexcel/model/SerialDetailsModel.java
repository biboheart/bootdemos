package com.biboheart.dexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SerialDetailsModel {
    @ExcelProperty(index = 0)
    private String reservedField; // 坐标编号
    @ExcelProperty(index = 2)
    private String batchNumber; // 批号
    @ExcelProperty(index = 3)
    private Integer packageCount; // 包装单位数量
    /*@ExcelProperty(index = 3)
    private String buyingPrice; // 进价*/
    /*@ExcelProperty(index = 3)
    private String sellingPrice; // 售价*/
    /*@ExcelProperty(index = 5)
    private Integer saleUnit; // 0: 不拆零, 7: 拆零*/
}
