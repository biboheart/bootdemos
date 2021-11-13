package com.biboheart.dweixin.controller;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dweixin.controller.model.BridgeWechatErrorMessage;
import com.biboheart.dweixin.service.WechatService;
import com.biboheart.dweixin.utils.RequestUtils;
import com.biboheart.dweixin.wechat.model.WechatTmpInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信公众号桥接，完成微信公众号连接、回调、跳转等工作
 */
@Slf4j
@Controller
@RequestMapping(value = "/wechat/bridge")
public class WechatBridgeController {
    private final WechatService wechatService;
    private final HttpServletRequest request;
    private final HttpSession session;

    private final String ERROR_PAGE_URL = "/wechat/bridge/error/";
    private final String CALLBACK_PAGE_URL = "/wechat/bridge/callback";

    public WechatBridgeController(WechatService wechatService, HttpServletRequest request, HttpSession session) {
        this.wechatService = wechatService;
        this.request = request;
        this.session = session;
    }

    /**
     * 错误结果页面，在微信公众号桥接过程中，返回错误界面
     * @param ecode 错误码，错误码对应的描述查看BridgeWechatErrorMessage类
     * @return 页面模型
     */
    @RequestMapping(value = "/error/{ecode}", method = {RequestMethod.GET})
    public String errorPage(@PathVariable Integer ecode, Model model) {
        model.addAttribute("errmsg", BridgeWechatErrorMessage.getMessage(ecode));
        return "bridgeerror";
    }

    /**
     * 为获取微信openid架桥
     * @param redirect_uri 获取openid完成后跳转地址
     * @param scope 应用授权作用域，snsapi_base（不弹出授权页面，直接跳转，只能获取用户openid，不能获取用户信息）
     *              snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     */
    @RequestMapping(value = "/redirect", method = {RequestMethod.GET})
    public String redirect(String redirect_uri, String scope) {
        log.info("请求跳转到:{}", redirect_uri);
        // 获取请求服务的地址
        String baseUrl = RequestUtils.baseUrl(request);
        // 错误页面，如果出错跳转到错误页面
        String errUrl = baseUrl + ERROR_PAGE_URL;
        // 生成code回调链接
        if (CheckUtils.isEmpty(redirect_uri)) {
            return "redirect:" + errUrl + BridgeWechatErrorMessage.NoneRedirectUriError;
        }
        // 回调URL转码
        String callbackUrl;
        try {
            callbackUrl = URLEncoder.encode(baseUrl + CALLBACK_PAGE_URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "redirect:" + errUrl + BridgeWechatErrorMessage.EncodeUrlError;
        }
        String codeUrl = wechatService.codeUrl(callbackUrl, redirect_uri, scope);
        log.info("跳转到：{}", codeUrl);
        return "redirect:" + codeUrl;
    }

    @RequestMapping(value = "/callback", method = {RequestMethod.GET})
    public String callback(String code, String state) {
        log.info(this.getClass().getName() + "---- code:" + code + ";state:" + state);
        // 获取请求服务的地址
        String baseUrl = RequestUtils.baseUrl(request);
        // 错误页面，如果出错跳转到错误页面
        String errUrl = baseUrl + ERROR_PAGE_URL;
        if (CheckUtils.isEmpty(state)) {
            return "redirect:" + errUrl + BridgeWechatErrorMessage.WechatCodeResultErr;
        }
        WechatTmpInfo tmpInfo = wechatService.supplementTmpInfo(state, code);
        if (null == tmpInfo) {
            return "redirect:" + errUrl + BridgeWechatErrorMessage.LoadOpenidError;
        }
        log.info("获取到用户openid: {}", tmpInfo.getOpenid());
        session.setAttribute("openid", tmpInfo.getOpenid());
        return "redirect:" + tmpInfo.getRedirectUri();
    }
}
