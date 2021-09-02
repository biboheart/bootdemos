package com.biboheart.ddatabase.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ddatabase.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(String orderSn) {
        return new BhResponseResult<>(0, "success", orderService.load(orderSn));
    }

    @RequestMapping(value = "/order/apply", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> apply(String orderSn) {
        return new BhResponseResult<>(0, "success", orderService.apply(orderSn));
    }
}
