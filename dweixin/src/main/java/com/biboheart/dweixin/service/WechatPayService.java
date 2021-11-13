package com.biboheart.dweixin.service;

import com.biboheart.dweixin.pay.model.OrderRequest;

public interface WechatPayService {
    String apply(OrderRequest order);
}
