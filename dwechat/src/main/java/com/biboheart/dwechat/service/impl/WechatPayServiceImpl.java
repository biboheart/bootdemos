package com.biboheart.dwechat.service.impl;

import com.biboheart.dwechat.service.WechatPayService;
import com.biboheart.dwechat.wechat.exception.AesException;
import com.biboheart.dwechat.wechat.exception.WxPayException;
import com.biboheart.dwechat.wechat.model.*;
import com.biboheart.dwechat.wechat.service.WechatPayInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatPayServiceImpl implements WechatPayService {
    private final WechatPayInterfaces wechatPayInterfaces;

    @Autowired
    public WechatPayServiceImpl(WechatPayInterfaces wechatPayInterfaces) {
        this.wechatPayInterfaces = wechatPayInterfaces;
    }

    @Override
    public PayResult pay(UnifiedOrderParams uop) throws AesException {
        return wechatPayInterfaces.pay(uop);
    }

    @Override
    public WechatJsPayParams generateJsParams(PayResult payResult) throws AesException {
        return wechatPayInterfaces.generateJsParams(payResult);
    }

    @Override
    public WechatJsPayParams generateJsParams(WxPayUnifiedOrderResult payResult) throws AesException {
        return wechatPayInterfaces.generateJsParams(payResult);
    }

    @Override
    public QueryOrderResult queryOrder(QueryOrderParams qop) throws AesException {
        return wechatPayInterfaces.queryOrder(qop);
    }

    @Override
    public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException {
        return wechatPayInterfaces.unifiedOrder(request);
    }
}
