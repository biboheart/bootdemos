package com.biboheart.dexcel.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dexcel.entity.ExpirationTime;
import com.biboheart.dexcel.model.ExpirationTimeModel;
import com.biboheart.dexcel.repository.ExpirationTimeRepository;
import com.biboheart.dexcel.service.ExpirationTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExpirationTimeServiceImpl implements ExpirationTimeService {
    private final ExpirationTimeRepository expirationTimeRepository;

    public ExpirationTimeServiceImpl(ExpirationTimeRepository expirationTimeRepository) {
        this.expirationTimeRepository = expirationTimeRepository;
    }

    @Override
    public void save(List<ExpirationTimeModel> expirationTimeModelList) {
        if (CheckUtils.isEmpty(expirationTimeModelList)) {
            return;
        }
        for (ExpirationTimeModel expirationTimeModel : expirationTimeModelList) {
            ExpirationTime expirationTime = new ExpirationTime();
            expirationTime.setBatchNumber(expirationTimeModel.getBatchNumber());
            expirationTime.setReservedField(expirationTimeModel.getReservedField());
            expirationTime.setExpirationTime(TimeUtils.getDateMillis(expirationTimeModel.getExpirationTime(), "yyyy-MM-dd"));
            if (CheckUtils.isEmpty(expirationTime.getBatchNumber()) || CheckUtils.isEmpty(expirationTime.getReservedField()) || CheckUtils.isEmpty(expirationTime.getExpirationTime())) {
                log.info("有数据为空");
                continue;
            }
            if (null != expirationTimeRepository.findByBatchNumberAndReservedField(expirationTime.getBatchNumber(), expirationTime.getReservedField())) {
                continue;
            }
            expirationTimeRepository.save(expirationTime);
        }
    }
}
