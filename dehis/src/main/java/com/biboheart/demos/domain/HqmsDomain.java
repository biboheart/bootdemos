package com.biboheart.demos.domain;

import com.biboheart.demos.collect.model.HealthDataModel;

import java.util.List;

public interface HqmsDomain {
    void processHealthData(List<HealthDataModel> healthDataModelList);
}
