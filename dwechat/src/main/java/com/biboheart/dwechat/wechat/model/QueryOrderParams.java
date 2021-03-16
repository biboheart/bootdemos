package com.biboheart.dwechat.wechat.model;

import lombok.Data;

@Data
public class QueryOrderParams {
    private String appid; // 公众账号ID,微信支付分配的公众账号ID（企业号corpid即为此appId）
    private String mchId; // 商户号,微信支付分配的商户号
    private String transactionId; // 微信订单号,微信的订单号，建议优先使用
    private String outTradeNo; // 商户订单号,商户系统内部订单号,与transactionId二选一
    private String nonceStr; // 随机字符串，不长于32位。
    private String sign; // 签名,通过签名算法计算得出的签名值
    private String signType; // 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
}
