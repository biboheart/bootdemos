package com.biboheart.dhttpclient.service.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.MapUtils;
import com.biboheart.dhttpclient.domain.InsuranceDataService;
import com.biboheart.dhttpclient.domain.InsuranceService;
import com.biboheart.dhttpclient.service.PatientService;
import com.biboheart.dhttpclient.service.datas.ResultDataGenerator;
import com.biboheart.dhttpclient.service.model.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final InsuranceService insuranceService;
    private final InsuranceDataService insuranceDataService;

    //private final String hosLocation = "3307";

    public PatientServiceImpl(InsuranceService insuranceService, InsuranceDataService insuranceDataService) {
        this.insuranceService = insuranceService;
        this.insuranceDataService = insuranceDataService;
    }

    @Override
    public ResultData patientInfo() throws BhException {
        // 读取卡片信息
        String card = insuranceService.readCard();
        String[] cardArr = card.split("\\|");
        String cardNumber = cardArr[2] + "|" + cardArr[3];
        // 判断医保类型，暂时只走异地医保
        /*Integer route = 1;
        if (!card.substring(0, 4).equals(hosLocation)) {
            route = 2;
        }*/
        // 获取医保信息
        Map<String, Object> input = new HashMap<>();
        input.put("6", cardNumber);
        input.put("15", cardArr[1]);
        input.put("16", cardArr[4]);
        String data1 = insuranceDataService.packageData("9201", input);
        return ResultDataGenerator.patientInfo(insuranceService.patientInfo(data1));
    }

    @Override
    public ResultData outpatientPreSettlement(Map<String, Object> patient) throws BhException {
        String cardNumber = MapUtils.getStringValue(patient, "cardNumber") + "|" + MapUtils.getStringValue(patient, "informationDotransfer");
        Map<String, Object> input = new HashMap<>();
        input.put("6", cardNumber);
        // 1. 查询订单
        // 2. 查询订单明细
        // 3. 转换入参
        String data1 = insuranceDataService.packageData("9202", input);
        System.out.println(data1);
        // 向医保发出请求，解析结果
        // String result = insuranceService.request("9202", data1);
        return null;
    }
}
