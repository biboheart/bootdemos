package com.biboheart.dweixin.datas;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dweixin.wechat.model.WechatTmpInfo;
import com.biboheart.dweixin.wechat.model.WechatUserInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WechatDatas {
    private static final Map<String, WechatTmpInfo> wechatTmpInfos;
    private static final Map<String, WechatUserInfo> wechatUserInfos;

    static {
        wechatTmpInfos = new HashMap<>();
        wechatUserInfos = new HashMap<>();
    }

    public void saveTmpInfo(WechatTmpInfo wechatTmpInfo) {
        putWechatTmpInfo(wechatTmpInfo);
    }

    public void deleteTmpInfo(String state) {
        removeWechatTmpInfo(state);
    }

    public WechatTmpInfo getTmpInfo(String state) {
        return getWechatTmpInfo(state);
    }

    public void saveUserInfo(WechatUserInfo wechatUserInfo) {
        putWechatUserInfo(wechatUserInfo);
    }

    public void deleteUserInfo(String openid) {
        removeWechatUserInfo(openid);
    }

    public WechatUserInfo getUserInfo(String openid) {
        return getWechatUserInfo(openid);
    }

    private static synchronized void putWechatTmpInfo(WechatTmpInfo wechatTmpInfo) {
        if (CheckUtils.isEmpty(wechatTmpInfo.getState())) {
            return;
        }
        WechatDatas.wechatTmpInfos.put(wechatTmpInfo.getState(), wechatTmpInfo);
    }

    private static synchronized void removeWechatTmpInfo(String state) {
        if (CheckUtils.isEmpty(state)) {
            return;
        }
        WechatDatas.wechatTmpInfos.remove(state);
    }

    private static synchronized WechatTmpInfo getWechatTmpInfo(String state) {
        if (CheckUtils.isEmpty(state)) {
            return null;
        }
        return WechatDatas.wechatTmpInfos.get(state);
    }

    private static synchronized void putWechatUserInfo(WechatUserInfo wechatUserInfo) {
        if (CheckUtils.isEmpty(wechatUserInfo.getOpenid())) {
            return;
        }
        WechatDatas.wechatUserInfos.put(wechatUserInfo.getOpenid(), wechatUserInfo);
    }

    private static synchronized void removeWechatUserInfo(String openid) {
        if (CheckUtils.isEmpty(openid)) {
            return;
        }
        WechatDatas.wechatUserInfos.remove(openid);
    }

    private static synchronized WechatUserInfo getWechatUserInfo(String openid) {
        if (CheckUtils.isEmpty(openid)) {
            return null;
        }
        return WechatDatas.wechatUserInfos.get(openid);
    }
}
