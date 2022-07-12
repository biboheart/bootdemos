package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.DiagnosisInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DiagnosisInfoRepository extends JpaRepository<DiagnosisInfo, Long> {
    List<DiagnosisInfo> findByNumberIn(Collection<String> numberList);
}
