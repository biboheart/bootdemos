package com.biboheart.dhelloworld.service.model;

import lombok.Data;

@Data
public class CostInfo {
    private String id;
    private Integer jzid; // 就诊ID
    private Double je; // 金额
    private String fm; // 费目
}
