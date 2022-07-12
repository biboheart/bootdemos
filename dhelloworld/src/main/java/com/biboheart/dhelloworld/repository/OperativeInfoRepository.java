package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.OperativeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OperativeInfoRepository extends JpaRepository<OperativeInfo, Long> {
    List<OperativeInfo> findByNumberIn(Collection<String> numberList);
}
