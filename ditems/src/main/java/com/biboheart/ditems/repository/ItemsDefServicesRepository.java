package com.biboheart.ditems.repository;

import com.biboheart.ditems.entity.ItemsDefServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemsDefServicesRepository extends JpaRepository<ItemsDefServices, Long>, JpaSpecificationExecutor<ItemsDefServices> {
    ItemsDefServices findByTypeSnAndDeptTypeAndDeptSn(String typeSn, Integer deptType, String deptSn);
}
