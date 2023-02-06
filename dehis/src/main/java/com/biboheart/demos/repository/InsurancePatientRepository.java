package com.biboheart.demos.repository;

import com.biboheart.demos.entity.InsurancePatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface InsurancePatientRepository extends JpaRepository<InsurancePatient, Long> {
    InsurancePatient findByNumber(String number);
    List<InsurancePatient> findByNumberIn(Collection<String> numberList);
}
