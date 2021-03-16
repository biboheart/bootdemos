package com.biboheart.dwechat.service;

import com.biboheart.dwechat.wechat.exception.AesException;
import com.biboheart.dwechat.wechat.exception.WxPayException;
import com.biboheart.dwechat.wechat.model.*;

public interface WechatPayService {
    PayResult pay(UnifiedOrderParams uop) throws AesException;

    WechatJsPayParams generateJsParams(PayResult payResult) throws AesException;

    WechatJsPayParams generateJsParams(WxPayUnifiedOrderResult payResult) throws AesException;

    QueryOrderResult queryOrder(QueryOrderParams qop) throws AesException;

    WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException;
}
