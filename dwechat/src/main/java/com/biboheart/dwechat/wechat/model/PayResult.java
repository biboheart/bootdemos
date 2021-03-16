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
public class PayResult implements Serializable {
    @XStreamAlias("return_code")
    private String code; // 返回状态码SUCCESS/FAIL
    @XStreamAlias("return_msg")
    private String message; // 返回信息,当return_code为FAIL时返回信息为错误原因
    @XStreamAlias("appid")
    private String appid; // 公众账号ID,调用接口提交的公众账号ID
    @XStreamAlias("mch_id")
    private String mchId; // 商户号
    @XStreamAlias("device_info")
    private String deviceInfo; // 设备号,自定义参数，可以为请求支付的终端设备号等
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
    @XStreamAlias("trade_type")
    private String tradeType; // 交易类型
    @XStreamAlias("prepay_id")
    private String prepayId; // 预支付交易会话,标识微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
    @XStreamAlias("code_url")
    private String codeUrl; // 二维码链接,trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
    @XStreamAlias("mweb_url")
    private String mwebUrl; // 支付跳转链接,mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
}
