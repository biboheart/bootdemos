package com.biboheart.dwechat.wechat.service;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.dwechat.wechat.model.WechatUserAccessToken;
import com.biboheart.dwechat.wechat.model.WechatUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WechatInterfaces {
    private final RestTemplate restTemplate;

    @Value("${wechat.mp.appid:null}")
    private String appid;
    @Value("${wechat.mp.secret:null}")
    private String secret;

    @Autowired
    public WechatInterfaces(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String codeUrl(String redirectUri, String state, String scope) {
        // https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        return "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=" + scope +
                "&state=" + state +
                "#wechat_redirect";
    }

    public WechatUserAccessToken accessToken(String code) {
        if (CheckUtils.isEmpty(code)) {
            return null;
        }
        // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + appid +
                "&secret=" + secret +
                "&code=" + code +
                "&grant_type=authorization_code";
        log.info("请求微信access_token:" + url);
        String response = restTemplate.getForObject(url, String.class);
        return (WechatUserAccessToken) JsonUtils.json2obj(response, WechatUserAccessToken.class);
    }

    public WechatUserInfo loadUserInfo(String accessToken, String openid) {
        if (CheckUtils.isEmpty(accessToken) || CheckUtils.isEmpty(openid)) {
            return null;
        }
        // https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        String url = "https://api.weixin.qq.com/sns/userinfo?" + "access_token=" + accessToken +
                "&openid=" + openid +
                "&lang=zh_CN";
        log.info("请求微信用户信息:" + url);
        String response = restTemplate.getForObject(url, String.class);
        log.info("请求微信用户信息结果:" + response);
        return (WechatUserInfo) JsonUtils.json2obj(response, WechatUserInfo.class);
    }
}
