package com.biboheart.dexcel.repository;

import com.biboheart.dexcel.entity.ExpirationTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpirationTimeRepository extends JpaRepository<ExpirationTime, Long> {
    ExpirationTime findByBatchNumberAndReservedField(String batchNumber, String reservedField);
}
