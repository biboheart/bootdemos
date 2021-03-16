package com.biboheart.dwechat.wechat.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@SuppressWarnings("serial")
public class QueryOrderResult implements Serializable {
    @XStreamAlias("return_code")
    private String code; // 返回状态码SUCCESS/FAIL
    @XStreamAlias("return_msg")
    private String message; // 返回信息,当return_code为FAIL时返回信息为错误原因
    @XStreamAlias("appid")
    private String appid; // 公众账号ID,调用接口提交的公众账号ID
    @XStreamAlias("mch_id")
    private String mchId; // 商户号
    @XStreamAlias("nonce_str")
    private String nonceStr; // 随机字符串,微信返回的随机字符串
    @XStreamAlias("sign")
    private String sign; // 签名
    @XStreamAlias("result_code")
    private String resultCode; // 业务结果SUCCESS/FAIL
    @XStreamAlias("err_code")
    private String errCode; // 错误代码,当result_code为FAIL时返回错误代码
    @XStreamAlias("err_code_des")
    private String errCodeDes; // 错误代码描述
    @XStreamAlias("device_info")
    private String deviceInfo; // 设备号,自定义参数，可以为请求支付的终端设备号等
    @XStreamAlias("openid")
    private String openid; // 用户在商户appid下的唯一标识
    @XStreamAlias("is_subscribe")
    private String isSubscribe; // 用户是否关注公众账号，Y-关注，N-未关注
    @XStreamAlias("trade_type")
    private String tradeType; // 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY
    /**
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（付款码支付）
     * USERPAYING--用户支付中（付款码支付）
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    @XStreamAlias("trade_state")
    private String tradeState; // 支付状态机请见下单API页面
    @XStreamAlias("bank_type")
    private String bankType; // 付款银行,银行类型，采用字符串类型的银行标识
    @XStreamAlias("total_fee")
    private Integer totalFee; // 订单总金额，单位为分
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee; // 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
    @XStreamAlias("fee_type")
    private String feeType; // 标价币种,货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
    @XStreamAlias("cash_fee")
    private Integer cashFee; // 现金支付金额订单现金支付金额
    @XStreamAlias("cash_fee_type")
    private String cashFeeType; // 现金支付币种 ,货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
    @XStreamAlias("transaction_id")
    private String transactionId; // 微信支付订单号
    @XStreamAlias("out_trade_no")
    private String outTradeNo; // 商户系统内部订单号
    @XStreamAlias("attach")
    private String attach; // 附加数据，原样返回
    @XStreamAlias("time_end")
    private String timeEnd; // 支付完成时间,格式为yyyyMMddHHmmss
    @XStreamAlias("trade_state_desc")
    private String tradeStateDesc; // 对当前查询订单状态的描述和下一步操作的指引
    @XStreamAlias("coupon_fee")
    private Integer couponFee; // 代金券金额,“代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额
    @XStreamAlias("coupon_count")
    private Integer couponCount; // 代金券使用数量
}
