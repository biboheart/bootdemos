package com.biboheart.ddatabase.service;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.ListUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.ddatabase.entity.Dictionary;
import com.biboheart.ddatabase.entity.*;
import com.biboheart.ddatabase.repository.*;
import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

@Component
public class ExportService {
    private final HomeRepository homeRepository;
    private final AddressInfoRepository addressInfoRepository;
    private final DiagnosisInfoRepository diagnosisInfoRepository;
    private final DepartmentRepository departmentRepository;
    private final OperativeInfoRepository operativeInfoRepository;
    private final DictionaryRepository dictionaryRepository;
    private final PersonRepository personRepository;
    private final QualityInfoRepository qualityInfoRepository;
    private final ChargeRepository chargeRepository;

    public ExportService(HomeRepository homeRepository, AddressInfoRepository addressInfoRepository, DiagnosisInfoRepository diagnosisInfoRepository, DepartmentRepository departmentRepository, OperativeInfoRepository operativeInfoRepository, DictionaryRepository dictionaryRepository, PersonRepository personRepository, QualityInfoRepository qualityInfoRepository, ChargeRepository chargeRepository) {
        this.homeRepository = homeRepository;
        this.addressInfoRepository = addressInfoRepository;
        this.diagnosisInfoRepository = diagnosisInfoRepository;
        this.departmentRepository = departmentRepository;
        this.operativeInfoRepository = operativeInfoRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.personRepository = personRepository;
        this.qualityInfoRepository = qualityInfoRepository;
        this.chargeRepository = chargeRepository;
    }

    public void exportDBF(String jzids, HttpServletResponse response) throws Exception {
        // 定义DBF文件字段
        Field[] fields = N041Vo.class.getDeclaredFields();
        DBFField[] dbfFields = new DBFField[fields.length];
        int a = 0;
        for (Field field : fields) {
            dbfFields[a] = new DBFField();
            switch (field.getType().getName()) {
                case "java.lang.String":
                    dbfFields[a].setName(field.getName());
                    dbfFields[a].setType(DBFDataType.CHARACTER);
                    dbfFields[a].setLength(100);
                    break;
                case "java.lang.Integer":
                    dbfFields[a].setName(field.getName());
                    dbfFields[a].setType(DBFDataType.NUMERIC);
                    dbfFields[a].setLength(12);
                    break;
                case "java.lang.Double":
                    dbfFields[a].setName(field.getName());
                    dbfFields[a].setType(DBFDataType.FLOATING_POINT);
                    dbfFields[a].setLength(12);
                    dbfFields[a].setDecimalCount(2);
                    break;
            }
            a++;
        }

        String fileName = "N041_金华田氏骨科医院.DBF";
        response.setContentType("text/DBF");
        response.setCharacterEncoding("GBK");
        response.setHeader("Content-Disposition", "attachment; filename=\""+  URLEncoder.encode(fileName, "UTF-8")  +"\";charset=UTF-8");
        DBFWriter writer = new DBFWriter(response.getOutputStream(), Charset.forName("GBK"));
        writer.setFields(dbfFields); // 设置头

        List<N041Vo> n041Vos = setData(jzids);
        for (N041Vo n041Vo : n041Vos) {
            Object[] rowData = new Object[dbfFields.length];
            int b = 0;
            for (DBFField dbfField : dbfFields) {
                Class<?> clazz = n041Vo.getClass();
                Method method = clazz.getMethod("get" + dbfField.getName().toUpperCase());
                rowData[b] = method.invoke(n041Vo);
                b++;
            }
            writer.addRecord(rowData);
        }
        writer.close();
    }

    private List<N041Vo> setData(String jzids) throws Exception {
        //        OrgInfo orgInfo = hisDock.loadOrgInfo();
        // 病人首页信息
//        List<HomeInfo> homeInfoList = hisDock.listHomeInfo(jzids, null, null, null);
        List<String> hospitalizedSn = ListUtils.string2ListString(jzids, ",");
        List<Home> homeInfoList = homeRepository.findByHospitalizedSnIn(hospitalizedSn);
        if (CheckUtils.isEmpty(homeInfoList)) {
            throw new BhException("未查询到首页数据");
        }
        Set<String> numberSet = new HashSet<>();
        for (Home home : homeInfoList) {
            numberSet.add(home.getNumber());
        }
        Map<String, AddressInfo> addressInfoHash = new HashMap<>();
        List<AddressInfo> addressInfoList = addressInfoRepository.findByNumberIn(numberSet);
        for (AddressInfo addressInfo : addressInfoList) {
            addressInfoHash.put(addressInfo.getNumber() + "" + addressInfo.getType(), addressInfo);
        }
        List<DiagnosisInfo> diagnosisInfoList = diagnosisInfoRepository.findByNumberIn(numberSet);
        Map<String, DiagnosisInfo> diagnosisInfoHash = new HashMap<>();
        Map<String, List<DiagnosisInfo>> diagnoseInfoListHash = new HashMap<>();
        for (DiagnosisInfo diagnosisInfo : diagnosisInfoList) {
            diagnosisInfoHash.put(diagnosisInfo.getNumber() + "" + diagnosisInfo.getType(), diagnosisInfo);
            List<DiagnosisInfo> numberDiagnosisList = diagnoseInfoListHash.computeIfAbsent(diagnosisInfo.getNumber(), k -> new ArrayList<>());
            numberDiagnosisList.add(diagnosisInfo);
            diagnoseInfoListHash.put(diagnosisInfo.getNumber(), numberDiagnosisList);
        }
        List<Department> departmentList = departmentRepository.findByNumberIn(numberSet);
        Map<String, Department> departmentHash = new HashMap<>();
        for (Department department : departmentList) {
            departmentHash.put(department.getNumber() + "" + department.getType(), department);
        }
        // 手术列表
        List<OperativeInfo> operativeInfoList = operativeInfoRepository.findByNumberIn(numberSet);
        Map<String, List<OperativeInfo>> operativeInfoHash = new HashMap<>();
        for (OperativeInfo operativeInfo : operativeInfoList) {
            List<OperativeInfo> list = operativeInfoHash.computeIfAbsent(operativeInfo.getNumber(), k -> new ArrayList<>());
            list.add(operativeInfo);
            operativeInfoHash.put(operativeInfo.getNumber(), list);
        }
        List<Dictionary> dictionaryList = dictionaryRepository.findByCatalogSn("70010002");
        Map<String, String> dictionaryHash = new HashMap<>();
        for (Dictionary dictionary : dictionaryList) {
            dictionaryHash.put(dictionary.getName(), dictionary.getValue());
        }
        List<Person> personList = personRepository.findByNumberIn(numberSet);
        Map<String, Person> personHash = new HashMap<>();
        for (Person person : personList) {
            personHash.put(person.getNumber() + "" + person.getType(), person);
        }
        List<QualityInfo> qualityInfoList = qualityInfoRepository.findByNumberIn(numberSet);
        Map<String, QualityInfo> qualityInfoHash = new HashMap<>();
        for (QualityInfo qualityInfo : qualityInfoList) {
            qualityInfoHash.put(qualityInfo.getNumber(), qualityInfo);
        }
        List<Charge> chargeList = chargeRepository.findByNumberIn(numberSet);
        Map<String, Charge> chargeHash = new HashMap<>();
        for (Charge charge : chargeList) {
            chargeHash.put(charge.getNumber(), charge);
        }
        List<N041Vo> n041Vos = new ArrayList<>();
        for (Home home : homeInfoList) {
            N041Vo n041 = new N041Vo();
            n041.setUSERNAME("8331"); // 机构名称
            n041.setJKKH(null); // 健康卡号
            n041.setZYCS("1"); // 住院次数
            n041.setBAH(home.getHospitalizedSn()); // 病案号
            n041.setXM(home.getName()); // 姓名
            n041.setXB(String.valueOf(home.getGender())); // 性别
            if (!CheckUtils.isEmpty(home.getBirthday())) {
                n041.setCSRQ(TimeUtils.formatDateWithMilis(null, home.getBirthday())); // 出生日期
            }
            n041.setNL(home.getAge() / 1000000); // 年龄
            n041.setBZYZSNL(null); // 不足一周岁年龄
            n041.setXSECSTZ(null); // 新生儿出生体重克
            n041.setXSERYTZ(null);  // 新生儿入院体重克
            n041.setCSD(null); // 出生地
            n041.setGG(null); // 籍贯
            n041.setMZ(home.getRaceSn()); // 民族代码
            n041.setSFZH(home.getIdCard()); // 身份证号
            n041.setZY(home.getOccupationSn()); // 职业
            AddressInfo addressInfo = addressInfoHash.get(home.getNumber() + "1");
            if (null != addressInfo) {
                n041.setXZZ(addressInfo.getAddress() + addressInfo.getHouse()); // 现住址
                n041.setYB1(addressInfo.getZip()); // 邮编号
            } else {
                n041.setXZZ(null); // 现住址
                n041.setYB1(null); // 邮编号
            }
            n041.setDH(home.getPhone()); // 电话
            addressInfo = addressInfoHash.get(home.getNumber() + "3");
            if (null != addressInfo) {
                n041.setHKDZ(addressInfo.getAddress() + addressInfo.getHouse()); // 户口地址
                n041.setYB2(addressInfo.getZip()); // 户籍邮编
            } else {
                n041.setHKDZ(null); // 户口地址
                n041.setYB2(null); // 户籍邮编
            }
            n041.setDWDH(home.getBusinessPhone()); // 单位电话
            addressInfo = addressInfoHash.get(home.getNumber() + "4");
            if (null != addressInfo) {
                n041.setGZDWJDZ(addressInfo.getAddress() + addressInfo.getHouse()); // 工作单位及地址
                n041.setYB3(addressInfo.getZip()); // 单位邮编
            } else {
                n041.setGZDWJDZ(null); // 工作单位及地址
                n041.setYB3(null); // 单位邮编
            }
            n041.setLXRXM(home.getContactsName()); // 联系人
            n041.setGX(home.getContactsRelationSn()); // 0:本人或户主,1:配偶,2:子,3:女,4:孙子、孙女或外孙子、外孙女,5:父母,6:祖父母或外祖父母,7:兄、弟、姐,8:其他
            n041.setDZ(home.getContactsAddress()); // 地址
            n041.setDH2(home.getContactsPhone()); // 电话
            n041.setRYTJ(CheckUtils.isEmpty(home.getAdmissionChannel()) ? "2" : String.valueOf(home.getAdmissionChannel())); // 入院科别 入院途径 1 急诊 2 门诊 3 其他医疗机构转入 9 其他
            n041.setRYSJ(TimeUtils.formatDateWithMilis("yyyyMMdd", home.getAdmissionTime())); // 入院时间
            n041.setRYSJS(null); // 入院时间时
            n041.setRYBF(null); // 入院病房 入院床位
            n041.setCYSJ(TimeUtils.formatDateWithMilis("yyyyMMdd", home.getDischargeTime())); // 出院时间
            n041.setCYSJS(null); // 出院时间时
            n041.setCYBF(null); // 出院病房 床位
            n041.setSJZYTS(String.valueOf(home.getDays())); // 实际住院(天)
            n041.setYWGM(null); // 药物过敏 有无过敏
            n041.setGMYW(null); // 过敏药物疾病
            DiagnosisInfo diagnoseInfo = diagnosisInfoHash.get(home.getNumber() + "6");
            if (null != diagnoseInfo) {
                n041.setBLZD(diagnoseInfo.getDiagnosis()); // 病理诊断
                n041.setJBMM(diagnoseInfo.getIcd()); // 病理诊断编码
            } else {
                n041.setBLZD(null); // 病理诊断
                n041.setJBMM(null); // 病理诊断编码
            }
            diagnoseInfo = diagnosisInfoHash.get(home.getNumber() + "7");
            if (null != diagnoseInfo) {
                n041.setWBYY(diagnoseInfo.getDiagnosis()); // 病理诊断
                n041.setH23(diagnoseInfo.getIcd()); // 病理诊断编码
            } else {
                n041.setWBYY(null); // 病理诊断
                n041.setH23(null); // 病理诊断编码
            }
            diagnoseInfo = diagnosisInfoHash.get(home.getNumber() + "1");
            if (null != diagnoseInfo) {
                n041.setMZZD(diagnoseInfo.getDiagnosis()); // 病理诊断
                n041.setJBBM(diagnoseInfo.getIcd()); // 病理诊断编码
            } else {
                n041.setMZZD(null); // 病理诊断
                n041.setJBBM(null); // 病理诊断编码
            }
            diagnoseInfo = diagnosisInfoHash.get(home.getNumber() + "1");
            if (null != diagnoseInfo) {
                n041.setMZZD(diagnoseInfo.getDiagnosis()); // 病理诊断
                n041.setJBBM(diagnoseInfo.getIcd()); // 病理诊断编码
            } else {
                n041.setMZZD(null); // 病理诊断
                n041.setJBBM(null); // 病理诊断编码
            }
            List<DiagnosisInfo> numberDiagnosisList = diagnoseInfoListHash.get(home.getNumber());
            int i = 1;
            for (DiagnosisInfo numberDiagnosis : numberDiagnosisList) {
                if (i == 15) break;
                if(Integer.valueOf(1).equals(numberDiagnosis.getTag())) {
                    n041.setZYZD(numberDiagnosis.getDiagnosis()); // 主要诊断 his出院诊断
                    n041.setJBDM(numberDiagnosis.getIcd());
                    n041.setRYBQ(String.valueOf(numberDiagnosis.getInCondition()));
                } else {
                    // 1:门诊诊断,2:入院诊断,3:出院诊断,4:,5:医院感染,6:病理诊断,7:损伤中毒,8:术前诊断,9:术后诊断,10:并发症,11:病原学诊断,12:尸检主要诊断
                    String QTZD = "QTZD" + i;
                    String JBDM = "JBDM" + i;
                    String RYBQ = "RYBQ" + i;
                    Method setQTZD = n041.getClass().getDeclaredMethod("set" + QTZD, String.class);
                    Method setJBDM = n041.getClass().getDeclaredMethod("set" + JBDM, String.class);
                    Method setRYBQ = n041.getClass().getDeclaredMethod("set" + RYBQ, String.class);
                    setQTZD.invoke(n041, numberDiagnosis.getDiagnosis());
                    setJBDM.invoke(n041, numberDiagnosis.getIcd());
                    setRYBQ.invoke(n041, String.valueOf(numberDiagnosis.getInCondition()));
                    i++;
                }
            }
            Department department = departmentHash.get(home.getNumber() + "1");
            if (null != department) {
                n041.setRYKB(department.getCategorySn());
            }
            department = departmentHash.get(home.getNumber() + "2");
            if (null != department) {
                n041.setCYKB(department.getCategorySn());
            }
            department = departmentHash.get(home.getNumber() + "3");
            if (null != department) {
                n041.setZKKB(department.getCategorySn()); // 转科科室
            }
            n041.setGJ(home.getNationalitySn()); // 国籍
            n041.setHY(home.getMaritalSn()); // 婚姻 婚姻状况ID
            n041.setYLFKFS(String.valueOf(home.getPaymentMethod())); // 医疗付款方式
            // 设置手术
            operativeInfoList = operativeInfoHash.get(home.getNumber());
            int order = 1;
            if (!CheckUtils.isEmpty(operativeInfoList)) {
                for (OperativeInfo operativeInfo : operativeInfoList) {
                    Method setSSJCZBM = n041.getClass().getDeclaredMethod("setSSJCZBM" + order, String.class); // 手术及操作编码  ICD9
                    setSSJCZBM.invoke(n041, operativeInfo.getIcd());
                    Method setSSJCZRQ = n041.getClass().getDeclaredMethod("setSSJCZRQ" + order, String.class); // 手术及操作日期
                    String ssjczrq = TimeUtils.formatDateWithMilis("yyyyMMdd", operativeInfo.getTime());
                    setSSJCZRQ.invoke(n041, ssjczrq);
                    Method setSSJB = n041.getClass().getDeclaredMethod("setSSJB" + order, String.class); // 手术级别  1:一级手术 2:二级手术 3:三级手术 4:四级手术
                    setSSJB.invoke(n041, String.valueOf(operativeInfo.getGrade()));
                    Method setSSJCZMC = n041.getClass().getDeclaredMethod("setSSJCZMC" + order, String.class); // 手术及操作名称
                    setSSJCZMC.invoke(n041, operativeInfo.getOperative());
                    Method setSZ = n041.getClass().getDeclaredMethod("setSZ" + order, String.class); // 术者 主刀
                    setSZ.invoke(n041, operativeInfo.getOperatorName());
                    Method setYZ = n041.getClass().getDeclaredMethod("setYZ" + order, String.class); // I助
                    setYZ.invoke(n041, operativeInfo.getFirstName());
                    Method setEZ = n041.getClass().getDeclaredMethod("setEZ" + order, String.class); // II助
                    setEZ.invoke(n041, operativeInfo.getSecondName());
                    Method setQKDJ = n041.getClass().getDeclaredMethod("setQKDJ" + order, String.class); // 切口等级 1:0 2:Ⅰ 3:Ⅱ 4:Ⅲ
                    setQKDJ.invoke(n041, String.valueOf(operativeInfo.getIncisionType()));
                    Method setQKYHLB = n041.getClass().getDeclaredMethod("setQKYHLB" + order, String.class); // 切口愈合类别	1:甲 2:乙 3:丙 9:未知
                    setQKYHLB.invoke(n041, operativeInfo.getHealingCourse());
                    Method setMZFS = n041.getClass().getDeclaredMethod("setMZFS" + order, String.class);  // 麻醉方式 1:全身麻醉 11:0吸入麻醉 ....
                    setMZFS.invoke(n041, dictionaryHash.get(operativeInfo.getAnesthesiaType()));
                    Method setMZYS = n041.getClass().getDeclaredMethod("setMZYS" + order, String.class); // 麻醉医师
                    setMZYS.invoke(n041, operativeInfo.getAnesthesiaDoctor());
                    order++;
                }
            }
            n041.setBLH(null); // 病理号
            n041.setSWHZSJ(null); // 死亡患者尸检
            n041.setXX(String.valueOf(home.getBloodType())); // 血型
            n041.setRH(String.valueOf(home.getBloodRh())); // RH
            Person person = personHash.get(home.getNumber() + "11");
            if (null != person) {
                n041.setKZR(person.getPersonName()); // 科主任
            } else {
                n041.setKZR(null);
            }
            person = personHash.get(home.getNumber() + "12");
            if (null != person) {
                n041.setZRYS(person.getPersonName());
            } else {
                n041.setZRYS(null);
            }
            person = personHash.get(home.getNumber() + "13");
            if (null != person) {
                n041.setZZYS(person.getPersonName());
            } else {
                n041.setZZYS(null);
            }
            person = personHash.get(home.getNumber() + "14");
            if (null != person) {
                n041.setZYYS(person.getPersonName());
            } else {
                n041.setZYYS(null);
            }
            person = personHash.get(home.getNumber() + "21");
            if (null != person) {
                n041.setZRHS(person.getPersonName());
            } else {
                n041.setZRHS(null);
            }
            person = personHash.get(home.getNumber() + "32");
            if (null != person) {
                n041.setSXYS(person.getPersonName());
            } else {
                n041.setSXYS(null);
            }
            person = personHash.get(home.getNumber() + "32");
            if (null != person) {
                n041.setBMY(person.getPersonName());
            } else {
                n041.setBMY(null);
            }
            QualityInfo qualityInfo = qualityInfoHash.get(home.getNumber());
            if (null != qualityInfo) {
                n041.setBAZL(String.valueOf(qualityInfo.getQuality())); // 病案质量
                n041.setZKYS(qualityInfo.getDoctorName()); // 质控医师
                n041.setZKHS(qualityInfo.getNurseName()); // 质控护士
                if (!CheckUtils.isEmpty(qualityInfo.getTime())) {
                    n041.setZKRQ(TimeUtils.formatDateWithMilis("yyyyMMdd", qualityInfo.getTime())); // 质控日期
                }
            }
            n041.setLYFS(String.valueOf(home.getDischargeState())); // 离院方式 1:医嘱离院 2:医嘱转院 3:医嘱转社区卫生服务机构/乡镇卫生院 4: 非医嘱离院 5:死亡 9:其他
            if (!CheckUtils.isEmpty(n041.getLYFS()) && n041.getLYFS().equals("2")) {
                n041.setYZZY_YLJG(home.getReceivingOrganization()); // 医嘱转院，拟接收医疗机构名称
            }
            if (!CheckUtils.isEmpty(n041.getLYFS()) && n041.getLYFS().equals("3")) {
                n041.setWSY_YLJG(home.getReceivingOrganization()); // 医嘱转社区卫生服务机构/乡镇卫生院，拟接收医疗机构名称
            }
            n041.setSFZZYJH(Integer.valueOf(1).equals(home.getAgainIntention()) ? "2" : "1"); // 是否有31天再入院计划
            if (n041.getSFZZYJH().equals("2")) { // 1 无 2 有
                n041.setMD(home.getAgainObjective()); // 目的
            }
            n041.setRYQ_T(null); // 颅脑损伤患者昏迷入院前时间：天
            n041.setRYQ_XS(null); // 颅脑损伤患者昏迷入院前时间：小时
            n041.setRYQ_F(null); // 颅脑损伤患者昏迷入院前时间：分
            n041.setRYH_T(null); // 颅脑损伤患者昏迷入院后时间：天
            n041.setRYH_XS(null); // 颅脑损伤患者昏迷入院后时间：小时
            n041.setRYH_F(null);  // 颅脑损伤患者昏迷入院后时间：分
            // 设置费用
            Charge charge = chargeHash.get(home.getNumber());
            if (null != charge) {
                n041.setZFY(null == charge.getMedfeeSumamt() ? 0 : BigDecimal.valueOf(charge.getMedfeeSumamt()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue());
                n041.setZFJE(null == charge.getSelfFee() ? 0 : BigDecimal.valueOf(charge.getSelfFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue());
                n041.setYLFUF(null == charge.getOrdnMedServfee() ? 0 : BigDecimal.valueOf(charge.getOrdnMedServfee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 综合医疗服务类：(1)一般医疗服务费
                n041.setZLCZF(null == charge.getOrdnTrtOprtFee() ? 0 : BigDecimal.valueOf(charge.getOrdnTrtOprtFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 一般治疗操作费
                n041.setHLF(null == charge.getNursFee() ? 0 : BigDecimal.valueOf(charge.getNursFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 护理费住院费
                n041.setQTFY(null == charge.getComMedServOthFee() ? 0 : BigDecimal.valueOf(charge.getComMedServOthFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 其他费用
                n041.setBLZDF(null == charge.getPalgDiagFee() ? 0 : BigDecimal.valueOf(charge.getPalgDiagFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 诊断类：(5)病理诊断费
                n041.setSYSZDF(null == charge.getLabDiagFee() ? 0 : BigDecimal.valueOf(charge.getLabDiagFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 实验室诊断费
                n041.setYXXZDF(null == charge.getRdhyDiagFee() ? 0 : BigDecimal.valueOf(charge.getRdhyDiagFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 影像学诊断费
                n041.setLCZDXMF(null == charge.getClncDiseFee() ? 0 : BigDecimal.valueOf(charge.getClncDiseFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 临床诊断项目费
                n041.setFSSZLXMF(null == charge.getNsrgtrtItemFee() ? 0 : BigDecimal.valueOf(charge.getNsrgtrtItemFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 治疗类：(9)非手术治疗项目费
                n041.setWLZLF(null == charge.getClncPhysTrtFee() ? 0 : BigDecimal.valueOf(charge.getClncPhysTrtFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 临床物理治疗费
                n041.setSSZLF(null == charge.getRgtrtTrtFee() ? 0 : BigDecimal.valueOf(charge.getRgtrtTrtFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // (10)手术治疗费
                n041.setMAF(null == charge.getAnstFee() ? 0 : BigDecimal.valueOf(charge.getAnstFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 麻醉费
                n041.setSSF(null == charge.getOprnFee() ? 0 : BigDecimal.valueOf(charge.getOprnFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 手术费
                n041.setKFF(null == charge.getRhabFee() ? 0 : BigDecimal.valueOf(charge.getRhabFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 康复类：(11)康复费
                n041.setZYZLF(null == charge.getTcmTrtFee() ? 0 : BigDecimal.valueOf(charge.getTcmTrtFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 中医类:(12)中医治疗费
                n041.setXYF(null == charge.getWmfee() ? 0 : BigDecimal.valueOf(charge.getWmfee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 西药类:(13)西药费
                n041.setKJYWF(null == charge.getAbtlMednFee() ? 0 : BigDecimal.valueOf(charge.getAbtlMednFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 抗菌药物费
                n041.setZCYF(null == charge.getTcmpatFee() ? 0 : BigDecimal.valueOf(charge.getTcmpatFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 中药类:(14)中成药费
                n041.setZCYF1(null == charge.getTcmherbFee() ? 0 : BigDecimal.valueOf(charge.getTcmherbFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 中草药费
                n041.setXF(null == charge.getBloFee() ? 0 : BigDecimal.valueOf(charge.getBloFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 血液和血液制品类:(16)血费
                n041.setBDBLZPF(null == charge.getAlbuFee() ? 0 : BigDecimal.valueOf(charge.getAlbuFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 白蛋白类制品费
                n041.setQDBLZPF(null == charge.getGlonFee() ? 0 : BigDecimal.valueOf(charge.getGlonFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 球蛋白类制品费
                n041.setNXYZLZPF(null == charge.getClotfacFee() ? 0 : BigDecimal.valueOf(charge.getClotfacFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 凝血因子类制品费
                n041.setXBYZLZPF(null == charge.getCykiFee() ? 0 : BigDecimal.valueOf(charge.getCykiFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 细胞因子类制品费
                n041.setHCYYCLF(null == charge.getExamDspoMatlFee() ? 0 : BigDecimal.valueOf(charge.getExamDspoMatlFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 耗材类:(21)检查用一次性医用材料费
                n041.setYYCLF(null == charge.getTrtDspoMatlFee() ? 0 : BigDecimal.valueOf(charge.getTrtDspoMatlFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 治疗用一次性医用材料费
                n041.setYCXYYCLF(null == charge.getOprnDspoMatlFee() ? 0 : BigDecimal.valueOf(charge.getOprnDspoMatlFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 手术用一次性医用材料费
                n041.setQTF(null == charge.getOthFee() ? 0 : BigDecimal.valueOf(charge.getOthFee()).divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).doubleValue()); // 其他费
            }
            n041Vos.add(n041);
        }
        return n041Vos;
    }
}
