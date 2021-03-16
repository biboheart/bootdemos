package com.biboheart.dwechat.datas;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dwechat.wechat.model.WechatTempInfo;
import com.biboheart.dwechat.wechat.model.WechatUserInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WechatDatas {
    private static final Map<String, WechatTempInfo> wechatTempInfos;
    private static final Map<String, WechatUserInfo> wechatUserInfos;

    static {
        wechatTempInfos = new HashMap<>();
        wechatUserInfos = new HashMap<>();
    }

    public void saveTempInfo(WechatTempInfo wechatTempInfo) {
        putWechatTempInfo(wechatTempInfo);
    }

    public void deleteTempInfo(String state) {
        removeWechatTempInfo(state);
    }

    public WechatTempInfo getTempInfo(String state) {
        return getWechatTempInfo(state);
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

    private static synchronized void putWechatTempInfo(WechatTempInfo wechatTempInfo) {
        if (CheckUtils.isEmpty(wechatTempInfo.getState())) {
            return;
        }
        WechatDatas.wechatTempInfos.put(wechatTempInfo.getState(), wechatTempInfo);
    }

    private static synchronized void removeWechatTempInfo(String state) {
        if (CheckUtils.isEmpty(state)) {
            return;
        }
        WechatDatas.wechatTempInfos.remove(state);
    }

    private static synchronized WechatTempInfo getWechatTempInfo(String state) {
        if (CheckUtils.isEmpty(state)) {
            return null;
        }
        return WechatDatas.wechatTempInfos.get(state);
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
