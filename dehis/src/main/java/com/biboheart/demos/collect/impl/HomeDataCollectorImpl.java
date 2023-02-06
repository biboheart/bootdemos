package com.biboheart.demos.collect.impl;

import com.alibaba.excel.EasyExcel;
import com.biboheart.demos.collect.HomeDataCollector;
import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.collect.model.PatientModel;
import com.biboheart.demos.collect.parser.HomeExcelListener;
import com.biboheart.demos.collect.parser.PatientExcelListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomeDataCollectorImpl implements HomeDataCollector {
    private final HomeExcelListener homeExcelListener;
    private final PatientExcelListener patientExcelListener;

    @Override
    public boolean collectRecordFromExcel(String filePath, Integer headRowNumber) {
        if (null == headRowNumber) {
            headRowNumber = 1;
        }
        EasyExcel.read(filePath, HealthDataModel.class, homeExcelListener).sheet().headRowNumber(headRowNumber).doRead();
        return true;
    }

    @Override
    public boolean collectPatientFromExcel(String filePath, Integer headRowNumber) {
        if (null == headRowNumber) {
            headRowNumber = 1;
        }
        EasyExcel.read(filePath, PatientModel.class, patientExcelListener).sheet().headRowNumber(headRowNumber).doRead();
        return true;
    }
}
