package com.biboheart.dwechat.wechat.model;

import lombok.Data;

@Data
public class UnifiedOrderParams {
    private String appid; // 公众账号ID,微信支付分配的公众账号ID（企业号corpid即为此appId）
    private String mchId; // 商户号,微信支付分配的商户号
    private String deviceInfo; // 可空，自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
    private String nonceStr; // 随机字符串，长度要求在32位以内。
    private String sign; // 签名, 通过签名算法计算得出的签名值
    private String signType; // 可空，签名类型, 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
    private String body; // 商品描述, 商品简单描述，该字段请按照规范传递
    private String detail; // 可空，商品详情， 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传
    private String attach; // 可空，附加数据，附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
    private String outTradeNo; // 商户订单号, 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。
    private String feeType; // 可空，标价币种,符合ISO 4217标准的三位字母代码，默认人民币：CNY
    private Integer totalFee; // 标价金额, 订单总金额，单位为分
    private String spbillCreateIp; // 终端IP,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
    private String timeStart; // 可空，交易起始时间,订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
    private String timeExpire; // 可空，交易结束时间,订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。
    private String goodsTag; // 可空，订单优惠标记,订单优惠标记，使用代金券或立减优惠功能时需要的参数
    private String notifyUrl; // 通知地址,异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
    private String tradeType; // 交易类型, JSAPI -JSAPI支付 NATIVE -Native支付 APP -APP支付 MWEB--H5支付 MICROPAY--付款码支付
    private String productId; // 可空，商品ID,trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
    private String limitPay; // 可空，指定支付方式,上传此参数no_credit--可限制用户不能使用信用卡支付
    private String openid; // 可空，用户标识,trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
    private String receipt; // 可空，电子发票入口开放标识,Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
    private String sceneInfo; // 可空，场景信息,该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }}
}
