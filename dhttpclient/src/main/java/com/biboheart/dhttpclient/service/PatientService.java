package com.biboheart.dhttpclient.service;

import com.biboheart.brick.exception.BhException;
import com.biboheart.dhttpclient.service.model.ResultData;

import java.util.Map;

public interface PatientService {
    ResultData patientInfo() throws BhException;

    /**
     * 门诊预结算
     * @param patient 患者信息
     * @return
     * @throws BhException
     */
    ResultData outpatientPreSettlement(Map<String, Object> patient) throws BhException;
}
