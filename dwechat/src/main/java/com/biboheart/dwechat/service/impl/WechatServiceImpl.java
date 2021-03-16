package com.biboheart.dwechat.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dwechat.datas.WechatDatas;
import com.biboheart.dwechat.wechat.model.WechatTempInfo;
import com.biboheart.dwechat.wechat.model.WechatUserAccessToken;
import com.biboheart.dwechat.wechat.model.WechatUserInfo;
import com.biboheart.dwechat.service.WechatService;
import com.biboheart.dwechat.wechat.service.WechatInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WechatServiceImpl implements WechatService {
    private final WechatInterfaces wechatInterfaces;
    private final WechatDatas wechatDatas;

    @Autowired
    public WechatServiceImpl(WechatInterfaces wechatInterfaces, WechatDatas wechatDatas) {
        this.wechatInterfaces = wechatInterfaces;
        this.wechatDatas = wechatDatas;
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
