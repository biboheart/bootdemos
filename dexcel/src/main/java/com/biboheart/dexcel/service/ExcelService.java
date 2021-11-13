package com.biboheart.dexcel.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public interface ExcelService {
    void readExcel() throws IOException, InvalidFormatException;
    void readExcelOperation() throws IOException, InvalidFormatException;
    void readExcelOther() throws IOException, InvalidFormatException;
    void readExcelDrug() throws IOException, InvalidFormatException;
    void readExcelDrugFull() throws IOException, InvalidFormatException;
}
