package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {
    List<Home> findByHospitalizedSnIn(Collection<String> hospitalizedSn);
}
