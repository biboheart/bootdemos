package com.biboheart.dexcel.repository;

import com.biboheart.dexcel.entity.SerialDetailsForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerialDetailsFormRepository extends JpaRepository<SerialDetailsForm, Long> {
    List<SerialDetailsForm> findBySerialIdAndItemsSnNotNull(Long serialId);
}
