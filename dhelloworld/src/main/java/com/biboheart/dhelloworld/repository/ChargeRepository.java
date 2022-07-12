package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
    List<Charge> findByNumberIn(Collection<String> numberList);
}
