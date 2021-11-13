package com.biboheart.dweixin.wechat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatTmpInfo {
    private String state; // 用uuid生成一个标识，识别微信返回code时能对号
    private String code; // 换取access_token的票据，只能用一次
    private String scope; // 应用授权作用域
    private String redirectUri; // 成功获取到微信用户信息后跳转地址
    private String openid; // 微信openid
}
