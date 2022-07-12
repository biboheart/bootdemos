package com.biboheart.dexcel.repository;

import com.biboheart.dexcel.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis findByInsuranceSn(String insuranceSn);
}
