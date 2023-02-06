package com.biboheart.demos.service.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.demos.entity.InsurancePatient;
import com.biboheart.demos.repository.InsurancePatientRepository;
import com.biboheart.demos.service.InsurancePatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsurancePatientServiceImpl implements InsurancePatientService {
    private final InsurancePatientRepository insurancePatientRepository;

    @Override
    public InsurancePatient save(InsurancePatient insurancePatient) throws BhException {
        if (CheckUtils.isEmpty(insurancePatient.getNumber())) {
            throw new BhException("就诊号不能为空");
        }
        InsurancePatient source = load(insurancePatient.getNumber());
        if (null != source) {
            insurancePatient.setId(source.getId());
        }
        return insurancePatientRepository.save(insurancePatient);
    }

    @Override
    public InsurancePatient load(String number) {
        InsurancePatient insurancePatient = null;
        if (!CheckUtils.isEmpty(number)) {
            insurancePatient = insurancePatientRepository.findByNumber(number);
        }
        return insurancePatient;
    }

    @Override
    public List<InsurancePatient> list(Collection<String> inNumber) {
        if (CheckUtils.isEmpty(inNumber)) {
            return insurancePatientRepository.findAll();
        }
        return insurancePatientRepository.findByNumberIn(inNumber);
    }
}
