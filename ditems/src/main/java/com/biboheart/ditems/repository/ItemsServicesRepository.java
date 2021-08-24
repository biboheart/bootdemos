package com.biboheart.ditems.repository;

import com.biboheart.ditems.entity.ItemsServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemsServicesRepository extends JpaRepository<ItemsServices, Long>, JpaSpecificationExecutor<ItemsServices> {
    ItemsServices findByItemsSnAndDeptTypeAndDeptSn(String itemsSn, Integer deptType, String deptSn);
}
