package com.biboheart.dhttpclient.domain.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.dhttpclient.dock.DockManager;
import com.biboheart.dhttpclient.dock.InsuranceDock;
import com.biboheart.dhttpclient.dock.mode.DiffCodeEnum;
import com.biboheart.dhttpclient.domain.InsuranceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {
    private final DockManager dockManager;
    private final RestTemplate restTemplate;

    public InsuranceServiceImpl(DockManager dockManager, RestTemplate restTemplate) {
        this.dockManager = dockManager;
        this.restTemplate = restTemplate;
    }

    @Override
    public String readCard() throws BhException {
        InsuranceDock insuranceDock = dockManager.getInsuranceDock(2);
        if (insuranceDock == null) {
            throw new BhException("未找到医保接口实现");
        }
        return insuranceDock.readCard(restTemplate);
    }

    @Override
    public String patientInfo(String data1) throws BhException {
        InsuranceDock insuranceDock = dockManager.getInsuranceDock(2);
        if (insuranceDock == null) {
            throw new BhException("未找到医保接口实现");
        }
        return insuranceDock.request(restTemplate, String.valueOf(DiffCodeEnum.DIFF_CODE_9201.valueOf()), data1);
    }

    @Override
    public String request(String code, String data1) throws BhException {
        InsuranceDock insuranceDock = dockManager.getInsuranceDock(2);
        if (insuranceDock == null) {
            throw new BhException("未找到医保接口实现");
        }
        return insuranceDock.request(restTemplate, code, data1);
    }
}
