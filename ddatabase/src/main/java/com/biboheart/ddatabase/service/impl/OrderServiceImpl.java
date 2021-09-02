package com.biboheart.ddatabase.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.ddatabase.entity.Items;
import com.biboheart.ddatabase.entity.Order;
import com.biboheart.ddatabase.repository.ItemsRepository;
import com.biboheart.ddatabase.repository.OrderRepository;
import com.biboheart.ddatabase.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemsRepository itemsRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ItemsRepository itemsRepository) {
        this.orderRepository = orderRepository;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public Order load(String orderSn) {
        return orderRepository.findByOrderSn(orderSn);
    }

    @Override
    public Map<String, Double> apply(String orderSn) {
        Order order = orderRepository.findByOrderSn(orderSn);
        List<Integer> stateList = Integer.valueOf(1).equals(order.getClassify()) ? Arrays.asList(1, 5) : Arrays.asList(1, 5, 9);
        List<Items> itemsList = itemsRepository.findByOrderSnAndStateIn(orderSn, stateList);
        /*Map<String, Long> longMap = new HashMap<>();
        Map<String, List<Items>> listMap = new HashMap<>();
        for (Items items : itemsList) {
            if (CheckUtils.isEmpty(items.getPayment())) continue;
            String type = items.getChargeTypeName();
            Long val = longMap.get(type);
            if (null == val) val = 0L;
            val += items.getPayment();
            longMap.put(type, val);
            List<Items> items1 = listMap.computeIfAbsent(type, k -> new ArrayList<>());
            items1.add(items);
        }
        Map<String, List<Items>> map = itemsList.stream().collect(Collectors.groupingBy(Items::getChargeTypeName));
        Map<String, Long> longMap2 = new HashMap<>();
        Map<String, Double> paymentMap = new HashMap<>();
        for (String key : map.keySet()) {
            List<Items> mapItemsList = map.get(key);
            if (CheckUtils.isEmpty(mapItemsList)) {
                continue;
            }
            long val = 0;
            BigDecimal totalPrice = new BigDecimal(0);
            for (Items orderItems : mapItemsList) {
                val += orderItems.getPayment();
                BigDecimal payment = new BigDecimal(orderItems.getPayment()).divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP);
                totalPrice = totalPrice.add(payment);
            }
            longMap2.put(key, val);
            paymentMap.put(key, totalPrice.doubleValue());
        }
        double total1 = 0.0;
        double total2 = 0.0;
        double total3 = 0.0;
        for (String key : longMap.keySet()) {
            total1 += new BigDecimal(longMap.get(key)).divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP).doubleValue();
            total2 += new BigDecimal(longMap2.get(key)).divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP).doubleValue();
            total3 += paymentMap.get(key);
        }
        System.out.println("1:" + total1 + ";2:" + total2 + ";3:" + total3);*/
        /*Map<String, Double> paymentMap = new HashMap<>();
        for (String key : longMap.keySet()) {
            paymentMap.put(key, new BigDecimal(longMap.get(key)).divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP).doubleValue());
        }*/
        Map<String, List<Items>> map = itemsList.stream().collect(Collectors.groupingBy(Items::getChargeTypeName));
        Map<String, Double> paymentMap = new HashMap<>();
        for (String key : map.keySet()) {
            List<Items> mapItemsList = map.get(key);
            if (CheckUtils.isEmpty(mapItemsList)) {
                continue;
            }
            BigDecimal totalPrice = new BigDecimal(0);
            for (Items orderItems : mapItemsList) {
                BigDecimal payment = new BigDecimal(orderItems.getPayment()).divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP);
                totalPrice = totalPrice.add(payment);
            }
            paymentMap.put(key, totalPrice.doubleValue());
        }
        double total = 0.0;
        for (String key : paymentMap.keySet()) {
            total += paymentMap.get(key);
        }
        System.out.println(total);
        return paymentMap;
    }
}
