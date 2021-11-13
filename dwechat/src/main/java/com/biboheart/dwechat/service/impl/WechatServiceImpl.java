package com.biboheart.dwechat.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dwechat.datas.WechatDatas;
import com.biboheart.dwechat.service.WechatService;
import com.biboheart.dwechat.wechat.exception.AesException;
import com.biboheart.dwechat.wechat.model.WechatMessage;
import com.biboheart.dwechat.wechat.model.WechatTempInfo;
import com.biboheart.dwechat.wechat.model.WechatUserAccessToken;
import com.biboheart.dwechat.wechat.model.WechatUserInfo;
import com.biboheart.dwechat.wechat.service.WechatInterfaces;
import com.biboheart.dwechat.wechat.utils.WechatEncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class WechatServiceImpl implements WechatService {
    @Value("${wechat.mp.token}")
    private String wechatToken;
    @Value("${wechat.mp.appid}")
    private String wechatAppid;
    @Value("${wechat.mp.secret}")
    private String wechatSecret;

    private final WechatInterfaces wechatInterfaces;
    private final WechatDatas wechatDatas;

    @Autowired
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
    public String codeUrl(String callbackUrl, String redirectUri, String scope) {
        if (CheckUtils.isEmpty(scope)) {
            scope = "snsapi_base";
        }
        String state = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        WechatTempInfo wechatTempInfo = new WechatTempInfo();
        wechatTempInfo.setState(state);
        wechatTempInfo.setRedirectUri(redirectUri);
        wechatTempInfo.setScope(scope);
        wechatDatas.saveTempInfo(wechatTempInfo);
        return wechatInterfaces.codeUrl(callbackUrl, state, scope);
    }

    @Override
    public WechatTempInfo supplementTimeInfo(String state, String code) {
        WechatTempInfo wechatTempInfo = wechatDatas.getTempInfo(state);
        if (null == wechatTempInfo) {
            return null;
        }
        WechatTempInfo temp = new WechatTempInfo();
        BeanUtils.copy(wechatTempInfo, temp, null, null);
        wechatDatas.deleteTempInfo(state);
        WechatUserAccessToken accessToken = wechatInterfaces.accessToken(code);
        if (null == accessToken) {
            return null;
        }
        temp.setOpenid(accessToken.getOpenid());
        String redirectUri = temp.getRedirectUri();
        temp.setRedirectUri(redirectUri.replace("{[wechatsn]}", accessToken.getOpenid()));
        if ("snsapi_userinfo".equals(temp.getScope())) {
            WechatUserInfo userInfo = wechatInterfaces.loadUserInfo(accessToken.getAccess_token(), accessToken.getOpenid());
            if (null != userInfo) {
                if (CheckUtils.isEmpty(userInfo.getOpenid())) {
                    userInfo.setOpenid(accessToken.getOpenid());
                }
                wechatDatas.saveUserInfo(userInfo);
            }
        }
        return temp;
    }

    @Override
    public WechatUserInfo getUserInfo(String openid) {
        return wechatDatas.getUserInfo(openid);
    }
}
