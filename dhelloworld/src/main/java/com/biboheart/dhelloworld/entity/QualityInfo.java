package com.biboheart.dhelloworld.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_platform_archives_quality")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_quality", comment = "病案中心-病历质控")
public class QualityInfo {
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

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '病案质量等级'")
    private Integer quality;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '质控医师编号'")
    private String doctorSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '质控医师'")
    private String doctorName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '质控护士编号'")
    private String nurseSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '质控护士'")
    private String nurseName;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '质控日期'")
    private Long time;
}
