package com.biboheart.dwechat.service;


import com.biboheart.dwechat.wechat.model.WechatTempInfo;
import com.biboheart.dwechat.wechat.model.WechatUserInfo;

public interface WechatService {
    String codeUrl(String callbackUrl, String redirectUri, String scope);

    WechatTempInfo supplementTimeInfo(String state, String code);

    WechatUserInfo getUserInfo(String openid);

}
