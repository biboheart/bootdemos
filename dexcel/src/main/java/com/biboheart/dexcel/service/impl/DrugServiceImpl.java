package com.biboheart.dexcel.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dexcel.entity.Drug;
import com.biboheart.dexcel.model.DrugFullModel;
import com.biboheart.dexcel.model.DrugModel;
import com.biboheart.dexcel.repository.DrugRepository;
import com.biboheart.dexcel.service.DrugService;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;

import java.util.*;

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
            drug.setInsuranceSn(item.getInsuranceSn());
            drug.setReimbursementType(item.getReimbursementType());
            drug.setName(item.getName());
            drug.setCommonName(item.getCommonName());
            drug.setForm(item.getForm());
            drug.setPlaceOrigin(item.getPlaceOrigin());
            drug.setSpecifications(item.getSpecifications());
            drug.setPackageUnit(item.getPackageUnit());
            drugList.add(drug);
        });
        saveBatch(drugList);
    }

    @Override
    public void saveBatchFromFullModel(List<DrugFullModel> modelList) {
        Map<String, Drug> drugMap = new HashMap<>();
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
            if (CheckUtils.isEmpty(drug.getCommonName())) {
                drug.setCommonName(item.getCommonName());
            }
            if (CheckUtils.isEmpty(drug.getForm())) {
                drug.setForm(item.getForm());
            }
            if (CheckUtils.isEmpty(drug.getPlaceOrigin())) {
                drug.setPlaceOrigin(item.getPlaceOrigin());
            }
            if (CheckUtils.isEmpty(drug.getSpecifications())) {
                drug.setSpecifications(item.getSpecifications());
            }
            drug.setStandardSn(item.getStandardSn());
            drug.setTypeSn(item.getTypeSn());
            drug.setApprovalNumber(item.getApprovalNumber());
            drug.setBasicUnit(item.getBasicUnit());
            drug.setPackageUnit(item.getPackageUnit());
            drug.setConversionCoefficient(item.getConversionCoefficient());
            saveList.add(drug);
        });
        saveBatch(saveList);
    }
}
