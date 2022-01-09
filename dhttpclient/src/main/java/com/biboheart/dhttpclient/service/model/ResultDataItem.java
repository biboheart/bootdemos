package com.biboheart.dhttpclient.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultDataItem {
    private String sourceName;  // 结果名称
    private String name;        // 属性名
    private String desc;        // 描述
    private Object value;       // 值
    private ResultData resultData;  // 深层结构
}
