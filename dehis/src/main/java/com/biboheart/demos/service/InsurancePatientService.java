package com.biboheart.demos.service;

import com.biboheart.brick.exception.BhException;
import com.biboheart.demos.entity.InsurancePatient;

import java.util.Collection;
import java.util.List;

public interface InsurancePatientService {
    InsurancePatient save(InsurancePatient insurancePatient) throws BhException;

    InsurancePatient load(String number);

    List<InsurancePatient> list(Collection<String> inNumber);
}
