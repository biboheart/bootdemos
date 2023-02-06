package com.biboheart.demos.domain.packager;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.domain.packager.model.HQMSModel;
import com.biboheart.demos.support.PaymentUtils;

public class HqmsDataPackager {
    public HQMSModel hqmsData(HealthDataModel healthDataModel) {
        HQMSModel data = new HQMSModel();
        data.setA01("H36118100203"); // 1.		组织机构代码	A01	字符	30	必填① 指医疗机构执业许可证上面的机构代码；不能为“-”。
        data.setA02("德兴田氏医院"); // 2.		医疗机构名称	A02	字符	80	必填① 指患者住院诊疗所在的医疗机构名称，按照《医疗机构执业许可证》登记的机构名称填写；不能为“-”。
        data.setA48(healthDataModel.getBAH()); // 3.		病案号	A48	字符	50	必填① 不能为“-”。
        data.setA49("1"); // 4.		住院次数	A49	数字	4	必填① 大于0的整数；不能为“-”。
        data.setB12(TimeUtils.formatDateWithMilis("yyyy-MM-dd  HH:mm:ss", TimeUtils.getDateMillis(healthDataModel.getRYSJ(), "yyyyMMdd"))); // 5.		入院时间	B12	日期时间		必填① 格式 yyyy-MM-dd HH:mm:ss；入院时间不能晚于出院时间；不能为“-”。
        data.setB15(TimeUtils.formatDateWithMilis("yyyy-MM-dd  HH:mm:ss", TimeUtils.getDateMillis(healthDataModel.getCYSJ(), "yyyyMMdd"))); // 6.		出院时间	B15	日期时间		必填① 格式 yyyy-MM-dd HH:mm:ss；不能为“-”。
        data.setA47(healthDataModel.getJKKH()); // 7.		健康卡号	A47	字符	50		在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”。
        data.setA46C(healthDataModel.getYLFKFS()); // 8.		医疗付费方式	A46C	字符	3	必填① 值域范围参考RC032
        data.setA11(healthDataModel.getXM()); // 9.		姓名	A11	字符	40	必填①
        data.setA12C(healthDataModel.getXB()); // 10.		性别	A12C	数字	1	必填① 值域范围参考RC001；不能为“-”。
        data.setA13(TimeUtils.formatDateWithMilis("yyyy-MM-dd  HH:mm:ss", TimeUtils.getDateMillis(healthDataModel.getCSRQ(), "yyyyMMdd"))); // 11.		出生日期	A13	日期	10	必填① 格式 yyyy-MM-dd
        data.setA14(healthDataModel.getNL()); // 12.		年龄（岁）	A14	数字	3	必填① 患者入院年龄，指患者入院时按照日历计算的历法年龄，应以实足年龄的相应整数填写。大于或等于0的整数
        data.setA15C(healthDataModel.getGJ()); // 13.		国籍	A15C	字符	40	必填①
        data.setA21C(healthDataModel.getHY()); // 14.		婚姻	A21C	字符	1	必填① 值域范围参考RC002
        data.setA38C(healthDataModel.getZY()); // 15.		职业	A38C	字符	2	必填① 值域范围参考RC003
        data.setA19C(healthDataModel.getMZ()); // 16.		民族	A19C	字符	2	必填① 值域范围参考RC035
        data.setA20N("1"); // 17.		证件类别	A20N	字符	1	必填① 值域范围参考RC038；住院病案首页数据质量管理与控制指标必填项。
        data.setA20(ifNUll(healthDataModel.getSFZH())); // 18.		证件号码	A20	字符	18	必填① 住院患者入院时填写的唯一身份识别号码，当“证件类别”为“居民身份证”时，证件号码限定为15位或18位
        data.setA22(healthDataModel.getCSD()); // 19.		出生地址	A22	字符	200	必填①
        data.setA23C(healthDataModel.getGG()); // 20.		籍贯省（自治区、直辖市）	A23C	字符	50	必填① 值域范围参考RC036
        data.setA24(healthDataModel.getHKDZ()); // 21.		户口地址	A24	字符	200	必填①
        data.setA25C(healthDataModel.getYB2()); // 22.		户口地址邮政编码	A25C	字符	6	必填① 6位数字。
        data.setA26(healthDataModel.getXZZ()); // 23.		现住址	A26	字符	200	必填①
        data.setA27(healthDataModel.getDH()); // 24.		现住址电话	A27	字符	40	必填①
        data.setA28C(healthDataModel.getYB1()); // 25.		现住址邮政编码	A28C	字符	6	必填① 6位数字。
        String business = healthDataModel.getGZDWJDZ();
        if (!CheckUtils.isEmpty(business)){
            data.setA29(business); // 26.		工作单位及地址	A29	字符	200	必填①
            data.setA30(healthDataModel.getDWDH()); // 27.		工作单位电话	A30	字符	20	必填①
            data.setA31C(healthDataModel.getYB3()); // 28.		工作单位邮政编码	A31C	字符	6	必填① 6位数字。
        }
        data.setA32(healthDataModel.getLXRXM()); // 29.		联系人姓名	A32	字符	40	必填①
        data.setA33C(healthDataModel.getGX()); // 30.		联系人关系	A33C	字符	1	必填① 值域范围参考RC033
        data.setA34(healthDataModel.getDZ()); // 31.		联系人地址	A34	字符	200	必填①
        data.setA35(healthDataModel.getDH2()); // 32.		联系人电话	A35	字符	40	必填①
        data.setB38("0"); // 33.		是否为日间手术	B38	字符	1	必填	日间手术：为本省和国家确定的日间手术目录中的手术，值域范围参考RC039。
        data.setB11C(healthDataModel.getRYTJ()); // 34.		入院途径	B11C	字符	1	必填① 值域范围参考RC026；不能为“-”。
        data.setB13C(healthDataModel.getRYKB()); // 35.		入院科别	B13C	字符	6	必填① 值域范围参考RC023
        if (!CheckUtils.isEmpty(data.getB13C())) {
            data.setB13C(data.getB13C().replace("A","")); // Y 入院科别	代码：科室代码
            data.setB13C(data.getB13C().replace("B","")); // Y 入院科别	代码：科室代码
            data.setB13C(data.getB13C().replace("C","")); // Y 入院科别	代码：科室代码
            data.setB13C(data.getB13C().replace("D","")); // Y 入院科别	代码：科室代码
            data.setB13C(data.getB13C().replace("E","")); // Y 入院科别	代码：科室代码
        }
        data.setB14(healthDataModel.getRYBF()); // 36.		入院病房	B14	字符	30	必填①
        data.setB21C(healthDataModel.getZKKB()); // 37.		转科科别	B21C	集合	可以多选	必填	值域范围参考RC023；转经多个科室时，值以英文逗号进行分隔。
        if (!CheckUtils.isEmpty(data.getB21C())) {
            data.setB21C(data.getB21C().replace("A","")); // Y 转科科别	代码：科室代码
            data.setB21C(data.getB21C().replace("B","")); // Y 转科科别	代码：科室代码
            data.setB21C(data.getB21C().replace("C","")); // Y 转科科别	代码：科室代码
            data.setB21C(data.getB21C().replace("D","")); // Y 转科科别	代码：科室代码
            data.setB21C(data.getB21C().replace("E","")); // Y 转科科别	代码：科室代码
        }
        data.setB16C(healthDataModel.getCYKB()); // 38.		出院科别	B16C	字符	6	必填① 值域范围参考RC023
        if (!CheckUtils.isEmpty(data.getB16C())) {
            data.setB16C(data.getB16C().replace("A","")); // Y 转科科别	代码：科室代码
            data.setB16C(data.getB16C().replace("B","")); // Y 转科科别	代码：科室代码
            data.setB16C(data.getB16C().replace("C","")); // Y 转科科别	代码：科室代码
            data.setB16C(data.getB16C().replace("D","")); // Y 转科科别	代码：科室代码
            data.setB16C(data.getB16C().replace("E","")); // Y 转科科别	代码：科室代码
        }
        data.setB17(healthDataModel.getCYBF()); // 39.		出院病房	B17	字符	30	必填①
        data.setB20(healthDataModel.getSJZYTS()); // 40.		实际住院（天）	B20	数字	6	必填① 大于0整数。入院时间与出院时间只计算一天，例如：2018年6月12日入院，2018年6月15日出院，计住院天数为3天；不能为“-”。
        data.setC01C(healthDataModel.getJBBM()); // 41.		门（急）诊诊断编码	C01C	字符	20	必填① 采用疾病分类代码国家临床版2.0疾病诊断编码（ICD-10）
        data.setC02N(healthDataModel.getMZZD()); // 42.		门（急）诊诊断名称	C02N	字符	100	必填① 采用疾病分类代码国家临床版2.0疾病诊断编码（ICD-10）与编码对应的诊断名称
        data.setF01(""); // 43.		入院时情况	F01	字符	1		值域范围参考RC004
        data.setF02C(healthDataModel.getJBDM()); // 44.		入院诊断编码	F02C	字符	20		采用疾病分类代码国家临床版2.0编码（ICD-10）
        data.setF03N(healthDataModel.getRYBQ()); // 45.		入院诊断名称	F03N	字符	100		采用疾病分类代码国家临床版2.0(ICD-10)与编码对应的诊断名称
        // hqmsModel.setF04(); // 46.		入院后确诊日期	F04	日期			yyyy-MM-dd
        data.setC09C(healthDataModel.getJBMM()); // 211.		病理诊断编码	C09C	字符	20	条件必填① 采用疾病分类代码国家临床版2.0肿瘤形态学编码（M码）；主要诊断ICD编码首字母为C或D00-D48时必填。
        data.setC10N(healthDataModel.getBLZD()); // 212.		病理诊断名称	C10N	字符	100	条件必填① 采用疾病分类代码国家临床版2.0肿瘤形态学编码（M码）与编码对应的病理名称；主要诊断ICD编码首字母为C或D00-D48时必填。
        data.setC11(healthDataModel.getBLH()); // 213.		病理号	C11	字符	50	条件必填① 有病理诊断编码时必填。
//        private String F07x01C; // 214.		病理诊断编码1	F07x01C	字符	20		采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)
//        private String F08x01N; // 215.		病理诊断名称1	F08x01N	字符	100		采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)与编码对应的病理名称
//        private String F09x01; // 216.		病理号1	F09x01	字符	50
//        private String F07x02C; // 217.		病理诊断编码2	F07x02C	字符	20		采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)
//        private String F08x02N; // 218.		病理诊断名称2	F08x02N	字符	100		采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)与编码对应的病理名称
//        private String F09x02; // 219.		病理号2	F09x02	字符	50
        data.setC12C(healthDataModel.getH23()); // 220.		损伤、中毒外部原因编码	C12C	字符	20	条件必填① 采用疾病分类代码国家临床版2.0疾病诊断编码（ICD-10），主要诊断ICD编码首字母为S或T时必填
        data.setC13N(healthDataModel.getWBYY()); // 221.		损伤、中毒外部原因名称	C13N	字符	100	条件必填① 采用疾病分类代码国家临床版2.0疾病诊断编码（ICD-10）对应的外部原因名称；主要诊断ICD编码首字母为S或T时必填。
        data.setC24C("1");
        String allergyDrug = healthDataModel.getYWGM();
        if (!CheckUtils.isEmpty(allergyDrug)) {
            data.setC24C("2"); // 222.		有无药物过敏	C24C	字符	1	必填① 值域范围参考RC037
            data.setC25(allergyDrug); // 223.		过敏药物名称	C25	字符	200	条件必填① “有无药物过敏”为“有”时必填；多种药物用英文逗号进行分隔
        }
//        private String F10; // 224.		HBsAg	F10	字符	1		值域范围参考RC007
//        private String F11; // 225.		HCV-Ab	F11	字符	1		值域范围参考RC007
//        private String F12; // 226.		HIV-Ab	F12	字符	1		值域范围参考RC007
//        data.setB22C(home.getDeptDrtSn()); // 227.		科主任编码	B22C	字符	30	必填	填写医师执业证书编码
        data.setB22(healthDataModel.getKZR()); // 228.		科主任	B22	字符	40	必填①填写与医师执业证书编码对应的医师姓名
//        data.setB23C(home.getChfDrSn()); // 229.		主（副主）任医师编码	B23C	字符	30	必填	填写医师执业证书编码
        data.setB23(healthDataModel.getZRYS()); // 230.		主（副主）任医师	B23	字符	40	必填①填写与医师执业证书编码对应的医师姓名
//        data.setB24C(home.getAtdDrSn()); // 231.		主治医师编码	B24C	字符	30	必填	填写医师执业证书编码
        data.setB24(healthDataModel.getZZYS()); // 232.		主治医师	B24	字符	40	必填①填写与医师执业证书编码对应的医师姓名
//        data.setB25C(home.getIptDrSn()); // 233.		住院医师编码	B25C	字符	30	必填	填写医师执业证书编码
        data.setB25(healthDataModel.getZYYS()); // 234.		住院医师	B25	字符	40	必填①填写与医师执业证书编码对应的医师姓名
//        data.setB26C(home.getRespNursSn()); // 235.		责任护士编码	B26C	字符	30	必填	填写护士执业证书编码
        data.setB26(healthDataModel.getZRHS()); // 236.		责任护士	B26	字符	40	必填①填写与护士执业证书编码对应的护士姓名
        data.setB27(healthDataModel.getJXYS()); // 237.		进修医师	B27	字符	40
        data.setB28(healthDataModel.getSXYS()); // 238.		实习医师	B28	字符	40
        data.setB29(healthDataModel.getBMY()); // 239.		编码员	B29	字符	40	必填①
        data.setB30C(healthDataModel.getBAZL()); // 240.		病案质量	B30C	字符	1		值域范围参考RC011
        data.setB31(healthDataModel.getZKYS()); // 241.		质控医师	B31	字符	40
        data.setB32(healthDataModel.getZKHS()); // 242.		质控护师	B32	字符	40
        data.setB33(TimeUtils.formatDateWithMilis("yyyy-MM-dd", TimeUtils.getDateMillis(healthDataModel.getZKRQ(), "yyyyMMdd"))); // 243.		质控日期	B33	日期			格式 yyyy-MM-dd
        data.setC34C(""); // 244.		死亡患者尸检	C34C	字符	1		值域范围参考RC016
        data.setC26C(healthDataModel.getXX()); // 245.		ABO血型	C26C	字符	1	必填①值域范围参考RC030
        data.setC27C(healthDataModel.getRH()); // 246.		Rh血型	C27C	字符	1	必填①值域范围参考RC031
//        private String F17; // 739.		特级护理天数	F17	数字	6		单位（天）
//        private String F18; // 740.		一级护理天数	F18	数字	6		单位（天）
//        private String F19; // 741.		二级护理天数	F19	数字	6		单位（天）
//        private String F20; // 742.		三级护理天数	F20	数字	6		单位（天）
        data.setF21("-"); // 743.		输血反应	F21	数字	1	条件必填	输血患者填写，指与输血具有时序相关性的不良反应，值域范围参考RC018；如血费>0,则必填，没有可以填0或英文横杠”-”。
        data.setF22("-"); // 744.		红细胞	F22	数字	(4,1)	条件必填	输血患者填写，单位（单位）；如血费>0,则必填，没有可以填0或英文横杠”-”。
        data.setF23("-"); // 745.		血小板	F23	数字	(4,1)	条件必填	输血患者填写（仅填写单采血小板），单位（治疗量）；如血费>0,则必填，没有可以填0或英文横杠”-”。
        data.setF24("-"); // 746.		血浆	F24	数字	(8,2)	条件必填	输血患者填写，单位（毫升）；如血费>0,则必填，没有可以填0或英文横杠”-”。
        data.setF25("-"); // 747.		全血	F25	数字	(8,2)	条件必填	输血患者填写，单位（毫升）；如血费>0,则必填，没有可以填0或英文横杠”-”。
        data.setF26("-"); // 748.		自体血回输	F26	数字	(4,1)	条件必填	自体血回输患者填写，单位（单位）；如血费>0,则必填，没有可以填0或英文横杠”-”。
//        private String A16; // 749.		年龄不足1周岁的年龄（天）	A16	数字	3	条件必填②年龄不足1周岁（A14（年龄）=0）时，填写实足年龄的天数，即入院时间减出生日期后取整数，不足一天按0天计算；取值范围：大于或等于0小于365。
//        private String A18x01; // 750.		新生儿出生体重（克）	A18x01	数字	6	条件必填②测量新生儿体重要求精确到10克；应在活产后一小时内称取重量；产妇和新生儿病案填写；新生儿体重范围：100克-9999克。
//        private String A18x02; // 751.		新生儿出生体重（克）2	A18x02	数字	6		新生儿体重范围：100克-9999克
//        private String A18x03; // 752.		新生儿出生体重（克）3	A18x03	数字	6		新生儿体重范围：100克-9999克
//        private String A18x04; // 753.		新生儿出生体重（克）4	A18x04	数字	6		新生儿体重范围：100克-9999克
//        private String A18x05; // 754.		新生儿出生体重（克）5	A18x05	数字	6		新生儿体重范围：100克-9999克
//        private String A17; // 755.		新生儿入院体重（克）	A17	数字	6	条件必填②指新生儿入院当日体重；100克-9999克，精确到10克；“新生儿入院体重”与“年龄不足1周岁的年龄（天）”互为逻辑校验项，小于等于28天的新生儿必填。
        /*if (!CheckUtils.isEmpty(home.getFrontHospitalTime())) {
            data.setC28(new Long(home.getFrontHospitalTime() / TimeStampUtils.per_day_ms).intValue()); // 756.		颅脑损伤患者入院前昏迷时间（天）	C28	数字	5	必填①大于等于0整数。
            data.setC29(new Long(home.getFrontHospitalTime() / TimeStampUtils.per_hour_ms).intValue()); // 757.		颅脑损伤患者入院前昏迷时间（小时）	C29	数字	2	必填①大于等于0，小于24整数。
            data.setC30(new Long(home.getFrontHospitalTime() / TimeStampUtils.per_minute_ms).intValue()); // 758.    颅脑损伤患者入院前昏迷时间（分钟）	C30	数字	2	必填①大于等于0，小于60整数。
        }
        if (!CheckUtils.isEmpty(home.getAfterHospitalTime())) {
            data.setC31(new Long(home.getAfterHospitalTime() / TimeStampUtils.per_day_ms).intValue()); // 759.		颅脑损伤患者入院后昏迷时间（天）	C31	数字	5	必填①大于等于0整数。
            data.setC32(new Long(home.getAfterHospitalTime() / TimeStampUtils.per_hour_ms).intValue()); // 760.		颅脑损伤患者入院后昏迷时间（小时）	C32	数字	2	必填①大于等于0，小于24整数。
            data.setC33(new Long(home.getAfterHospitalTime() / TimeStampUtils.per_minute_ms).intValue()); // 761.		颅脑损伤患者入院后昏迷时间（分钟）	C33	数字	2	必填①大于等于0，小于60整数。
        }*/
//        hqmsModel.setC47(); // 762.		有创呼吸机使用时间	C47	数字	6		大于等于0的整数，单位（小时），指患者住院期间有创呼吸机累计使用时间，全麻期间使用有创呼吸机的时间除外，不足1小时按1小时计算
//        private String C48x01C; // 763.		重症监护室名称1	C48x01C	字符	2		值域范围参考RC015
//        private String C49x01; // 764.		进入时间1	C49x01	日期时间			指进入重症监护室的时间，格式yyyy-MM-dd HH:mm:ss，进入时间不能晚于退出时间
//        private String C50x01; // 765.		退出时间1	C50x01	日期时间			指退出重症监护室的时间，格式yyyy-MM-dd HH:mm:ss
//        private String C48x02C; // 766.		重症监护室名称2	C48x02C	字符	2		值域范围参考RC015
//        private String C49x02; // 767.		进入时间2	C49x02	日期时间			指进入重症监护室的时间，格式yyyy-MM-dd HH:mm:ss，进入时间不能晚于退出时间
//        private String C50x02; // 768.		退出时间2	C50x02	日期时间			指退出重症监护室的时间，格式yyyy-MM-dd HH:mm:ss
//        private String C48x03C; // 769.		重症监护室名称3	C48x03C	字符	2		值域范围参考RC015
//        private String C49x03; // 770.		进入时间3	C49x03	日期时间			指进入重症监护室的时间，格式yyyy-MM-dd HH:mm:ss，进入时间不能晚于退出时间
//        private String C50x03; // 771.		退出时间3	C50x03	日期时间			指退出重症监护室的时间，格式yyyy-MM-dd HH:mm:ss
//        private String C48x04C; // 772.		重症监护室名称4	C48x04C	字符	2		值域范围参考RC015
//        private String C49x04; // 773.		进入时间4	C49x04	日期时间			指进入重症监护室的时间，格式yyyy-MM-dd HH:mm:ss，进入时间不能晚于退出时间
//        private String C50x04; // 774.		退出时间4	C50x04	日期时间			指退出重症监护室的时间，格式yyyy-MM-dd HH:mm:ss
//        private String C48x05C; // 775.		重症监护室名称5	C48x05C	字符	2		值域范围参考RC015
//        private String C49x05; // 776.		进入时间5	C49x05	日期时间			指进入重症监护室的时间，格式yyyy-MM-dd HH:mm:ss，进入时间不能晚于退出时间
//        private String C50x05; // 777.		退出时间5	C50x05	日期时间			指退出重症监护室的时间，格式yyyy-MM-dd HH:mm:ss
        data.setB36C("1"); // 778.		是否有出院31日内再住院计划	B36C	数字	1	必填①值域范围参考RC028；指患者本次住院出院后31天内是否有诊疗需要的再住院安排。如果有再住院计划，则需要填写目的，如：进行二次手术。
//        private String B37; // 779.		出院31天再住院计划目的	B37	字符	100	条件必填①是否有出院31日内再住院计划填“有”时必填。
        data.setB34C("9");
        /*if (!CheckUtils.isEmpty(home.getReceivingOrganization())) {
            data.setB34C(home.getDischargeState()); // 780.		离院方式	B34C	字符	1	必填①值域范围参考RC019；指患者本次住院出院的方式，填写相应的阿拉伯数字；不能为“-”。
            data.setB35(home.getReceivingOrganization()); // 781.		医嘱转院、转社区卫生服务机构/乡镇卫生院名称	B35	字符	100	条件必填①离院方式为医嘱转院或医嘱转社区患者必填
        }*/
        data.setB34C("1"); // 780.		离院方式	B34C	字符	1	必填①值域范围参考RC019；指患者本次住院出院的方式，填写相应的阿拉伯数字；不能为“-”。
        data.setD01(PaymentUtils.strToDouble(healthDataModel.getZFY())); // 782.		住院总费用	D01	数字	(11,2)	必填①住院总费用必填且大于0；总费用大于或等于分项费用之和；不能为“-”。
        data.setD09(PaymentUtils.strToDouble(healthDataModel.getZFJE())); // 783.		住院总费用其中自付金额	D09	数字	(10,2)	必填①小于等于总费用
        data.setD11(PaymentUtils.strToDouble(healthDataModel.getYLFUF())); // 784.		1.一般医疗服务费	D11	数字	(10,2)
        data.setD12(PaymentUtils.strToDouble(healthDataModel.getZLCZF())); // 785.		2.一般治疗操作费	D12	数字	(10,2)
        data.setD13(PaymentUtils.strToDouble(healthDataModel.getHLF())); // 786.		3.护理费	D13	数字	(10,2)
        data.setD14(PaymentUtils.strToDouble(healthDataModel.getQTFY())); // 787.		4.综合医疗服务类其他费用	D14	数字	(10,2)
        data.setD15(PaymentUtils.strToDouble(healthDataModel.getBLZDF())); // 788.		5.病理诊断费	D15	数字	(10,2)
        data.setD16(PaymentUtils.strToDouble(healthDataModel.getSYSZDF())); // 789.		6.实验室诊断费	D16	数字	(10,2)
        data.setD17(PaymentUtils.strToDouble(healthDataModel.getYXXZDF())); // 790.		7.影像学诊断费	D17	数字	(10,2)
        data.setD18(PaymentUtils.strToDouble(healthDataModel.getLCZDXMF())); // 791.		8.临床诊断项目费	D18	数字	(10,2)
        data.setD19(PaymentUtils.strToDouble(healthDataModel.getFSSZLXMF())); // 792.		9.非手术治疗项目费	D19	数字	(10,2)
        data.setD19x01(PaymentUtils.strToDouble(healthDataModel.getWLZLF())); // 793.		其中：临床物理治疗费	D19x01	数字	(10,2)
        data.setD20(PaymentUtils.strToDouble(healthDataModel.getSSZLF())); // 794.		10.手术治疗费	D20	数字	(10,2)
        data.setD20x01(PaymentUtils.strToDouble(healthDataModel.getMAF())); // 795.		其中：麻醉费	D20x01	数字	(10,2)
        data.setD20x02(PaymentUtils.strToDouble(healthDataModel.getSSF())); // 796.		其中：手术费	D20x02	数字	(10,2)
        data.setD21(PaymentUtils.strToDouble(healthDataModel.getKFF())); // 797.		11.康复费	D21	数字	(10,2)
        data.setD22(PaymentUtils.strToDouble(healthDataModel.getZYZLF())); // 798.		12.中医治疗费	D22	数字	(10,2)
        data.setD23(PaymentUtils.strToDouble(healthDataModel.getXYF())); // 799.		13.西药费	D23	数字	(10,2)
        data.setD23x01(PaymentUtils.strToDouble(healthDataModel.getKJYWF())); // 800.		其中：抗菌药物费	D23x01	数字	(10,2)
        data.setD24(PaymentUtils.strToDouble(healthDataModel.getZCYF())); // 801.		14.中成药费	D24	数字	(10,2)
        data.setD25(PaymentUtils.strToDouble(healthDataModel.getZCYF1())); // 802.		15.中草药费	D25	数字	(10,2)
        data.setD26(PaymentUtils.strToDouble(healthDataModel.getXF())); // 803.		16.血费	D26	数字	(10,2)
        data.setD27(PaymentUtils.strToDouble(healthDataModel.getBDBLZPF())); // 804.		17.白蛋白类制品费	D27	数字	(10,2)
        data.setD28(PaymentUtils.strToDouble(healthDataModel.getQDBLZPF())); // 805.		18.球蛋白类制品费	D28	数字	(10,2)
        data.setD29(PaymentUtils.strToDouble(healthDataModel.getNXYZLZPF())); // 806.		19.凝血因子类制品费	D29	数字	(10,2)
        data.setD30(PaymentUtils.strToDouble(healthDataModel.getXBYZLZPF())); // 807.		20.细胞因子类制品费	D30	数字	(10,2)
        data.setD31(PaymentUtils.strToDouble(healthDataModel.getHCYYCLF())); // 808.		21.检查用一次性医用材料费	D31	数字	(10,2)
        data.setD32(PaymentUtils.strToDouble(healthDataModel.getYYCLF())); // 809.		22.治疗用一次性医用材料费	D32	数字	(10,2)
        data.setD33(PaymentUtils.strToDouble(healthDataModel.getYCXYYCLF())); // 810.		23.手术用一次性医用材料费	D33	数字	(10,2)
        data.setD34(PaymentUtils.strToDouble(healthDataModel.getQTF())); // 811.		24.其他费：	D34	数字	(10,2)
        return data;
    }

    private String ifNUll(String data) {
        return ifNUll(data, "-");
    }

    private String ifNUll(String data, String rex) {
        return null == data ? rex : data;
    }
}
