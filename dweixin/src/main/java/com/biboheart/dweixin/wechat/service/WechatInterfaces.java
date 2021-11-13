package com.biboheart.dweixin.wechat.service;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.brick.utils.MapUtils;
import com.biboheart.dweixin.utils.StringUtils;
import com.biboheart.dweixin.wechat.model.WechatAccessToken;
import com.biboheart.dweixin.wechat.model.WechatButton;
import com.biboheart.dweixin.wechat.model.WechatUserAccessToken;
import com.biboheart.dweixin.wechat.model.WechatUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WechatInterfaces {
    private final RestTemplate restTemplate;

    public WechatInterfaces(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取access_token
     */
    public WechatAccessToken loadToken(String appid, String secret) {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", appid);
        map.put("secret", secret);
        String url = StringUtils.replace("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={[appid]}&secret={[secret]}", map);
        log.info("获取微信access_token, {}", url);
        String response = restTemplate.getForObject(url, String.class);
        return (WechatAccessToken) JsonUtils.json2obj(response, WechatAccessToken.class);
    }

    /**
     * 创建菜单
     */
    @SuppressWarnings("unchecked")
    public boolean createMenu(String access_token, List<WechatButton> buttons) {
        if (CheckUtils.isEmpty(access_token)) {
            return false;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
        Map<String, Object> message = new HashMap<>();
        message.put("button", buttons);
        String body = JsonUtils.obj2json(message);
        boolean success = false;
        Map<String, Object> result = restTemplate.postForObject(url, body, HashMap.class);
        if (!CheckUtils.isEmpty(result) && CheckUtils.isEmpty(MapUtils.getIntValue(result, "errcode"))) {
            success = true;
        }
        return success;
    }

    /**
     * 删除菜单
     */
    @SuppressWarnings("unchecked")
    public boolean deleteMenu(String access_token) {
        if (CheckUtils.isEmpty(access_token)) {
            return false;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + access_token;
        boolean success = false;
        Map<String, Object> result = restTemplate.getForObject(url, HashMap.class);
        if (!CheckUtils.isEmpty(result) && CheckUtils.isEmpty(MapUtils.getIntValue(result, "errcode"))) {
            success = true;
        }
        return success;
    }

    public String codeUrl(String appid, String redirectUri, String state, String scope) {
        // https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=" + scope +
                "&state=" + state +
                "#wechat_redirect";
    }

    public WechatUserAccessToken userAccessToken(String appid, String secret, String code) {
        if (CheckUtils.isEmpty(code) || CheckUtils.isEmpty(secret) || CheckUtils.isEmpty(appid)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("appid", appid);
        map.put("secret", secret);
        map.put("code", code);
        // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String url = StringUtils.replace("https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid={[appid]}&secret={[secret]}&code={[code]}&grant_type=authorization_code", map);
        log.info("请求微信access_token:" + url);
        String response = restTemplate.getForObject(url, String.class);
        return (WechatUserAccessToken) JsonUtils.json2obj(response, WechatUserAccessToken.class);
    }

    public WechatUserInfo loadUserInfo(String accessToken, String openid) {
        if (CheckUtils.isEmpty(accessToken) || CheckUtils.isEmpty(openid)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        // https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        String url = StringUtils.replace("https://api.weixin.qq.com/sns/userinfo?access_token={[access_token]}&openid={[openid]}&lang=zh_CN", map);
        log.info("请求微信用户信息:" + url);
        String response = restTemplate.getForObject(url, String.class);
        log.info("请求微信用户信息结果:" + response);
        return (WechatUserInfo) JsonUtils.json2obj(response, WechatUserInfo.class);
    }
}
