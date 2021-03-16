package com.biboheart.dwechat.wechat.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WechatJsPayParams {
	private String appid; // 公众号id
	private String timeStamp; // 时间戳,当前的时间
	private String nonceStr; // 随机字符串
	private String prepayId; // 订单详情扩展字符串
	private String signType; // 签名方式,签名类型，默认为MD5，支持HMAC-SHA256和MD5。
	private String paySign; // 签名
}
