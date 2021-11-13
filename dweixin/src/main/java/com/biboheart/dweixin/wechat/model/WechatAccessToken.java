package com.biboheart.dweixin.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WechatAccessToken {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("errcode")
    private Integer errcode;
}
