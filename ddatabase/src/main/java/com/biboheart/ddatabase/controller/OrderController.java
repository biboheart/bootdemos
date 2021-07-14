package com.biboheart.ddatabase.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ddatabase.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/order/list", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(Long id) {
//        orderRepository.findByEndTimeBetween("1624982400000", "1625068799000");
        return new BhResponseResult<>(0, "success", orderRepository.findByEndTimeBetween(1624982400000L, 1625068799000L));
    }
}
