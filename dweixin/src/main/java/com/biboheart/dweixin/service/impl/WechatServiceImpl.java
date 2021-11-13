package com.biboheart.dweixin.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dweixin.datas.WechatDatas;
import com.biboheart.dweixin.service.WechatService;
import com.biboheart.dweixin.wechat.exception.AesException;
import com.biboheart.dweixin.wechat.model.*;
import com.biboheart.dweixin.wechat.service.WechatInterfaces;
import com.biboheart.dweixin.wechat.utils.WechatEncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class WechatServiceImpl implements WechatService {
    private final WechatInterfaces wechatInterfaces;
    private final WechatDatas wechatDatas;

    @Value("${wechat.mp.token}")
    private String wechatToken;
    @Value("${wechat.mp.appid}")
    private String wechatAppid;
    @Value("${wechat.mp.secret}")
    private String wechatSecret;

    public WechatServiceImpl(WechatInterfaces wechatInterfaces, WechatDatas wechatDatas) {
        this.wechatInterfaces = wechatInterfaces;
        this.wechatDatas = wechatDatas;
    }

    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String data = "";
        try {
            data = WechatEncryptionUtils.verifyUrl(signature, timestamp, nonce, null, wechatToken, wechatAppid, wechatSecret);
        } catch (AesException e) {
            e.printStackTrace();
            return false;
        }
        // 与signature(微信传来的加密签名)比较
        return data.equals(signature);
    }

    @Override
    public String processMessage(WechatMessage wechatMessage) {
        long now = TimeUtils.getCurrentTimeInMillis();
        switch(wechatMessage.getMsgType()) {
            case "text":
                log.info("{}向{}发了文本消息", wechatMessage.getFromUserName(), wechatMessage.getToUserName());
                break;
            case "image":
                log.info("{}向{}发了图片消息", wechatMessage.getFromUserName(), wechatMessage.getToUserName());
                break;
            case "event":
                switch(wechatMessage.getEvent()) {
                    case "subscribe":
                        log.info("{}向{}发了关注事件", wechatMessage.getFromUserName(), wechatMessage.getToUserName());
                        break;
                    case "SCAN":
                        log.info("{}向{}发了扫码事件", wechatMessage.getFromUserName(), wechatMessage.getToUserName());
                        break;
                    case "unsubscribe":
                        log.info("{}向{}发了取消关注事件", wechatMessage.getFromUserName(), wechatMessage.getToUserName());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return "success";
    }

    @Override
    public String accessToken() {
        WechatAccessToken wat = wechatInterfaces.loadToken(wechatAppid, wechatSecret);
        return wat.toString();
    }

    @Override
    public String codeUrl(String callbackUrl, String redirectUri, String scope) {
        if (CheckUtils.isEmpty(scope)) {
            scope = "snsapi_base";
        }
        String state = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        WechatTmpInfo wechatTmpInfo = new WechatTmpInfo();
        wechatTmpInfo.setState(state);
        wechatTmpInfo.setRedirectUri(redirectUri);
        wechatTmpInfo.setScope(scope);
        wechatDatas.saveTmpInfo(wechatTmpInfo);
        return wechatInterfaces.codeUrl(wechatAppid, callbackUrl, state, scope);
    }

    @Override
    public WechatTmpInfo supplementTmpInfo(String state, String code) {
        WechatTmpInfo wechatTempInfo = wechatDatas.getTmpInfo(state);
        if (null == wechatTempInfo) {
            return null;
        }
        WechatTmpInfo tmp = new WechatTmpInfo();
        BeanUtils.copy(wechatTempInfo, tmp, null, null);
        wechatDatas.deleteTmpInfo(state);
        WechatUserAccessToken userAccessToken = wechatInterfaces.userAccessToken(wechatAppid, wechatSecret, code);
        if (null == userAccessToken) {
            return null;
        }
        tmp.setOpenid(userAccessToken.getOpenid());
        String redirectUri = tmp.getRedirectUri();
        tmp.setRedirectUri(redirectUri.replace("{[wechatsn]}", userAccessToken.getOpenid()));
        if ("snsapi_userinfo".equals(tmp.getScope())) {
            WechatUserInfo userInfo = wechatInterfaces.loadUserInfo(userAccessToken.getAccessToken(), userAccessToken.getOpenid());
            if (null != userInfo) {
                if (CheckUtils.isEmpty(userInfo.getOpenid())) {
                    userInfo.setOpenid(userAccessToken.getOpenid());
                }
                wechatDatas.saveUserInfo(userInfo);
            }
        }
        return tmp;
    }
}
