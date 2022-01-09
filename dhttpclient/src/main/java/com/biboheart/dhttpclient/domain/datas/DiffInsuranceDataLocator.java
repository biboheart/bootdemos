package com.biboheart.dhttpclient.domain.datas;

import com.biboheart.dhttpclient.adapter.ConverterLocator;
import com.biboheart.dhttpclient.dock.mode.DiffCodeEnum;
import com.biboheart.dhttpclient.domain.model.DiffInsuranceData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DiffInsuranceDataLocator {
    private final ConverterLocator converterLocator;
    private final Map<DiffCodeEnum, DiffInsuranceData> dataHash = new HashMap<>();

    DiffInsuranceDataLocator(ConverterLocator converterLocator) {
        this.converterLocator = converterLocator;
        this.init();
    }

    private void init() {
        dataHash.put(DiffCodeEnum.DIFF_CODE_9201, DiffDatas9201.create(converterLocator));
        dataHash.put(DiffCodeEnum.DIFF_CODE_9202, DiffDatas9202.create(converterLocator));
    }

    public DiffInsuranceData getInsuranceData(DiffCodeEnum code) {
        return dataHash.get(code);
    }
}
