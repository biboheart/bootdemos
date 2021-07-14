package com.biboheart.ddatabase.repository;

import com.biboheart.ddatabase.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>  {
    List<Order> findByEndTimeBetween(Long start, Long end);
}
