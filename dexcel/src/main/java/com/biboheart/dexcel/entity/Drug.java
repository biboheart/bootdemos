package com.biboheart.dexcel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bh_tmp_drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '医保码'")
    private String insuranceSn;

    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '偿付等级'")
    private String reimbursementType;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '目录名称'")
    private String catalogName;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT NULL COMMENT '类型编号'")
    private String typeSn;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT NULL COMMENT '二级类型'")
    private String additionalType;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT NULL COMMENT '管理等级'")
    private String manageLevel;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '标准码'")
    private String standardSn;

    @Column(columnDefinition = "VARCHAR(256) DEFAULT NULL COMMENT '名称'")
    private String name;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '通用名'")
    private String commonName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '剂型'")
    private String form;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '药理毒理分类'")
    private String pharmacology;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '药品安全等级'")
    private String safetyLevel;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '费别'")
    private String chargeTypeName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '批准文号'")
    private String approvalNumber;

    @Column(columnDefinition = "VARCHAR(1024) DEFAULT NULL COMMENT '产地'")
    private String placeOrigin;

    @Column(columnDefinition = "VARCHAR(256) DEFAULT NULL COMMENT '规格'")
    private String specifications;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '基本单位'")
    private String basicUnit;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '包装单位'")
    private String packageUnit;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '转换系数'")
    private String conversionCoefficient;
}
