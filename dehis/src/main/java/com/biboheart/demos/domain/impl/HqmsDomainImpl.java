package com.biboheart.demos.domain.impl;

import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.domain.HqmsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HqmsDomainImpl implements HqmsDomain {
    @Override
    public void processHealthData(List<HealthDataModel> healthDataModelList) {
        System.out.println(healthDataModelList.size());
    }
}
