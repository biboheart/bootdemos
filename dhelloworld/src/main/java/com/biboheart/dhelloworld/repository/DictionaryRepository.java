package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
    List<Dictionary> findByCatalogSn(String catalogSn);
}
