package com.biboheart.demos.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ehis_platform_insurance_items_contrast")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_insurance_items_contrast", comment = "医保服务-项目对码")
public class ItemsContrast {
    @Id
    private Long id;

    @NotBlank(message = "本地编号不能为空")
    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '本地编号'")
    private String localSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '预留字段'")
    private String reservedField;

    @NotNull(message = "项目分类不能为空")
    @Column(columnDefinition = "int COMMENT '项目分类{1: 药品, 2: 诊疗项目, 3: 材料}'")
    private Integer type;

    @NotNull(message = "医保目录类型不能为空")
    @Column(columnDefinition = "VARCHAR(10) COMMENT '医保目录类型{101: 西药中成药, 102: 中药饮片, 103: 自制剂, 104: 民族药, 201: 医疗服务项目, 301: 医用耗材}'")
    private String insuranceType;

    @NotBlank(message = "国家码不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '国家码'")
    private String countryNumber;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '医保码'")
    private String insuranceNumber;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '国家项目名称'")
    private String countryName;

    @NotBlank(message = "名称不能为空")
    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '名称'")
    private String name;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '拼音首字母'")
    private String initials;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '剂型'")
    private String form;

    @NotBlank(message = "分类编号不能为空")
    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '分类编号'")
    private String typeSn;

    @NotBlank(message = "分类名称不能为空")
    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '分类名称'")
    private String typeName;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '附加类型编号'")
    private String additionalSn;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '附加类型名称'")
    private String additionalType;

    @NotNull(message = "医保偿付等级不能为空")
    @Column(columnDefinition = "int COMMENT '医保偿付等级{1: 甲, 2: 乙, 3: 丙}'")
    private Integer reimbursementType;

    @NotBlank(message = "费别编号不能为空")
    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '费别编号'")
    private String chargeTypeSn;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '费别名称'")
    private String chargeTypeName;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '批准文号'")
    private String approvalNumber;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '产地'")
    private String placeOrigin;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '规格'")
    private String specifications;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '基本单位'")
    private String basicUnit;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '包装单位'")
    private String packageUnit;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '剂量单位'")
    private String doseUnit;

    @Column(columnDefinition = "int COMMENT '转换系数'")
    private Integer conversionCoefficient;

    @Column(columnDefinition = "double COMMENT '自付比例'")
    private Double selfRatio;

    @Column(columnDefinition = "bigint COMMENT '拆零单价'")
    private Long pieceSellingPrice;

    @Column(columnDefinition = "bigint COMMENT '包装单价'")
    private Long packagePrice;

    @NotNull(message = "状态不能为空")
    @Column(columnDefinition = "int COMMENT '状态{1: 未对码, 2: 已对码待上传, 3: 中心审核中, 5: 完成, 6: 上传对码失败, 7: 中心审核未通过, 8: 变更, 9: 撤销}'")
    private Integer state;

    @NotNull(message = "最后更新时间不能为空")
    @Column(columnDefinition = "bigint COMMENT '最后更新时间'")
    private Long lastTime;

    @NotNull(message = "启用时间不能为空")
    @Column(columnDefinition = "bigint COMMENT '启用时间'")
    private Long enableTime;

    @Column(columnDefinition = "bigint COMMENT '停用时间'")
    private Long stopDate;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '版本号'")
    private String version;

    @Column(columnDefinition = "bigint COMMENT '标记'")
    private Long flags;
}
