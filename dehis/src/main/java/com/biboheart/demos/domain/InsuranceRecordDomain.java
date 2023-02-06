package com.biboheart.demos.domain;

import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.collect.model.PatientModel;

import java.util.List;

public interface InsuranceRecordDomain {
    void processHealthData(List<HealthDataModel> healthDataModelList);

    void processPatientData(List<PatientModel> patientModelList);
}
