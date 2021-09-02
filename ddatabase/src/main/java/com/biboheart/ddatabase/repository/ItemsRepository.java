package com.biboheart.ddatabase.repository;

import com.biboheart.ddatabase.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Long>, JpaSpecificationExecutor<Items> {
    List<Items> findByOrderSnAndStateIn(String orderSn, List<Integer> stateList);
}
