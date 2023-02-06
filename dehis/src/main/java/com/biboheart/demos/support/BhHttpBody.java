package com.biboheart.demos.support;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BhHttpBody {
    private Integer code; // 结果码，对应结果码表
    private String message; // 结果描述
    private Object result; // 结果
}
