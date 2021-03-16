package com.biboheart.demo.doauth.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResponseBody implements Serializable {
    private Integer code;
    private String message;
    private Object result;
    private String jwtToken;
}
