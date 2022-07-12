package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.QualityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface QualityInfoRepository extends JpaRepository<QualityInfo, Long> {
    List<QualityInfo> findByNumberIn(Collection<String> numberList);
}
