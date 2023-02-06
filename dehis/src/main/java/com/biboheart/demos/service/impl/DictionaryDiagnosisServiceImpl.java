package com.biboheart.demos.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.demos.entity.DictionaryDiagnosis;
import com.biboheart.demos.repository.DictionaryDiagnosisRepository;
import com.biboheart.demos.service.DictionaryDiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryDiagnosisServiceImpl implements DictionaryDiagnosisService {
    private final DictionaryDiagnosisRepository dictionaryDiagnosisRepository;

    @Override
    public DictionaryDiagnosis load(String icd) {
        if (CheckUtils.isEmpty(icd)) {
            return null;
        }
        return dictionaryDiagnosisRepository.findByIssuerAndIcd("医保版", icd);
    }

    @Override
    public List<DictionaryDiagnosis> list(Collection<String> inIcd) {
        if (CheckUtils.isEmpty(inIcd)) {
            return null;
        }
        return dictionaryDiagnosisRepository.findByIssuerAndIcdIn("医保版", inIcd);
    }
}
