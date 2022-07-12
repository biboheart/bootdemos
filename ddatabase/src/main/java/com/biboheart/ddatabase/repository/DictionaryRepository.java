package com.biboheart.ddatabase.repository;

import com.biboheart.ddatabase.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
    List<Dictionary> findByCatalogSn(String catalogSn);
}
