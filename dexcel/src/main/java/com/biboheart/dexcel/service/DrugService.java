package com.biboheart.dexcel.service;

import com.biboheart.dexcel.entity.Drug;
import com.biboheart.dexcel.model.DrugFullModel;
import com.biboheart.dexcel.model.DrugModel;

import java.util.List;

public interface DrugService {
    void saveBatch(List<Drug> drugList);

    void saveBatchFromModel(List<DrugModel> modelList);

    void saveBatchFromFullModel(List<DrugFullModel> modelList);
}
