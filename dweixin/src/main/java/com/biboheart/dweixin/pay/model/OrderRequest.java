package com.biboheart.dweixin.pay.model;

import com.biboheart.brick.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String appid;           // 应用ID
    private String mchid;           // 直连商户号
    private String description;     // 商品描述
    @JsonProperty("out_trade_no")
    private String outTradeNo;      // 商户订单号
    @JsonProperty("time_expire")
    private String timeExpire;      // 交易结束时间
    private String attach;          // 附加数据, 原样返回
    @JsonProperty("notify_url")
    private String notifyUrl;      // 通知地址
    @JsonProperty("goods_tag")
    private String goodsTag;      // 订单优惠标记
    private Integer total;          // 总金额
    private String openid;          // 用户标识
    private Map<String, Object> amount; // 订单金额
    private Map<String, Object> payer;  // 支付者

    public static String toParameter(OrderRequest request) {
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("total", request.getTotal());
        amountMap.put("currency", "CNY");
        request.setAmount(amountMap);
        Map<String, Object> payerMap = new HashMap<>();
        payerMap.put("payer", request.getOpenid());
        request.setPayer(payerMap);
        return JsonUtils.obj2json(request);
    }
}
