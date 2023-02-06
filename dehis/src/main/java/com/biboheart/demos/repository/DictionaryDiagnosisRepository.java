package com.biboheart.demos.repository;

import com.biboheart.demos.entity.DictionaryDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DictionaryDiagnosisRepository extends JpaRepository<DictionaryDiagnosis, Integer> {
    DictionaryDiagnosis findByIssuerAndIcd(String issuer, String inIcd);
    List<DictionaryDiagnosis> findByIssuerAndIcdIn(String issuer, Collection<String> inIcd);
}
