package com.biboheart.ddatabase.service;

import com.biboheart.ddatabase.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order load(String orderSn);

    Map<String, Double> apply(String orderSn);
}
