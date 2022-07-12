package com.biboheart.dexcel.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "bh_tmp_home")
@org.hibernate.annotations.Table(appliesTo = "bh_tmp_home", comment = "病案中心-病案首页")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "住院号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '住院号'")
    private String number;

    @NotBlank(message = "病案号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '病案号'")
    private String hospitalizedSn;

    @NotBlank(message = "患者索引号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者索引号'")
    private String patientSn;

    @NotBlank(message = "患者编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者编号'")
    private String patientNumber;

    @NotBlank(message = "医疗机构不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '医疗机构'")
    private String organization;

    @NotBlank(message = "机构代码不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '机构代码'")
    private String organizationSn;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '付费方式'")
    private Integer paymentMethod;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '健康卡号'")
    private String cardNumber;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '住院次序'")
    private Integer hospitalizedCount;

    @NotBlank(message = "姓名不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '姓名'")
    private String name;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '姓名拼音首字母'")
    private String initials;

    @NotNull(message = "性别不能为空")
    @Column(columnDefinition = "INT COMMENT '性别'")
    private Integer gender;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '出生日期'")
    private Long birthday;

    @NotNull(message = "年龄不能为空")
    @Column(columnDefinition = "INT COMMENT '年龄'")
    private Integer age;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '电话'")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '国籍'")
    private String nationality;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '国籍编码'")
    private String nationalitySn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '出生体重'")
    private String birthWeight;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '入院体重'")
    private String admissionWeight;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '民族'")
    private String race;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '民族编码'")
    private String raceSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '身份证号'")
    private String idCard;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '职业'")
    private String occupation;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '职业编码'")
    private String occupationSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '婚姻'")
    private String marital;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '婚姻编码'")
    private String maritalSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '工作单位电话'")
    private String businessPhone;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '工作单位'")
    private String business;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '联系人姓名'")
    private String contactsName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '联系人关系'")
    private String contactsRelation;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '联系人关系编码'")
    private String contactsRelationSn;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '联系人地址'")
    private String contactsAddress;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '联系人详细地址'")
    private String contactsHouse;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '联系人电话'")
    private String contactsPhone;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '入院途径'")
    private Integer admissionChannel;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '入院时间'")
    private Long admissionTime;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '出院时间'")
    private Long dischargeTime;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '住院天数'")
    private Integer days;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '有无过敏药物'")
    private Integer hasAllergyDrug;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '过敏药物'")
    private String allergyDrug;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '死亡患者尸检'")
    private Integer hasAutopsy;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '血型'")
    private Integer bloodType;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT 'Rh'")
    private Integer bloodRh;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '离院方式'")
    private Integer dischargeState;

    @Column(columnDefinition = "varchar(200) default null comment '接收机构名称'")
    private String receivingOrganization;

    @Column(columnDefinition = "int default null comment '是否有31天再入院计划'")
    private Integer againIntention;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '再住院目的'")
    private String againObjective;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '颅脑损伤院前昏迷时间'")
    private Long frontHospitalTime;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '颅脑操作院后昏迷时间'")
    private Long afterHospitalTime;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '有无单病程管理'")
    private Integer hasDrgs;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '临床路径管理'")
    private Integer path;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '诊断符合情况(门诊与出院)'")
    private Integer diagnosisComplianceOutpatient;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '诊断符合情况(入院与出院)'")
    private Integer diagnosisComplianceHospitalized;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '诊断符合情况(术前与术后)'")
    private Integer diagnosisComplianceOperation;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '诊断符合情况(临床与病理)'")
    private Integer diagnosisComplianceClinical;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '诊断符合情况(放射与病理)'")
    private Integer diagnosisComplianceRadiation;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '抢救次数'")
    private Integer rescueCount;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '抢救成功次数'")
    private Integer rescueSuccessCount;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '转归情况'")
    private Integer reversion;
}
