package com.biboheart.dwechat.wechat.model;

import lombok.Data;

@Data
public class WechatUserAccessToken {
    private String access_token;
    private Long expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
