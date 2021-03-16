package com.biboheart.dwechat.wechat.model;

import lombok.Data;

import java.util.List;

@Data
public class WechatUserInfo {
    private String openid; // 微信用户的openid
    private String nickname; // 用户昵称
    private Integer sex; // 性别
    private String language; // 语言
    private String province; // 省
    private String city; // 市
    private String country; // 国家
    private String headimgurl; // 头像
    private String unionid; // unionid
    private List<String> privilege; // 用户特权信息
    private Long errcode; // 错误码
    private String errmsg; // 错误描述
}
