package com.biboheart.demos.service;

import com.biboheart.demos.entity.DictionaryDiagnosis;

import java.util.Collection;
import java.util.List;

public interface DictionaryDiagnosisService {
    DictionaryDiagnosis load(String icd);
    List<DictionaryDiagnosis> list(Collection<String> inIcd);
}
