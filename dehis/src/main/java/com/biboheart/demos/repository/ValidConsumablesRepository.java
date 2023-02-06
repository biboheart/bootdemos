package com.biboheart.demos.repository;

import com.biboheart.demos.entity.ValidConsumables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ValidConsumablesRepository extends JpaRepository<ValidConsumables, Long>, JpaSpecificationExecutor<ValidConsumables> {

    @Query("SELECT countryNumber FROM #{#entityName} WHERE countryNumber IN ?1")
    List<String> findCountryNumber(Collection<String> sns);
}
