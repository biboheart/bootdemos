package com.biboheart.dweixin.service.impl;

import com.biboheart.dweixin.pay.model.OrderRequest;
import com.biboheart.dweixin.service.WechatPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WechatPayServiceImpl implements WechatPayService {
    @Value("${wechat.pay.appid}")
    private String payAppid;
    @Value("${wechat.pay.mchid}")
    private String payMchid;
    @Value("${wechat.pay.key}")
    private String payKey;

    @Override
    public String apply(OrderRequest order) {
        order.setAppid(payAppid);
        order.setMchid(payMchid);
        String orderNo = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        order.setOutTradeNo(orderNo);
        return OrderRequest.toParameter(order);
    }
}
