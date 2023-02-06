package com.biboheart.demos.collect.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PatientModel {
    @ExcelProperty(index = 4)
    private String number;
    @ExcelProperty(index = 0)
    private String patientName;
    /*@ExcelProperty(index = 4)
    private String hospitalizedSn;*/
    @ExcelProperty(index = 12)
    private String centreNumber;
    @ExcelProperty(index = 8)
    private String insurancePersonSn;
    @ExcelProperty(index = 40)
    private String insuranceOrderSn;
    @ExcelProperty(index = 44)
    private Date admissionTime;
    @ExcelProperty(index = 48)
    private Date leaveTime;
    @ExcelProperty(index = 14)
    private Date settleTime;
}
