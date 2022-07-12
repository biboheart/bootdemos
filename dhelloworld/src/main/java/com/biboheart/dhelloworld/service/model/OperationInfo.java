package com.biboheart.dhelloworld.service.model;

import lombok.Data;

@Data
public class OperationInfo {
    private Integer jzid; //
    private Integer ssjlid; // a.rab01 ssid, -- 手术记录id
    private String ssjczbm; // a.bak02 ssjczbm, -- 手术及操作编码 手术icd编码
    private Long ssjczrq; // a.rab03 ssjczrq, -- 手术及操作日期
    private String ssjb; // a.ach01 ssjb, -- 手术级别
    private String ssjczmc; // a.bak05 ssjczmc, -- 手术及操作名称
    private String sz; // a.bce03a sz, -- 主刀
    private String yz; // a.bce03b yz, -- i助
    private String ez; // a.bce03c ez, -- ii助
    private String qkdj; // a.aab01 qkdj, -- 切口等级
    private String qkyhlb; // a.abd01 qkylb,	-- 切口愈合类别 切口愈合等级
    private String mzfs; // a.rab09 mzfs, -- 麻醉方式
    private String mzys; // a.bce03c mzys -- 麻醉医生
}
