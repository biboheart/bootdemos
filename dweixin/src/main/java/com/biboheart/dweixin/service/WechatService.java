package com.biboheart.dweixin.service;

import com.biboheart.dweixin.wechat.model.WechatMessage;
import com.biboheart.dweixin.wechat.model.WechatTmpInfo;

public interface WechatService {
    boolean checkSignature(String signature, String timestamp, String nonce);

    String processMessage(WechatMessage wechatMessage);

    String accessToken();

    String codeUrl(String callbackUrl, String redirectUri, String scope);

    WechatTmpInfo supplementTmpInfo(String state, String code);
}
