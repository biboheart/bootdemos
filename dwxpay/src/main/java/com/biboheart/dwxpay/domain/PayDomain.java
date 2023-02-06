package com.biboheart.dwxpay.domain;

import com.biboheart.brick.exception.BhException;

public interface PayDomain {
    // 下单
    Object order() throws BhException;
    // 查询
    Object query(String outTradeNo) throws BhException;
    // 撤销
    Object reverse(String outTradeNo) throws BhException;
    // 退费
    Object refund(String outTradeNo, String sourceOutTradeNo, Long refundAmount, String notifyUrl) throws BhException;
    // 查询退费
    Object refundQuery(String outTradeNo, String sourceOutTradeNo) throws BhException;
}
