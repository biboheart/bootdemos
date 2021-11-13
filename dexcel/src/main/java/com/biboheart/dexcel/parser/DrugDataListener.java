package com.biboheart.dexcel.parser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.biboheart.dexcel.model.DrugModel;
import com.biboheart.dexcel.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DrugDataListener extends AnalysisEventListener<DrugModel> {
    private final DrugService drugService;

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * 缓存的数据
     */
    private List<DrugModel> list = new ArrayList<>(BATCH_COUNT);
    private int count = 0;

    public DrugDataListener(DrugService drugService) {
        this.drugService = drugService;
    }

    @Override
    public void invoke(DrugModel drug, AnalysisContext analysisContext) {
        count ++;
        if ("无".equals(drug.getCommonName())) {
            drug.setCommonName(null);
        }
        list.add(drug);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list = new ArrayList<>(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！总数: {}", count);
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        drugService.saveBatchFromModel(list);
        log.info("存储{}条数据！", list.size());
    }
}
