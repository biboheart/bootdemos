package com.biboheart.demos.collect.parser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.domain.HqmsDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HqmsExcelListener extends AnalysisEventListener<HealthDataModel> {
    private final HqmsDomain hqmsDomain;

    private static final int BATCH_COUNT = 1000;
    private List<HealthDataModel> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(HealthDataModel data, AnalysisContext context) {
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            hqmsDomain.processHealthData(cachedDataList);
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        hqmsDomain.processHealthData(cachedDataList);
        cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        log.info("所有数据解析完成！");
    }
}
