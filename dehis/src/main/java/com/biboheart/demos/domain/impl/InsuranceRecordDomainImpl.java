package com.biboheart.demos.domain.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.brick.utils.MapUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.demos.collect.RecordDataUploader;
import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.collect.model.PatientModel;
import com.biboheart.demos.domain.InsuranceRecordDomain;
import com.biboheart.demos.domain.packager.RecordDataPackager;
import com.biboheart.demos.entity.InsurancePatient;
import com.biboheart.demos.service.DictionaryDiagnosisService;
import com.biboheart.demos.service.InsurancePatientService;
import com.biboheart.demos.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceRecordDomainImpl implements InsuranceRecordDomain {
    private final InsurancePatientService insurancePatientService;
    private final DictionaryDiagnosisService dictionaryDiagnosisService;
    private final PersonService personService;
    private final RecordDataUploader recordDataUploader;

    @Override
    public void processHealthData(List<HealthDataModel> healthDataModelList) {
        if (CheckUtils.isEmpty(healthDataModelList)) {
            return;
        }
        for (HealthDataModel healthDataModel : healthDataModelList) {
            String number = healthDataModel.getBAH();
            InsurancePatient insurancePatient = insurancePatientService.load(number);
            if (null == insurancePatient || Integer.valueOf(5).equals(insurancePatient.getState())) {
                log.error("未找到住院号为{}的信息", number);
                continue;
            }
            Map<String, Object> homedData = RecordDataPackager.homeData(healthDataModel, insurancePatient, dictionaryDiagnosisService);
            Map<String, Object> settleData = RecordDataPackager.settleData(healthDataModel, insurancePatient, dictionaryDiagnosisService, personService);
            insurancePatient.setHomeData(JsonUtils.obj2json(homedData));
            insurancePatient.setSettleData(JsonUtils.obj2json(settleData));
            try {
                insurancePatient = insurancePatientService.save(insurancePatient);
            } catch (BhException e) {
                log.error("保存患者{}数据出错", insurancePatient.getPatientName());
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("number", insurancePatient.getNumber());
            map.put("insurancePersonSn", insurancePatient.getInsurancePersonSn());
            map.put("insuranceOrderSn", insurancePatient.getInsuranceOrderSn());
            map.put("type", "1");
            map.put("data", insurancePatient.getHomeData());
            try {
                recordDataUploader.uploadData(map);
            } catch (BhException e) {
                log.error("上传患者{}首页数据出错", insurancePatient.getPatientName());
            }
            map.put("type", "2");
            map.put("data", insurancePatient.getSettleData());
            try {
                recordDataUploader.uploadData(map);
            } catch (BhException e) {
                log.error("上传患者{}结算数据出错", insurancePatient.getPatientName());
            }
        }
    }

    @Override
    public void processPatientData(List<PatientModel> patientModelList) {
        if (CheckUtils.isEmpty(patientModelList)) {
            return;
        }
        List<InsurancePatient> insurancePatientList = new ArrayList<>();
        for (PatientModel patientModel : patientModelList) {
            InsurancePatient insurancePatient = new InsurancePatient();
            insurancePatient.setNumber(patientModel.getNumber());
            insurancePatient.setHospitalizedSn(patientModel.getNumber());
            insurancePatient.setCentreNumber(patientModel.getCentreNumber());
            insurancePatient.setInsuranceOrderSn(patientModel.getInsuranceOrderSn());
            insurancePatient.setInsurancePersonSn(patientModel.getInsurancePersonSn());
            insurancePatient.setPatientName(patientModel.getPatientName());
            /*insurancePatient.setAdmissionTime(TimeUtils.formatDate(null, patientModel.getAdmissionTime()));
            insurancePatient.setLeaveTime(TimeUtils.formatDate(null, patientModel.getLeaveTime()));
            insurancePatient.setSettleTime(TimeUtils.formatDate(null, patientModel.getSettleTime()));*/
            insurancePatientList.add(insurancePatient);
        }
        for (InsurancePatient insurancePatient : insurancePatientList) {
            try {
                insurancePatientService.save(insurancePatient);
            } catch (BhException e) {
                log.error("保存患者{}信息出错", insurancePatient.getPatientName());
            }
        }
    }
}
