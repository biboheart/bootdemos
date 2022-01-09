package com.biboheart.dhttpclient.domain;

import com.biboheart.brick.exception.BhException;

public interface InsuranceService {
    String readCard() throws BhException;

    // 获取人员信息
    String patientInfo(String data1) throws BhException;

    String request(String code, String data1) throws BhException;
}
