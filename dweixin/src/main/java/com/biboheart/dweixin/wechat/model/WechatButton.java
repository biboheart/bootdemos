package com.biboheart.dweixin.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 公众号菜单
 */
@Data
public class WechatButton {
    private String type;    // view表示网页类型，click表示点击类型，miniprogram表示小程序类型
    private String name;
    private String key;
    private String url; // view、miniprogram类型必须
    @JsonProperty("media_id")
    private String mediaId;
    private String appid;   // miniprogram类型必须
    private String pagepath;    // miniprogram类型必须
    @JsonProperty("article_id")
    private String articleId;    // article_id类型和article_view_limited类型必须
    @JsonProperty("sub_button")
    private List<WechatButton> subButton;
}
