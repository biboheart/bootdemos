package com.biboheart.demos.collect.parser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.demos.collect.model.ItemsModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemsListener extends AnalysisEventListener<ItemsModel> {
    private static final int BATCH_COUNT = 1000;
    private List<ItemsModel> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(ItemsModel data, AnalysisContext analysisContext) {
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(JsonUtils.obj2json(cachedDataList.get(0)));
        cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        log.info("所有数据解析完成！");
    }
}
