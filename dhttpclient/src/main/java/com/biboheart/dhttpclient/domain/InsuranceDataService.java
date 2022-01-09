package com.biboheart.dhttpclient.domain;

import com.biboheart.brick.exception.BhException;

import java.util.Map;

public interface InsuranceDataService {
    /**
     * 组装请求的数据
     * @param code 接口编号
     * @param input 请求的数据
     * @return 组装好之后的数据，入参给医保接口
     */
    String packageData(String code, Map<String, Object> input) throws BhException;
}
