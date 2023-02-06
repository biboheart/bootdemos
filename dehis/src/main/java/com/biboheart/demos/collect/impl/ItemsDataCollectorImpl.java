package com.biboheart.demos.collect.impl;

import com.alibaba.excel.EasyExcel;
import com.biboheart.demos.collect.ItemsDataCollector;
import com.biboheart.demos.collect.model.ItemsModel;
import com.biboheart.demos.collect.parser.ItemsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemsDataCollectorImpl implements ItemsDataCollector {
    private final ItemsListener itemsListener;

    @Override
    public boolean collectItemsFromCsv(String filePath, Integer headRowNumber) {
        if (null == headRowNumber) {
            headRowNumber = 1;
        }
        EasyExcel.read(filePath, ItemsModel.class, itemsListener).sheet().headRowNumber(headRowNumber).doRead();
        return true;
    }
}
