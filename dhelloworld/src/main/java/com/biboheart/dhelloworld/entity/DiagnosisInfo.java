package com.biboheart.dhelloworld.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_platform_archives_diagnosis")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_diagnosis", comment = "病案中心-病案首页诊断")
public class DiagnosisInfo {
    @Id
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '住院号'")
    private String number;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '病案号'")
    private String hospitalizedSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者索引号'")
    private String patientSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者编号'")
    private String patientNumber;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '诊断编码'")
    private String icd;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '诊断名称'")
    private String diagnosis;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '入院病情'")
    private Integer inCondition;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '出院情况'")
    private String outCondition;

    @Column(columnDefinition = "varchar(200) default null comment '病理号'")
    private String pathologNumber;

    @Column(columnDefinition = "INT COMMENT '诊断类别 1:门诊诊断,2:入院诊断,3:出院诊断,4:,5:医院感染,6:病理诊断,7:损伤中毒,8:术前诊断,9:术后诊断,10:并发症,11:病原学诊断,12:尸检主要诊断'")
    private Integer type;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '诊断标识 1:主要诊断,2:其他诊断 默认0 诊断类型为出院诊断时只能提供1/2'")
    private Integer tag;

    @Column(columnDefinition = "INT COMMENT '诊断次序'")
    private Integer orderNumber;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '诊断时间'")
    private Long time;
}
