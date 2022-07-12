package com.biboheart.dexcel.service.impl;

import com.biboheart.dexcel.entity.Drug;
import com.biboheart.dexcel.model.DrugFullModel;
import com.biboheart.dexcel.model.DrugModel;
import com.biboheart.dexcel.repository.DrugRepository;
import com.biboheart.dexcel.service.DrugService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    private final DrugRepository drugRepository;

    public DrugServiceImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public void saveBatch(List<Drug> drugList) {
        drugRepository.saveAll(drugList);
    }

    @Override
    public void saveBatchFromModel(List<DrugModel> modelList) {
        List<Drug> drugList = new ArrayList<>();
        modelList.forEach(item -> {
            Drug drug = new Drug();
            drug.setReservedField(item.getReservedField());
            if ("是".equals(item.getSaleUnit())) {
                drug.setSaleUnit(7);
            } else {
                drug.setSaleUnit(0);
            }
            drugList.add(drug);
        });
        saveBatch(drugList);
    }

    @Override
    public void saveBatchFromFullModel(List<DrugFullModel> modelList) {
        /*Map<String, Drug> drugMap = new HashMap<>();
        Set<String> snSet = new HashSet<>();
        modelList.forEach(item -> snSet.add(item.getInsuranceSn()));
        List<Drug> drugList = drugRepository.findByInsuranceSnIn(snSet);
        if (CheckUtils.isEmpty(drugList)) {
            return;
        }
        drugList.forEach(drug -> drugMap.put(drug.getInsuranceSn(), drug));
        List<Drug> saveList = new ArrayList<>();
        modelList.forEach(item -> {
            Drug drug = drugMap.get(item.getInsuranceSn());
            if (drug == null) return;
            saveList.add(drug);
        });
        saveBatch(saveList);*/
    }
}
