package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Long> {
    List<AddressInfo> findByNumberIn(Collection<String> numberList);
}
