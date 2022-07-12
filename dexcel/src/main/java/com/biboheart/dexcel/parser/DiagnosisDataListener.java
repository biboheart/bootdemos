package com.biboheart.dexcel.parser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dexcel.entity.Diagnosis;
import com.biboheart.dexcel.model.DiagnosisModel;
import com.biboheart.dexcel.service.DiagnosisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DiagnosisDataListener extends AnalysisEventListener<DiagnosisModel> {
    private final DiagnosisService diagnosisService;

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * 缓存的数据
     */
    private List<Diagnosis> list = new ArrayList<>(BATCH_COUNT);
    private int count = 0;

    public DiagnosisDataListener(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Override
    public void invoke(DiagnosisModel dm, AnalysisContext analysisContext) {
        if (CheckUtils.isEmpty(dm.getInsuranceSn()) || CheckUtils.isEmpty(dm.getCountryNumber())){
            return;
        }
        Diagnosis diagnosis = new Diagnosis(null, dm.getInsuranceSn(), dm.getCountryNumber());
        list.add(diagnosis);
        count ++;
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
        diagnosisService.saveBatch(list);
        log.info("存储{}条数据！", list.size());
    }
}
