package com.biboheart.dhelloworld.service.model;

import lombok.Data;

@Data
public class DiagnoseInfo {
    private Integer jzid; //
    private String zdlx; // select isnull(case vao11 when 3 then '其他诊断' when 5 then '医院感染' when 7 then '损伤中毒'when 10 then '并发症' end,'-') 诊断类型,
    private Integer rybq; // a.vao24 入院病情
    private String cyqkid; // a.abx01 出院情况id
    private String icd10; // vao15  icd10
    private String jbbm; // b.bak02 疾病编码
    private String bak05a;
    private Integer acf01; //  -- 1=门诊, 2=住院, 3=病案, 关联字段:acf1.acf01
    private Integer vao10; // 	-- 0=西医, 1=中医
    private Integer vao11; //  -- 1=门诊诊断, 2=入院诊断, 3=出院诊断, 4=, 5=医院感染, 6=病理诊断, 7=损伤中毒, 8=术前诊断, 9=术后诊断, 10=并发症, 11=病原学诊断，12＝尸检主要诊断
    private Integer vao25; //  -- 1主要诊断 2 次要诊断
    private Integer vao07; // 0=初步诊断, 1=正常诊断
    private Integer vao06; // 次序
}
