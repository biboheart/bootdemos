package com.biboheart.dexcel.service;

import com.biboheart.dexcel.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    Diagnosis save(Diagnosis diagnosis);

    void saveBatch(List<Diagnosis> diagnosisList);
}
