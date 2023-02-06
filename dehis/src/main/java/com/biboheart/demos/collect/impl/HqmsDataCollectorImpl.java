package com.biboheart.demos.collect.impl;

import com.alibaba.excel.EasyExcel;
import com.biboheart.demos.collect.HqmsDataCollector;
import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.collect.parser.HqmsExcelListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HqmsDataCollectorImpl implements HqmsDataCollector {
    private final HqmsExcelListener hqmsExcelListener;

    @Override
    public boolean collectDataFromExcel(String filePath, Integer headRowNumber) {
        if (null == headRowNumber) {
            headRowNumber = 1;
        }
        EasyExcel.read(filePath, HealthDataModel.class, hqmsExcelListener).sheet().headRowNumber(headRowNumber).doRead();
        return true;
    }
}
