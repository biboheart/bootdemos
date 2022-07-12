package com.biboheart.ddatabase.service;

import lombok.Data;

@Data
public class N041Vo {
    /**
     * Y/N  表示是否可以为空
     */
    private String USERNAME; // N	机构名称 DBF文件中填写病案号，CSV文件中填写机构名称
    private String YLFKFS; // Y	医疗付款方式	代码：医疗付款方式代码
    private String JKKH; // Y	健康卡号
    private String ZYCS; // N	住院次数
    private String BAH; // N	病案号
    private String XM; // Y	姓名
    private String XB; // Y	性别	代码：性别代码
    private String CSRQ; // Y	出生日期	格式为：YYYYMMDD，例如：20131125
    private Integer NL; //	N	年龄
    private String GJ; // Y 国籍 代码：国籍代码
    private String BZYZSNL;
    private Double XSECSTZ; // ; // (12,2)	Y	新生儿出生体重(克)
    private Double XSERYTZ; // ; // (12,2)	Y	新生儿入院体重(克）
    private String CSD; // Y 出生地
    private String GG; // Y	籍贯
    private String MZ; // Y	民族 代码：民族代码
    private String SFZH; // Y 身份证号 如果身份证号码尾号为X，请大写X
    private String ZY; // N	职业 代码：职业代码
    private String HY; // Y	婚姻 代码：婚姻状况代码
    private String XZZ; // Y 现住址
    private String DH; // Y	电话
    private String YB1; // Y 邮编
    private String HKDZ; // Y 户口地址
    private String YB2; // Y 邮编
    private String GZDWJDZ; // Y 工作单位及地址
    private String DWDH; // Y 单位电话
    private String YB3; // Y 邮编
    private String LXRXM; // Y	联系人姓名
    private String GX; // Y	关系 代码：联系人关系代码
    private String DZ; // Y	地址
    private String DH2; // Y 电话
    private String RYTJ; // Y 入院途径	代码：入院途径
    private String RYSJ; // N 入院时间	格式为：YYYYMMDD，例如：20131125
    private Integer RYSJS; // Y	时
    private String RYKB; // Y 入院科别	代码：科室代码
    private String RYBF; // Y 入院病房
    private String ZKKB; // Y 转科科别	代码：科室代码
    private String CYSJ; // N 出院时间	格式为：YYYYMMDD，例如：20131125
    private String CYSJS; // Y	时
    private String CYKB; // Y 出院科别	代码：科室代码
    private String CYBF; // Y 出院病房
    private String SJZYTS; // Y	实际住院(天)
    private String MZZD; // Y	门(急)诊诊断
    private String JBBM; // Y	疾病编码	代码：疾病分类代码
    private String ZYZD; // N	主要诊断
    private String JBDM; // N	疾病编码	代码：疾病分类代码
    private String RYBQ; //  Y	入院病情	代码：入院病情代码
    private String QTZD8; // Y	其他诊断
    private String JBDM8; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ8; // Y	入院病情	代码：入院病情代码
    private String QTZD1; // Y	其他诊断
    private String JBDM1; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ1; // Y	入院病情	代码：入院病情代码
    private String QTZD9; // Y	其他诊断
    private String JBDM9; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ9; // Y	入院病情	代码：入院病情代码
    private String QTZD2; // Y	其他诊断
    private String JBDM2; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ2; // Y	入院病情	代码：入院病情代码
    private String QTZD10; // Y	其他诊断
    private String JBDM10; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ10; // Y	入院病情	代码：入院病情代码
    private String QTZD3; // Y	其他诊断
    private String JBDM3; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ3; // Y	入院病情	代码：入院病情代码
    private String QTZD11; // Y	其他诊断
    private String JBDM11; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ11; // Y	入院病情	代码：入院病情代码
    private String QTZD4; // Y	其他诊断
    private String JBDM4; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ4; // Y	入院病情	代码：入院病情代码
    private String QTZD12; // Y	其他诊断
    private String JBDM12; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ12; // Y	入院病情	代码：入院病情代码
    private String QTZD5; // Y	其他诊断
    private String JBDM5; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ5; // Y	入院病情	代码：入院病情代码
    private String QTZD13; // Y	其他诊断
    private String JBDM13; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ13; // Y	入院病情	代码：入院病情代码
    private String QTZD6; // Y	其他诊断
    private String JBDM6; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ6; // Y	入院病情	代码：入院病情代码
    private String QTZD14; // Y	其他诊断
    private String JBDM14; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ14; // Y	入院病情	代码：入院病情代码
    private String QTZD7; // Y	其他诊断
    private String JBDM7; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ7; // Y	入院病情	代码：入院病情代码
    private String QTZD15; // Y	其他诊断
    private String JBDM15; // Y	疾病编码	代码：疾病分类代码
    private String RYBQ15; // Y	入院病情	代码：入院病情代码
    private String WBYY; // Y	中毒的外部原因
    private String H23; // Y	疾病编码	代码：疾病分类代码
    private String BLZD; // Y	病理诊断出
    private String JBMM; // Y	疾病编码	代码：疾病分类代码
    private String BLH; // Y	病理号
    private String YWGM; // Y	药物过敏
    private String GMYW; // Y	过敏药物疾病
    private String SWHZSJ; // Y	死亡患者尸检
    private String XX; // Y	血型	代码：血型编码
    private String RH; // Y	Rh	代码：Rh
    private String KZR; // Y	科主任
    private String ZRYS; // Y	主任（副主任）医师
    private String ZZYS; // Y	主治医师
    private String ZYYS; // Y	住院医师
    private String ZRHS; // Y	责任护士
    private String JXYS; // Y	进修医师住
    private String SXYS; // Y	实习医师
    private String BMY; // Y	编码员
    private String BAZL; // Y	病案质量	代码：病案质量
    private String ZKYS; // Y	质控医师
    private String ZKHS; // Y	质控护士
    private String ZKRQ; // Y	质控日期	格式为：YYYYMMDD，例如：20131125
    private String SSJCZBM1; // Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ1; // Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB1; // Y	手术级别	代码：手术级别
    private String SSJCZMC1; // Y	手术及操作名称
    private String SZ1; // (100)	Y	术者
    private String YZ1; // (100)	Y	I助
    private String EZ1; // (100)	Y	II助
    private String QKDJ1; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB1; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS1; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS1; // (100)	Y	麻醉医师
    private String SSJCZBM2; // (100)	Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ2; // (12)	Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB2; // (100)	Y	手术级别
    private String SSJCZMC2; // (200)	Y	手术及操作名称
    private String SZ2; // (100)	Y	术者
    private String YZ2; // (100)	Y	I助
    private String EZ2; // (100)	Y	II助
    private String QKDJ2; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB2; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS2; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS2; // (100)	Y	麻醉医师
    private String SSJCZBM3; // (100)	Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ3; // (12)	Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB3; // (100)	Y	手术级别
    private String SSJCZMC3; // (200)	Y	手术及操作名称
    private String SZ3; // (100)	Y	术者
    private String YZ3; // (100)	Y	I助
    private String EZ3; // (100)	Y	II助
    private String QKDJ3; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB3; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS3; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS3; // (100)	Y	麻醉医师
    private String SSJCZBM4; // (100)	Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ4; // (12)	Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB4; // (100)	Y	手术级别
    private String SSJCZMC4; // (200)	Y	手术及操作名称
    private String SZ4; // (100)	Y	术者
    private String YZ4; // (100)	Y	I助
    private String EZ4; // (100)	Y	II助
    private String QKDJ4; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB4; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS4; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS4; // (100)	Y	情况麻醉医师
    private String SSJCZBM5; // (100)	Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ5; // (12)	Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB5; // (100)	Y	手术级别
    private String SSJCZMC5; // (200)	Y	手术及操作名称
    private String SZ5; // (100)	Y	术者
    private String YZ5; // (100)	Y	I助
    private String EZ5; // (100)	Y	II助
    private String QKDJ5; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB5; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS5; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS5; // (100)	Y	麻醉医师
    private String SSJCZBM6; // (100)	Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ6; // (12)	Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB6; // (100)	Y	手术级别
    private String SSJCZMC6; // (200)	Y	手术及操作名称
    private String SZ6; // (100)	Y	术者
    private String YZ6; // (100)	Y	I助
    private String EZ6; // (100)	Y	II助
    private String QKDJ6; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB6; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS6; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS6; // (100)	Y	麻醉医师
    private String SSJCZBM7; // (100)	Y	手术及操作编码	代码：手术分类代码
    private String SSJCZRQ7; // (12)	Y	手术及操作日期	格式为：YYYYMMDD，例如：20131125
    private String SSJB7; // (100)	Y	手术级别
    private String SSJCZMC7; // (200)	Y	手术及操作名称
    private String SZ7; // (100)	Y	术者
    private String YZ7; // (100)	Y	I助
    private String EZ7; // (100)	Y	II助
    private String QKDJ7; // (100)	Y	切口等级	代码：切口愈合等级
    private String QKYHLB7; // (100)	Y	切口愈合类别	代码：切口愈合类别
    private String MZFS7; // (100)	Y	麻醉方式	代码：麻醉方式代码
    private String MZYS7; // (100)	Y	麻醉医师
    private String LYFS; // (100)	Y	离院方式	代码：离院方式
    private String YZZY_YLJG; // (200)	Y	医嘱转院，拟接收医疗机构名称
    private String WSY_YLJG; // (200)	Y	医嘱转社区卫生服务机构/乡镇卫生院，拟接收医疗机构名称
    private String SFZZYJH; // (100)	Y	是否有出院31天内再住院计划手术情况	代码：有无
    private String MD; // (100)	Y	目的
    private Integer RYQ_T; // (12)	Y	颅脑损伤患者昏迷入院前时间：天
    private Integer RYQ_XS; // (24)	Y	颅脑损伤患者昏迷入院前时间：小时
    private Integer RYQ_F; // (12)	Y	颅脑损伤患者昏迷入院前时间：分
    private Integer RYH_T; // (12)	Y	颅脑损伤患者昏迷入院后时间：天
    private Integer RYH_XS; // (24)	Y	颅脑损伤患者昏迷入院后时间：小时
    private Integer RYH_F; // (12)	Y	颅脑损伤患者昏迷入院后时间：分
    private Double ZFY; // (12,2)	N	住院费用(元)：总费用
    private Double ZFJE; // (12,2)	Y	自付金额
    private Double YLFUF; // (12,2)	Y	综合医疗服务类：(1)一般医疗服务费
    private Double ZLCZF; // (12,2)	Y	一般治疗操作费
    private Double HLF; // (12,2)	Y	护理费住院费
    private Double QTFY; // (12,2)	Y	其他费用
    private Double BLZDF; // (12,2)	Y	诊断类：(5)病理诊断费
    private Double SYSZDF; // (12,2)	Y	实验室诊断费
    private Double YXXZDF; // (12,2)	Y	影像学诊断费
    private Double LCZDXMF; // (12,2)	Y	临床诊断项目费
    private Double FSSZLXMF; // (12,2)	Y	治疗类：(9)非手术治疗项目费
    private Double WLZLF; // (12,2)	Y	临床物理治疗费
    private Double SSZLF; // (12,2)	Y	(10)手术治疗费
    private Double MAF; // (12,2)	Y	麻醉费
    private Double SSF; // (12,2)	Y	手术费
    private Double KFF; // (12,2)	Y	康复类：(11)康复费
    private Double ZYZLF; // (12,2)	Y	中医类:(12)中医治疗费
    private Double XYF; // (12,2)	Y	西药类:(13)西药费
    private Double KJYWF; // (12,2)	Y	抗菌药物费
    private Double ZCYF; // (12,2)	Y	中药类:(14)中成药费
    private Double ZCYF1; // (12,2)	Y	中草药费
    private Double XF; // (12,2)	Y	血液和血液制品类:(16)血费
    private Double BDBLZPF; // (12,2)	Y	白蛋白类制品费
    private Double QDBLZPF; // (12,2)	Y	球蛋白类制品费
    private Double NXYZLZPF; // (12,2)	Y	凝血因子类制品费
    private Double XBYZLZPF; // (12,2)	Y	细胞因子类制品费
    private Double HCYYCLF; // (12,2)	Y	耗材类:(21)检查用一次性医用材料费
    private Double YYCLF; // (12,2)	Y	(22)治疗用一次性医用材料费
    private Double YCXYYCLF; // (12,2)	Y	(23)手术用一次性医用材料费
    private Double QTF; // (12,2)	Y	其他类：(24)其他费
}
