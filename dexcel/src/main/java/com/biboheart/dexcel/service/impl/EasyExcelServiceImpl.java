package com.biboheart.dexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.biboheart.dexcel.model.*;
import com.biboheart.dexcel.parser.*;
import com.biboheart.dexcel.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class EasyExcelServiceImpl implements ExcelService {
    private final DrugDataListener drugDataListener;
    private final DrugFullDataListener drugFullDataListener;
    private final DiagnosisDataListener diagnosisDataListener;
    private final SerialDetailsListener serialDetailsListener;
    private final ExpirationTimeListener expirationTimeListener;

    public EasyExcelServiceImpl(DrugDataListener drugDataListener, DrugFullDataListener drugFullDataListener, DiagnosisDataListener diagnosisDataListener, SerialDetailsListener serialDetailsListener, ExpirationTimeListener expirationTimeListener) {
        this.drugDataListener = drugDataListener;
        this.drugFullDataListener = drugFullDataListener;
        this.diagnosisDataListener = diagnosisDataListener;
        this.serialDetailsListener = serialDetailsListener;
        this.expirationTimeListener = expirationTimeListener;
    }

    @Override
    public void readExcel() throws IOException, InvalidFormatException {

    }

    @Override
    public void readExcelOperation() throws IOException, InvalidFormatException {

    }

    @Override
    public void readExcelOther() throws IOException, InvalidFormatException {

    }

    @Override
    public void readExcelDrug() throws IOException, InvalidFormatException {
        String filePath = "E:/test/20220211/yp20220121.xls";
        EasyExcel.read(filePath, DrugModel.class, drugDataListener).sheet()
            // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
            .headRowNumber(1).doRead();
    }

    @Override
    public void readExcelDrugFull() throws IOException, InvalidFormatException {
        String filePath = "E:/test/DRUG.xlsx";
        EasyExcel.read(filePath, DrugFullModel.class, drugFullDataListener).sheet().headRowNumber(3).doRead();
    }

    @Override
    public void readExcelDiagnosis() throws IOException, InvalidFormatException {
        String filePath = "E:/test/diagnosis.xls";
        EasyExcel.read(filePath, DiagnosisModel.class, diagnosisDataListener).sheet().headRowNumber(1).doRead();
    }

    @Override
    public void readSerial() throws IOException, InvalidFormatException {
        String filePath = "E:/test/20220211/供应室材料.xls";
        EasyExcel.read(filePath, SerialDetailsModel.class, serialDetailsListener).sheet().headRowNumber(1).doRead();
    }

    @Override
    public void readExpirationTime() throws IOException, InvalidFormatException {
        String filePath = "E:/test/20220211/中药药房药品有效期.xls";
        EasyExcel.read(filePath, ExpirationTimeModel.class, expirationTimeListener).sheet().headRowNumber(1).doRead();
    }
}
