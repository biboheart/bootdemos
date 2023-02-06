package com.biboheart.demos.domain.packager;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.IdcardUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.demos.collect.model.HealthDataModel;
import com.biboheart.demos.entity.DictionaryDiagnosis;
import com.biboheart.demos.entity.InsurancePatient;
import com.biboheart.demos.entity.Person;
import com.biboheart.demos.service.DictionaryDiagnosisService;
import com.biboheart.demos.service.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordDataPackager {
    public static Map<String, Object> homeData(HealthDataModel healthDataModel, InsurancePatient insurancePatient, DictionaryDiagnosisService dictionaryDiagnosisService) {
        Map<String, Object> baseinfo = new HashMap<>();
        //baseinfo.put("mdtrt_sn", "H36118100203" + healthDataModel.getBAH()); // 就医流水号
        baseinfo.put("mdtrt_sn", "H33070300089" + healthDataModel.getBAH()); // 就医流水号
        baseinfo.put("mdtrt_id", insurancePatient.getCentreNumber()); // 就诊ID
        baseinfo.put("psn_no", insurancePatient.getInsurancePersonSn()); // 人员编号
        String idcard = healthDataModel.getSFZH();
        if (!CheckUtils.isEmpty(idcard) && '\'' == idcard.charAt(0)) {
            idcard = idcard.substring(1);
        }
        baseinfo.put("ipt_no", healthDataModel.getBAH()); // 住院号
        baseinfo.put("medcasno", healthDataModel.getBAH()); // 病案号
        baseinfo.put("patn_ipt_cnt", 1); // 患者住院次数
        baseinfo.put("psn_name", healthDataModel.getXM()); // 人员姓名
        baseinfo.put("gend", healthDataModel.getXB()); // 性别
        if (!CheckUtils.isEmpty(idcard)) {
            baseinfo.put("brdy", TimeUtils.formatDate("yyyy-MM-dd", IdcardUtils.birthday(idcard))); // 出生日期
        }
        baseinfo.put("ntly", "CHN"); // 国籍
        baseinfo.put("ntly_name", "中国"); // 国籍名称
        baseinfo.put("nwb_adm_type", null); // 新生儿出生体重
        baseinfo.put("nwb_bir_wt", null); // 新生儿出生体重
        baseinfo.put("nwb_adm_wt", null); // 新生儿入院体重
        baseinfo.put("birplc", null); // 出生地
        baseinfo.put("napl", healthDataModel.getGG()); // 籍贯
        baseinfo.put("naty_name", null); // 民族名称
        baseinfo.put("naty", healthDataModel.getMZ()); // 民族
        baseinfo.put("certno", idcard); // 证件号码
        baseinfo.put("prfs", healthDataModel.getZY()); // 职业
        baseinfo.put("mrg_stas", healthDataModel.getHY()); // 婚姻状态
        baseinfo.put("curr_addr_poscode", null); // 现住址-邮政编码
        baseinfo.put("curr_addr", null); // 现住址
        baseinfo.put("psn_tel", healthDataModel.getDH()); // 个人联系电话
        baseinfo.put("resd_addr_prov", null); // 户口地址-省（自治区、直辖市）
        baseinfo.put("resd_addr_city", null); // 户口地址-市（地区）
        baseinfo.put("resd_addr_coty", null); // 户口地址-县（区）
        baseinfo.put("resd_addr_subd", null); // 户口地址-乡（镇、街道办事处）
        baseinfo.put("resd_addr_vil", null); // 户口地址-村（街、路、弄等）
        baseinfo.put("resd_addr_housnum", null); // 户口地址-门牌号码
        baseinfo.put("resd_addr_poscode", null); // 户口地址- 邮政编码
        baseinfo.put("resd_addr", null); // 户口地址
        baseinfo.put("empr_tel", null); // 工作单位联系电话
        baseinfo.put("empr_poscode", null); // 工作单位- 邮政编码
        baseinfo.put("empr_addr", null); // 工作单位及地址
        baseinfo.put("coner_tel", healthDataModel.getDH2()); // 联系人电话
        baseinfo.put("coner_name", healthDataModel.getLXRXM()); // 联系人姓名
        baseinfo.put("coner_addr", null); // 联系人地址
        baseinfo.put("coner_rlts_code", "0".equals(healthDataModel.getGX()) ? null : healthDataModel.getGX()); // 与联系人关系代码
        baseinfo.put("adm_way_name", "门诊"); // 入院途径名称
        baseinfo.put("adm_way_code", "2"); // 入院途径代码
        baseinfo.put("trt_type_name", "西医"); // 治疗类别名称
        baseinfo.put("trt_type", "10"); // 治疗类别
        baseinfo.put("adm_caty", healthDataModel.getRYKB()); // 入院科别
        baseinfo.put("adm_ward", healthDataModel.getRYBF()); // 入院病房
        baseinfo.put("adm_date", TimeUtils.formatDateWithMilis("yyyy-MM-dd", insurancePatient.getAdmissionTime())); // 入院日期
        baseinfo.put("dscg_date", TimeUtils.formatDateWithMilis("yyyy-MM-dd", insurancePatient.getLeaveTime())); // 出院日期
        baseinfo.put("dscg_caty", healthDataModel.getCYKB()); // 出院科别
        baseinfo.put("refldeptCatyName", null); // 转科科别名称
        baseinfo.put("dscg_ward", null); // 出院病房
        baseinfo.put("ipt_days", healthDataModel.getSJZYTS()); // 住院天数
        baseinfo.put("drug_dicm_flag", null); // 药物过敏标志
        baseinfo.put("dicm_drug_name", null); // 过敏药物名称
        baseinfo.put("die_autp_flag", null); // 死亡患者尸检标志
        baseinfo.put("abo_code", healthDataModel.getXX()); // ABO血型代码
        baseinfo.put("abo_name", null); // ABO血型名称
        baseinfo.put("rh_code", healthDataModel.getRH()); // Rh血型代码
        baseinfo.put("rh_name", null); // RH血型
        baseinfo.put("die_flag", null); // 死亡标志
        baseinfo.put("deptdrt_name", healthDataModel.getKZR()); // 科主任姓名
        baseinfo.put("chfdr_name", healthDataModel.getZRYS()); // 主任( 副主任)医师姓名
        baseinfo.put("atddr_name", healthDataModel.getZZYS()); // 主治医生姓名
        baseinfo.put("chfpdr_name", healthDataModel.getZZYS()); // 主诊医师姓名
        baseinfo.put("ipt_dr_name", healthDataModel.getZYYS()); // 住院医师姓名
        baseinfo.put("resp_nurs_name", healthDataModel.getZRHS()); // 责任护士姓名
        baseinfo.put("train_dr_name", healthDataModel.getJXYS()); // 进修医师姓名
        baseinfo.put("intn_dr_name", healthDataModel.getSXYS()); // 实习医师姓名
        baseinfo.put("codr_name", healthDataModel.getBMY()); // 编码员姓名
        baseinfo.put("qltctrl_dr_name", healthDataModel.getZKYS()); // 质控医师姓名
        baseinfo.put("qltctrl_nurs_name", healthDataModel.getZKHS()); // 质控护士姓名
        baseinfo.put("medcas_qlt_name", null); // 病案质量名称
        baseinfo.put("medcas_qlt_code", healthDataModel.getBAZL()); // 病案质量代码
        if (!CheckUtils.isEmpty(healthDataModel.getZKRQ())) {
            baseinfo.put("qltctrl_date", TimeUtils.formatDateWithMilis("yyyy-MM-dd", TimeUtils.getDateMillis(healthDataModel.getZKRQ(), "yyyyMMdd"))); // 质控日期
        }
        baseinfo.put("dscg_way_name", null); // 离院方式名称
        baseinfo.put("dscg_way", healthDataModel.getLYFS()); // 离院方式
        baseinfo.put("acp_medins_code", null); // 拟接收医疗机构代码
        baseinfo.put("acp_medins_name", null); // 拟接收医疗机构名称
        baseinfo.put("dscg_31days_rinp_flag", null); // 出院 31天内再住院计划标志
        baseinfo.put("dscg_31days_rinp_pup", null); // 出院31天内再住院目的
        baseinfo.put("damg_intx_ext_rea", null); // 损伤、中毒的外部原因
        baseinfo.put("damg_intx_ext_rea_disecode", healthDataModel.getWBYY()); // 损伤、中毒的外部原因
        baseinfo.put("brn_damg_bfadm_coma_dura", null); // 颅脑损伤患者入院前昏迷时长
        baseinfo.put("brn_damg_afadm_coma_dura", null); // 颅脑损伤患者入院后昏迷时长
        baseinfo.put("vent_used_dura", null); // 呼吸机使用时长
        baseinfo.put("cnfm_date", null); // 确诊日期
        baseinfo.put("patn_dise_diag_crsp", null); // 患者疾病诊断对照
        baseinfo.put("patn_dise_diag_crsp_code", null); // 住院患者疾病诊断对照代码
        baseinfo.put("ipt_patn_diag_inscp", null); // 住院患者诊断符合情况
        baseinfo.put("ipt_patn_diag_inscp_code", null); // 住院患者诊断符合情况代码
        baseinfo.put("dscg_trt_rslt", null); // 出院治疗结果
        baseinfo.put("dscg_trt_rslt_code", null); // 出院治疗结果代码
        baseinfo.put("medins_orgcode", null); // 医疗机构组织机构代码
        baseinfo.put("age", healthDataModel.getNL()); // 医疗机构组织机构代码
        baseinfo.put("aise", null); // 过敏源
        baseinfo.put("pote_intn_dr_name", null); // 研究生实习医师姓名
        baseinfo.put("hbsag", null); // 乙肝表面抗原（HBsAg）
        baseinfo.put("hcvab", null); // 丙型肝炎抗体（HCV-Ab）
        baseinfo.put("hivab", null); // 艾滋病毒抗体（hiv-ab）
        baseinfo.put("resc_cnt", null); // 抢救次数
        baseinfo.put("resc_succ_cnt", null); // 抢救成功次数
        baseinfo.put("hosp_dise_fsttime", null); // 手术、治疗、检查、诊断为本院第一例
        baseinfo.put("hif_pay_way_name", null); // 医保基金付费方式名称
        baseinfo.put("hif_pay_way_code", "1"); // 医保基金付费方式代码
        baseinfo.put("med_fee_paymtd_name", null); // 医疗费用支付方式名称
        baseinfo.put("medfee_paymtd_code", healthDataModel.getYLFKFS()); // 医疗费用支付方式代码
        baseinfo.put("selfpay_amt", healthDataModel.getZFJE()); // 自付金额
        baseinfo.put("medfee_sumamt", healthDataModel.getZFY()); // 医疗费总额
        baseinfo.put("ordn_med_servfee", healthDataModel.getYLFUF()); // 一般医疗服务费
        baseinfo.put("ordn_trt_oprt_fee", healthDataModel.getZLCZF()); // 一般治疗操作费
        baseinfo.put("nurs_fee", healthDataModel.getHLF()); // 护理费
        baseinfo.put("com_med_serv_oth_fee", healthDataModel.getQTFY()); // 综合医疗服务类其他费用
        baseinfo.put("palg_diag_fee", healthDataModel.getBLZDF()); // 病理诊断费
        baseinfo.put("lab_diag_fee", healthDataModel.getSYSZDF()); // 实验室诊断费
        baseinfo.put("rdhy_diag_fee", healthDataModel.getYXXZDF()); // 影像学诊断费
        baseinfo.put("clnc_dise_fee", healthDataModel.getLCZDXMF()); // 临床诊断项目费
        baseinfo.put("nsrgtrt_item_fee", healthDataModel.getFSSZLXMF()); // 非手术治疗项目费
        baseinfo.put("clnc_phys_trt_fee", healthDataModel.getWLZLF()); // 临床物理治疗费
        baseinfo.put("rgtrt_trt_fee", healthDataModel.getSSZLF()); // 手术治疗费
        baseinfo.put("anst_fee", healthDataModel.getMAF()); // 麻醉费
        baseinfo.put("oprn_fee", healthDataModel.getSSF()); // 手术费
        baseinfo.put("rhab_fee", healthDataModel.getKFF()); // 康复费
        baseinfo.put("tcm_trt_fee", healthDataModel.getZYZLF()); // 中医治疗费
        baseinfo.put("wmfee", healthDataModel.getXYF()); // 西药费
        baseinfo.put("abtl_medn_fee", healthDataModel.getKJYWF()); // 抗菌药物费用
        baseinfo.put("tcmpat_fee", healthDataModel.getZCYF()); // 中成药费
        baseinfo.put("tcmherb_fee", healthDataModel.getZCYF1()); // 中药饮片费
        baseinfo.put("blo_fee", healthDataModel.getXF()); // 血费
        baseinfo.put("albu_fee", healthDataModel.getBDBLZPF()); // 白蛋白类制品费
        baseinfo.put("glon_fee", healthDataModel.getQDBLZPF()); // 球蛋白类制品费
        baseinfo.put("clotfac_fee", healthDataModel.getNXYZLZPF()); // 凝血因子类制品费
        baseinfo.put("cyki_fee", healthDataModel.getXBYZLZPF()); // 细胞因子类制品费
        baseinfo.put("exam_dspo_matl_fee", healthDataModel.getHCYYCLF()); // 检查用一次性医用材料费
        baseinfo.put("trt_dspo_matl_fee", healthDataModel.getYYCLF()); // 治疗用一次性医用材料费
        baseinfo.put("oprn_dspo_matl_fee", healthDataModel.getYCXYYCLF()); // 手术用一次性医用材料费
        baseinfo.put("oth_fee", healthDataModel.getQTF()); // 其他费
        baseinfo.put("vali_flag", "1"); // 有效标志
        //baseinfo.put("fixmedins_code", "H36118100203"); // 定点医药机构编号
        baseinfo.put("fixmedins_code", "H33070300089"); // 定点医药机构编号
        List<Map<String, Object>> diseinfoList = homeDise(healthDataModel, dictionaryDiagnosisService);
        List<Map<String, Object>> oprninfoList = homeOprn(healthDataModel);
        List<Map<String, Object>> icuinfoList = homeIcuinfo(healthDataModel);
        Map<String, Object> map = new HashMap<>();
        map.put("baseinfo", baseinfo);
        map.put("diseinfo", diseinfoList);
        map.put("oprninfo", oprninfoList);
        map.put("icuinfo", icuinfoList);
        return map;
    }

    private static List<Map<String, Object>> homeDise(HealthDataModel healthDataModel, DictionaryDiagnosisService dictionaryDiagnosisService) {
        List<Map<String, Object>> diseinfoList = new ArrayList<>();
        Map<String, Object> diseinfo = new HashMap<>();
        diseinfo.put("palg_no", null); // 病理号
        diseinfo.put("ipt_patn_disediag_type_code", "1"); // 住院患者疾病诊断类型代码
        diseinfo.put("disediag_type", "出院诊断"); // 疾病诊断类型
        diseinfo.put("maindiag_flag", "1"); // 主诊断标志
        String icd = healthDataModel.getJBDM();
        DictionaryDiagnosis dd = dictionaryDiagnosisService.load(icd);
        if (null != dd) {
            diseinfo.put("diag_code", "医保版".equals(dd.getIssuer()) ? dd.getIcd() : dd.getContrastIcd()); // 诊断代码
            diseinfo.put("diag_name", "医保版".equals(dd.getIssuer()) ? dd.getName() : dd.getContrastName()); // 诊断代码
            diseinfo.put("inhosp_diag_code", "医保版".equals(dd.getIssuer()) ? dd.getIcd() : dd.getContrastIcd()); // 院内诊断代码
            diseinfo.put("inhosp_diag_name", "医保版".equals(dd.getIssuer()) ? dd.getName() : dd.getContrastName()); // 院内诊断名称
        } else {
            diseinfo.put("diag_code", icd); // 诊断代码
            diseinfo.put("diag_name", null); // 诊断代码
            diseinfo.put("inhosp_diag_code", icd); // 院内诊断代码
            diseinfo.put("inhosp_diag_name", null); // 院内诊断名称
        }
        diseinfo.put("adm_dise_cond_code", null); // 入院疾病病情代码
        diseinfo.put("adm_cond", null); // 入院病情
        diseinfo.put("adm_cond_code", null); // 入院时病情代码
        diseinfo.put("high_diag_evid", null); // 最高诊断依据
        diseinfo.put("bkup_deg", null); // 分化程度
        diseinfo.put("bkup_deg_code", null); // 分化程度代码
        diseinfo.put("vali_flag", "1"); // 有效标志
        diseinfo.put("ipt_medcas_hmpg_sn", healthDataModel.getBAH() + "0"); // 住院病案首页流水号
        //diseinfo.put("mdtrt_sn", "H36118100203" + healthDataModel.getBAH()); // 就医流水号
        diseinfo.put("mdtrt_sn", "H33070300089" + healthDataModel.getBAH()); // 就医流水号
        //diseinfo.put("fixmedins_code", "H36118100203"); // 定点医药机构编号
        diseinfo.put("fixmedins_code", "H33070300089"); // 定点医药机构编号
        diseinfoList.add(diseinfo);
        for (int i = 1; i < 16; i ++) {
            String jbdm = BeanUtils.getFieldValueByName("JBDM" + i, healthDataModel, String.class);
            String qtzd = BeanUtils.getFieldValueByName("QTZD" + i, healthDataModel, String.class);
            String rybq = BeanUtils.getFieldValueByName("RYBQ" + i, healthDataModel, String.class);
            if (CheckUtils.isEmpty(jbdm) || "null".equals(jbdm)) {
                break;
            }
            dd = dictionaryDiagnosisService.load(jbdm);
            if (null == dd) {
                break;
            }
            diseinfo = new HashMap<>();
            diseinfo.put("palg_no", null); // 病理号
            diseinfo.put("ipt_patn_disediag_type_code", "1"); // 住院患者疾病诊断类型代码
            diseinfo.put("disediag_type", "出院诊断"); // 疾病诊断类型
            diseinfo.put("maindiag_flag", "0"); // 主诊断标志
            diseinfo.put("diag_code", "医保版".equals(dd.getIssuer()) ? dd.getIcd() : dd.getContrastIcd()); // 诊断代码
            diseinfo.put("diag_name", "医保版".equals(dd.getIssuer()) ? dd.getName() : dd.getContrastName()); // 诊断代码
            diseinfo.put("inhosp_diag_code", "医保版".equals(dd.getIssuer()) ? dd.getIcd() : dd.getContrastIcd()); // 院内诊断代码
            diseinfo.put("inhosp_diag_name", "医保版".equals(dd.getIssuer()) ? dd.getName() : dd.getContrastName()); // 院内诊断名称
            diseinfo.put("adm_dise_cond_code", "null".equals(rybq) ? null : rybq); // 入院疾病病情代码
            diseinfo.put("adm_cond", null); // 入院病情
            diseinfo.put("adm_cond_code", null); // 入院时病情代码
            diseinfo.put("high_diag_evid", null); // 最高诊断依据
            diseinfo.put("bkup_deg", null); // 分化程度
            diseinfo.put("bkup_deg_code", null); // 分化程度代码
            diseinfo.put("vali_flag", "1"); // 有效标志
            diseinfo.put("ipt_medcas_hmpg_sn", healthDataModel.getBAH() + i); // 住院病案首页流水号
            //diseinfo.put("mdtrt_sn", "H36118100203" + healthDataModel.getBAH()); // 就医流水号
            diseinfo.put("mdtrt_sn", "H33070300089" + healthDataModel.getBAH()); // 就医流水号
            //diseinfo.put("fixmedins_code", "H36118100203"); // 定点医药机构编号
            diseinfo.put("fixmedins_code", "H33070300089"); // 定点医药机构编号
            diseinfoList.add(diseinfo);
        }
        return diseinfoList;
    }

    private static List<Map<String, Object>> homeOprn(HealthDataModel healthDataModel) {
        List<Map<String, Object>> oprninfoList = new ArrayList<>();
        Map<String, Object> oprninfo;
        for (int i = 1; i < 8; i ++) {
            String ssjczbm = BeanUtils.getFieldValueByName("SSJCZBM" + i, healthDataModel, String.class);
            if (CheckUtils.isEmpty(ssjczbm) || "null".equals(ssjczbm)) {
                break;
            }
            oprninfo = new HashMap<>();
            String ssjczrq = BeanUtils.getFieldValueByName("SSJCZRQ" + i, healthDataModel, String.class);
            String ssjb = BeanUtils.getFieldValueByName("SSJB" + i, healthDataModel, String.class);
            String ssjczmc = BeanUtils.getFieldValueByName("SSJCZMC" + i, healthDataModel, String.class);
            String sz = BeanUtils.getFieldValueByName("SZ" + i, healthDataModel, String.class);
            String yz = BeanUtils.getFieldValueByName("YZ" + i, healthDataModel, String.class);
            String ez = BeanUtils.getFieldValueByName("EZ" + i, healthDataModel, String.class);
            String qkdj = BeanUtils.getFieldValueByName("QKDJ" + i, healthDataModel, String.class);
            String qkyhlb = BeanUtils.getFieldValueByName("QKYHLB" + i, healthDataModel, String.class);
            String mzfs = BeanUtils.getFieldValueByName("MZFS" + i, healthDataModel, String.class);
            String mzys = BeanUtils.getFieldValueByName("MZYS" + i, healthDataModel, String.class);
            if (CheckUtils.isEmpty(ssjczrq) || CheckUtils.isEmpty(ssjczrq.toUpperCase())) {
                oprninfo.put("oprn_oprt_date", null); // 手术操作日期
            } else {
                oprninfo.put("oprn_oprt_date", TimeUtils.formatDateWithMilis("yyyy-MM-dd", TimeUtils.getDateMillis(ssjczrq, "yyyyMMdd"))); // 手术操作日期
            }
            oprninfo.put("oprn_oprt_name", ssjczmc); // 手术操作名称
            oprninfo.put("oprn_oprt_code", ssjczbm); // 手术操作代码
            oprninfo.put("oprn_oprt_sn", null); // 手术操作序列号
            oprninfo.put("oprn_lv_code", "null".equals(ssjb) ? null : ssjb); // 手术级别代码
            oprninfo.put("oprn_lv_name", null); // 手术级别名称
            oprninfo.put("oper_name", "null".equals(sz) ? null : sz); // 手术者姓名
            oprninfo.put("asit1_name", "null".equals(yz) ? null : yz); // 助手Ⅰ姓名
            oprninfo.put("asit2_name", "null".equals(ez) ? null : ez); // 助手Ⅱ姓名
            oprninfo.put("sinc_heal_lv", null); // 手术切口愈合等级
            oprninfo.put("sinc_heal_lv_code", "null".equals(qkdj) ? null : qkdj); // 手术切口愈合等级代码
            oprninfo.put("anst_mtd_name", null); // 麻醉-方法名称
            oprninfo.put("anst_mtd_code", "null".equals(mzfs) ? null : mzfs); // 麻醉-方法代码
            oprninfo.put("anst_dr_name", "null".equals(mzfs) ? null : mzys); // 麻醉医师姓名
            oprninfo.put("oprn_oper_part", null); // 手术操作部位
            oprninfo.put("oprn_oper_part_code", null); // 手术操作部位代码
            oprninfo.put("oprn_con_time", null); // 手术持续时间
            oprninfo.put("anst_lv_name", null); // 麻醉分级名称
            oprninfo.put("anst_lv_code", null); // 麻醉分级代码
            oprninfo.put("oprn_patn_type", null); // 手术患者类型
            oprninfo.put("oprn_patn_type_code", null); // 手术患者类型代码
            oprninfo.put("main_oprn_flag", null); // 主要手术标志
            oprninfo.put("anst_asa_lv_code", null); // 麻醉ASA分级代码
            oprninfo.put("anst_asa_lv_name", null); // 麻醉ASA分级名称
            oprninfo.put("anst_medn_code", null); // 麻醉药物代码
            oprninfo.put("anst_medn_name", null); // 麻醉药物名称
            oprninfo.put("anst_medn_dos", null); // 麻醉药物剂量
            oprninfo.put("unt", null); // 计量单位
            oprninfo.put("anst_begntime", null); // 麻醉开始时间
            oprninfo.put("anst_endtime", null); // 麻醉结束时间
            oprninfo.put("anst_copn_code", null); // 麻醉合并症代码
            oprninfo.put("anst_copn_name", null); // 麻醉合并症名称
            oprninfo.put("anst_copn_dscr", null); // 麻醉合并症描述
            oprninfo.put("pacu_begntime", null); // 复苏室开始时
            oprninfo.put("pacu_endtime", null); // 复苏室结束时间
            oprninfo.put("canc_oprn_flag", null); // 取消手术标志
            oprninfo.put("vali_flag", "1"); // 有效标志
            oprninfo.put("ipt_medcas_hmpg_sn", healthDataModel.getBAH() + i); // 住院病案首页流水号
            //oprninfo.put("mdtrt_sn", "H36118100203" + healthDataModel.getBAH()); // 就医流水号
            oprninfo.put("mdtrt_sn", "H33070300089" + healthDataModel.getBAH()); // 就医流水号
            oprninfoList.add(oprninfo);
        }
        if (CheckUtils.isEmpty(oprninfoList)) {
            oprninfo = new HashMap<>();
            oprninfo.put("oprn_oprt_date", null); // 手术操作日期
            oprninfo.put("oprn_oprt_name", null); // 手术操作名称
            oprninfo.put("oprn_oprt_code", null); // 手术操作代码
            oprninfo.put("oprn_oprt_sn", null); // 手术操作序列号
            oprninfo.put("oprn_lv_code", null); // 手术级别代码
            oprninfo.put("oprn_lv_name", null); // 手术级别名称
            oprninfo.put("oper_name", null); // 手术者姓名
            oprninfo.put("asit1_name", null); // 助手Ⅰ姓名
            oprninfo.put("asit2_name", null); // 助手Ⅱ姓名
            oprninfo.put("sinc_heal_lv", null); // 手术切口愈合等级
            oprninfo.put("sinc_heal_lv_code", null); // 手术切口愈合等级代码
            oprninfo.put("anst_mtd_name", null); // 麻醉-方法名称
            oprninfo.put("anst_mtd_code", null); // 麻醉-方法代码
            oprninfo.put("anst_dr_name", null); // 麻醉医师姓名
            oprninfo.put("oprn_oper_part", null); // 手术操作部位
            oprninfo.put("oprn_oper_part_code", null); // 手术操作部位代码
            oprninfo.put("oprn_con_time", null); // 手术持续时间
            oprninfo.put("anst_lv_name", null); // 麻醉分级名称
            oprninfo.put("anst_lv_code", null); // 麻醉分级代码
            oprninfo.put("oprn_patn_type", null); // 手术患者类型
            oprninfo.put("oprn_patn_type_code", null); // 手术患者类型代码
            oprninfo.put("main_oprn_flag", null); // 主要手术标志
            oprninfo.put("anst_asa_lv_code", null); // 麻醉ASA分级代码
            oprninfo.put("anst_asa_lv_name", null); // 麻醉ASA分级名称
            oprninfo.put("anst_medn_code", null); // 麻醉药物代码
            oprninfo.put("anst_medn_name", null); // 麻醉药物名称
            oprninfo.put("anst_medn_dos", null); // 麻醉药物剂量
            oprninfo.put("unt", null); // 计量单位
            oprninfo.put("anst_begntime", null); // 麻醉开始时间
            oprninfo.put("anst_endtime", null); // 麻醉结束时间
            oprninfo.put("anst_copn_code", null); // 麻醉合并症代码
            oprninfo.put("anst_copn_name", null); // 麻醉合并症名称
            oprninfo.put("anst_copn_dscr", null); // 麻醉合并症描述
            oprninfo.put("pacu_begntime", null); // 复苏室开始时
            oprninfo.put("pacu_endtime", null); // 复苏室结束时间
            oprninfo.put("canc_oprn_flag", null); // 取消手术标志
            oprninfo.put("vali_flag", "0"); // 有效标志
            oprninfo.put("ipt_medcas_hmpg_sn", healthDataModel.getBAH() + "0"); // 住院病案首页流水号
            //oprninfo.put("mdtrt_sn", "H36118100203" + healthDataModel.getBAH()); // 就医流水号
            oprninfo.put("mdtrt_sn", "H33070300089" + healthDataModel.getBAH()); // 就医流水号
            oprninfoList.add(oprninfo);
        }
        return oprninfoList;
    }

    private static List<Map<String, Object>> homeIcuinfo(HealthDataModel healthDataModel) {
        List<Map<String, Object>> icuinfoList = new ArrayList<>();
        Map<String, Object> icuinfo = new HashMap<>();
        icuinfo.put("icu_code", null); // 重症监护室代码
        icuinfo.put("inpool_icu_time", null); // 进入监护室时间
        icuinfo.put("out_icu_time", null); // 退出监护室时间
        //icuinfo.put("medins_orgcode", "3342061130-1"); // 医疗机构组织机构代码
        //icuinfo.put("fixmedins_code", "H36118100203"); // 定点医药机构编号
        icuinfo.put("medins_orgcode", "32557065-0"); // 医疗机构组织机构代码
        icuinfo.put("fixmedins_code", "H33070300089"); // 定点医药机构编号
        icuinfo.put("nurscare_lv_code", null); // 护理等级代码
        icuinfo.put("nurscare_lv_name", null); // 护理等级名称
        icuinfo.put("nurscare_days", null); // 护理天数
        icuinfo.put("back_icu", null); // 重返重症监护室标志
        icuinfo.put("vali_flag", "0"); // 有效标志
        //icuinfo.put("ipt_medcas_hmpg_sn", "H36118100203" + healthDataModel.getBAH()); // 住院病案首页流水号
        icuinfo.put("ipt_medcas_hmpg_sn", "H33070300089" + healthDataModel.getBAH()); // 住院病案首页流水号
        icuinfo.put("mdtrt_sn", healthDataModel.getBAH() + "0"); // 住院病案首页流水号
        icuinfoList.add(icuinfo);
        return icuinfoList;
    }

    public static Map<String, Object> settleData(HealthDataModel healthDataModel,
                                                 InsurancePatient insurancePatient,
                                                 DictionaryDiagnosisService dictionaryDiagnosisService,
                                                 PersonService personService
    ) {
        Map<String, Object> setlinfo = new HashMap<>();
        setlinfo.put("setl_id", insurancePatient.getInsuranceOrderSn()); // 结算ID
        setlinfo.put("mdtrt_id", insurancePatient.getCentreNumber()); // 就诊ID
        setlinfo.put("psn_no", insurancePatient.getInsurancePersonSn()); // 人员编号
        setlinfo.put("hi_no", insurancePatient.getInsurancePersonSn()); // 医保编号
        setlinfo.put("medcasno", healthDataModel.getBAH()); // 病案号
        setlinfo.put("dcla_time", null); // 申报时间
        setlinfo.put("ntly", "CHN"); // 国籍
        setlinfo.put("nwb_admtype", null); // 新生儿入院类型
        setlinfo.put("nwbbirwt", null); // 新生儿出生体重
        setlinfo.put("nwbadmwt", null); // 新生儿入院体重
        setlinfo.put("mul_nwb_bir_wt", null); // 多新生儿出生体重
        setlinfo.put("mul_nwb_adm_wt", null); // 多新生儿入院体重
        setlinfo.put("opsp_diag_caty", null); // 门诊慢特病诊断科别
        setlinfo.put("opsp_mdtrt_date", null); // 门诊慢特病就诊日期
        setlinfo.put("prfs", healthDataModel.getZY()); // 职业
        setlinfo.put("curr_addr", null); // 现住址
        setlinfo.put("emp_name", null); // 单位名称
        setlinfo.put("emp_addr", null); // 单位地址
        setlinfo.put("emp_tel", null); // 单位电话
        setlinfo.put("poscode", null); // 邮编
        setlinfo.put("coner_name", null); // 联系人姓名
        setlinfo.put("patn_rlts", null); // 与患者关系
        setlinfo.put("coner_addr", null); // 联系人地址
        setlinfo.put("coner_tel", null); // 联系人电话
        setlinfo.put("adm_way", "2"); // 入院途径代码
        setlinfo.put("trt_type", "10"); // 治疗类别
        setlinfo.put("adm_time", TimeUtils.formatDateWithMilis("yyyy-MM-dd", insurancePatient.getAdmissionTime())); // 入院日期
        setlinfo.put("dscg_time", TimeUtils.formatDateWithMilis("yyyy-MM-dd", insurancePatient.getLeaveTime())); // 出院日期
        setlinfo.put("dscg_caty", healthDataModel.getCYKB()); // 出院科别
        setlinfo.put("refldept_dept", null); // 转科科别
        setlinfo.put("otp_wm_dise", null); // 门（急）诊诊断（西医诊断）
        setlinfo.put("wm_dise_code", healthDataModel.getJBDM()); // 西医疾病代码
        setlinfo.put("otptcmdise", null); // 门（急）诊诊断（中医诊断）
        setlinfo.put("tcmdisecode", null); // 中医疾病代码
        setlinfo.put("dscg_way", healthDataModel.getLYFS()); // 离院方式
        setlinfo.put("acp_optins_code", null); // 拟接收医疗机构代码
        setlinfo.put("acp_medins_name", null); // 拟接收医疗机构名称
        setlinfo.put("bill_code", "-"); // 票据代码
        setlinfo.put("bill_no", "-"); // 票据号码
        setlinfo.put("biz_sn", insurancePatient.getInsuranceOrderSn()); // 业务流水号
        setlinfo.put("days_rinp_flag_31", null); // 出院 31天内再住院计划标志
        setlinfo.put("days_rinp_pup_31", null); // 出院31天内再住院目的
        setlinfo.put("pwcry_bfadm_coma_dura", null); // 颅脑损伤患者入院前昏迷时长
        setlinfo.put("pwcry_afadm_coma_dura", null); // 颅脑损伤患者入院后昏迷时长
        setlinfo.put("vent_used_dura", null); // 呼吸机使用时长
        setlinfo.put("spga_nurscare_days", null); // 特级护理天数
        setlinfo.put("lv1_nurscare_days", null); // 一级护理天数
        setlinfo.put("scd_nurscare_days", null); // 二级护理天数
        setlinfo.put("lv3_nurscare_days", null); // 三级护理天数
        String zzys = healthDataModel.getZZYS();
        if (!CheckUtils.isEmpty(zzys)) {
            Person person = personService.load(zzys);
            if (null != person) {
                zzys = person.getCentreSn();
            }
        }
        setlinfo.put("chfpdr_code", zzys); // 主诊医师代码
        setlinfo.put("setl_begn_date", TimeUtils.formatDateWithMilis("yyyy-MM-dd", insurancePatient.getSettleTime())); // 结算开始日期
        setlinfo.put("setl_end_date", TimeUtils.formatDateWithMilis("yyyy-MM-dd", insurancePatient.getSettleTime())); // 结算结束日期
        setlinfo.put("medins_fill_dept", "病案室"); // 医疗机构填报部门
        setlinfo.put("medins_fill_psn", "曹冬梅"); // 医疗机构填报人
        setlinfo.put("resp_nurs_code", null); // 责任护士代码
        setlinfo.put("stas_type", "1"); // 状态分类
        setlinfo.put("hi_paymtd", "4"); // 医保支付方式
        List<Map<String, Object>> diseinfoList = settleDise(healthDataModel, dictionaryDiagnosisService);
        List<Map<String, Object>> oprninfoList = settleOprn(healthDataModel, personService);
        List<Map<String, Object>> icuinfoList = new ArrayList<>();
        List<Map<String, Object>> opspdiseinfoList = new ArrayList<>();
        List<Map<String, Object>> bldinfoList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("setlinfo", setlinfo);
        map.put("opspdiseinfo", opspdiseinfoList);
        map.put("diseinfo", diseinfoList);
        map.put("oprninfo", oprninfoList);
        map.put("icuinfo", icuinfoList);
        map.put("bldinfo", bldinfoList);
        return map;
    }

    private static List<Map<String, Object>> settleDise(HealthDataModel healthDataModel, DictionaryDiagnosisService dictionaryDiagnosisService) {
        List<Map<String, Object>> diseinfoList = new ArrayList<>();
        Map<String, Object> diseinfo = new HashMap<>();
        diseinfo.put("diag_type", "1"); // 诊断类别
        diseinfo.put("maindiag_flag", "1"); // 主诊断标志
        String icd = healthDataModel.getJBDM();
        DictionaryDiagnosis dd = dictionaryDiagnosisService.load(icd);
        if (null != dd) {
            diseinfo.put("diag_code", "医保版".equals(dd.getIssuer()) ? dd.getIcd() : dd.getContrastIcd()); // 诊断代码
            diseinfo.put("diag_name", "医保版".equals(dd.getIssuer()) ? dd.getName() : dd.getContrastName()); // 诊断名称
        } else {
            diseinfo.put("diag_code", icd); // 诊断代码
            diseinfo.put("diag_name", null); // 诊断名称
        }
        diseinfo.put("adm_cond_type", "1"); // 入院病情类型
        diseinfoList.add(diseinfo);
        for (int i = 1; i < 16; i ++) {
            String jbdm = BeanUtils.getFieldValueByName("JBDM" + i, healthDataModel, String.class);
            String qtzd = BeanUtils.getFieldValueByName("QTZD" + i, healthDataModel, String.class);
            String rybq = BeanUtils.getFieldValueByName("RYBQ" + i, healthDataModel, String.class);
            if (CheckUtils.isEmpty(jbdm) || "null".equals(jbdm)) {
                break;
            }
            dd = dictionaryDiagnosisService.load(jbdm);
            if (null == dd) {
                break;
            }
            diseinfo = new HashMap<>();
            diseinfo.put("diag_type", "1"); // 诊断类别
            diseinfo.put("maindiag_flag", "0"); // 主诊断标志
            diseinfo.put("diag_code", "医保版".equals(dd.getIssuer()) ? dd.getIcd() : dd.getContrastIcd()); // 诊断代码
            diseinfo.put("diag_name", "医保版".equals(dd.getIssuer()) ? dd.getName() : dd.getContrastName()); // 诊断名称
            diseinfo.put("adm_cond_type", "1"); // 入院病情类型
            diseinfoList.add(diseinfo);
        }
        return diseinfoList;
    }

    private static List<Map<String, Object>> settleOprn(HealthDataModel healthDataModel, PersonService personService) {
        List<Map<String, Object>> oprninfoList = new ArrayList<>();
        Map<String, Object> oprninfo;
        boolean hasMain = false;
        String ssjczrq = null;
        for (int i = 1; i < 8; i ++) {
            String ssjczbm = BeanUtils.getFieldValueByName("SSJCZBM" + i, healthDataModel, String.class);
            if (CheckUtils.isEmpty(ssjczbm) || "null".equals(ssjczbm)) {
                break;
            }
            oprninfo = new HashMap<>();
            String ssjczmc = BeanUtils.getFieldValueByName("SSJCZMC" + i, healthDataModel, String.class);
            String sz = BeanUtils.getFieldValueByName("SZ" + i, healthDataModel, String.class);
            String mzfs = BeanUtils.getFieldValueByName("MZFS" + i, healthDataModel, String.class);
            String mzys = BeanUtils.getFieldValueByName("MZYS" + i, healthDataModel, String.class);
            if (null == ssjczrq) {
                ssjczrq = BeanUtils.getFieldValueByName("SSJCZRQ" + i, healthDataModel, String.class);
            }
            if (!hasMain) {
                oprninfo.put("oprn_oprt_type", "1"); // 手术操作类别
                hasMain = true;
            } else {
                oprninfo.put("oprn_oprt_type", "2"); // 手术操作类别
            }
            oprninfo.put("oprn_oprt_code", ssjczbm); // 手术操作代码
            oprninfo.put("oprn_oprt_name", ssjczmc); // 手术操作名称
            oprninfo.put("anst_way", "null".equals(mzfs) ? null : mzfs); // 麻醉-方法代码
            if (!CheckUtils.isEmpty(sz)) {
                Person person = personService.load(sz);
                if (null != person) {
                    sz = person.getCentreSn();
                }
            }
            oprninfo.put("oper_dr_code", sz); // 手术者姓名(术者医师代码)
            if (!CheckUtils.isEmpty(mzys)) {
                Person person = personService.load(mzys);
                if (null != person) {
                    mzys = person.getCentreSn();
                }
            }
            oprninfo.put("anst_dr_code", "null".equals(mzys) ? null : mzys); // 麻醉医师姓名(麻醉医师代码)
            oprninfo.put("oprn_oprt_begntime", null == ssjczrq ? null : TimeUtils.formatDateWithMilis(null, TimeUtils.getDateMillis(ssjczrq, "yyyyMMdd"))); // 手术操作开始时间
            oprninfo.put("oprn_oprt_endtime", null == ssjczrq ? null : TimeUtils.formatDateWithMilis(null, TimeUtils.getDateMillis(ssjczrq, "yyyyMMdd"))); // 手术操作结束时间
            oprninfo.put("anst_begntime", null); // 麻醉开始时间
            oprninfo.put("anst_endtime", null); // 麻醉结束时间
            oprninfoList.add(oprninfo);
        }
        return oprninfoList;
    }
}
