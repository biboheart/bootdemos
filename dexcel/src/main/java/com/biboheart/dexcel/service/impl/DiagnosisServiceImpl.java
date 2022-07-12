package com.biboheart.dexcel.service.impl;

import com.biboheart.dexcel.entity.Diagnosis;
import com.biboheart.dexcel.repository.DiagnosisRepository;
import com.biboheart.dexcel.service.DiagnosisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @Override
    public Diagnosis save(Diagnosis diagnosis) {
        return null;
    }

    @Override
    public void saveBatch(List<Diagnosis> diagnosisList) {
        diagnosisRepository.saveAll(diagnosisList);
    }
}
