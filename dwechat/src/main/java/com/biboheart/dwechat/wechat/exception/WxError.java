package com.biboheart.dwechat.wechat.exception;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.dwechat.wechat.enums.WxCpErrorMsgEnum;
import com.biboheart.dwechat.wechat.enums.WxMpErrorMsgEnum;
import com.biboheart.dwechat.wechat.enums.WxType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信错误码.
 * 请阅读：
 * 公众平台：<a href="http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html">全局返回码说明</a>
 * 企业微信：<a href="https://work.weixin.qq.com/api/doc#10649">全局错误码</a>
 */
@Data
@Builder
public class WxError implements Serializable {
    /**
     * 微信错误代码.
     */
    private int errorCode;

    /**
     * 微信错误信息.
     * （如果可以翻译为中文，就为中文）
     */
    private String errorMsg;

    /**
     * 微信接口返回的错误原始信息（英文）.
     */
    private String errorMsgEn;

    private String json;

    public static WxError fromJson(String json) {
        return fromJson(json, null);
    }

    public static WxError fromJson(String json, WxType type) {
        final WxError wxError = (WxError) JsonUtils.json2obj(json, WxError.class);
        if (!CheckUtils.isEmpty(wxError.getErrorMsg())) {
            wxError.setErrorMsgEn(wxError.getErrorMsg());
        }

        if (type == null) {
            return wxError;
        }

        if (type == WxType.MP) {
            final String msg = WxMpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if (msg != null) {
                wxError.setErrorMsg(msg);
            }
        } else if (type == WxType.CP) {
            final String msg = WxCpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if (msg != null) {
                wxError.setErrorMsg(msg);
            }
        }

        return wxError;
    }

    @Override
    public String toString() {
        if (this.json != null) {
            return this.json;
        }
        return "错误: Code=" + this.errorCode + ", Msg=" + this.errorMsg;
    }
}
