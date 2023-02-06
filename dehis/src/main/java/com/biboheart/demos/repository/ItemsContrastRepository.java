package com.biboheart.demos.repository;

import com.biboheart.demos.entity.ItemsContrast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ItemsContrastRepository extends JpaRepository<ItemsContrast, Long>, JpaSpecificationExecutor<ItemsContrast> {
    ItemsContrast findByLocalSn(String localSn);

    List<ItemsContrast> findByType(Integer type);

    @Query("SELECT localSn FROM #{#entityName} WHERE localSn IN ?1")
    List<String> findLocalSn(Collection<String> localSns);

    @Query("SELECT countryNumber FROM #{#entityName} WHERE type = ?1")
    List<String> findCountryNumber(Integer type);
}
