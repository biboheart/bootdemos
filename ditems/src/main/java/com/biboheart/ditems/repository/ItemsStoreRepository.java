package com.biboheart.ditems.repository;

import com.biboheart.ditems.entity.ItemsStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface ItemsStoreRepository extends JpaRepository<ItemsStore, Integer>, JpaSpecificationExecutor<ItemsStore> {
    ItemsStore findByStoreSnAndItemsSn(String storeSn, String itemsSn);

    List<ItemsStore> findByItemsSn(String itemsSn);

    @Modifying
    @Transactional
    @Query("update #{#entityName} set targetScope = ?2, targetDept = ?3 where typeSn = ?1 and storeSn is null and targetSource = 1")
    void updateByTypeSn(String typeSn, Long targetScope, String targetDept);

    @Modifying
    @Transactional
    @Query("update #{#entityName} set targetScope = ?3, targetDept = ?4 where typeSn = ?1 and storeSn = ?2 and targetSource = 1")
    void updateByTypeSnAndStoreSn(String typeSn, String storeSn, Long targetScope, String targetDept);
}
