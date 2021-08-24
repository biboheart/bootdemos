package com.biboheart.ditems.service;

import com.biboheart.ditems.entity.ItemsStore;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemsStoreService {
    ItemsStore save(ItemsStore itemsStore);

    void updateTarget(String typeSn, Long targetScope, String targetDept);
    void updateTarget(String typeSn, String storeSn, Long targetScope, String targetDept);

    ItemsStore load(String storeSn, String itemsSn);

    List<ItemsStore> list(String itemsSn);

    Page<ItemsStore> find(String storeSn);

    Page<ItemsStore> findToBill(String billDept, String patientArea);
}
