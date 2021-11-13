package com.biboheart.dexcel.repository;

import com.biboheart.dexcel.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByInsuranceSnIn(Set<String> snSet);
}
