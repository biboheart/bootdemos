package com.biboheart.ddatabase.repository;

import com.biboheart.ddatabase.entity.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Long> {
    List<AddressInfo> findByNumberIn(Collection<String> numberList);
}
