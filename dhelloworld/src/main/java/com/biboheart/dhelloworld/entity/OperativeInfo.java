package com.biboheart.dhelloworld.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_platform_archives_operative")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_operative", comment = "病案中心-病案手术")
public class OperativeInfo {
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

    @Column(columnDefinition = "BIGINT COMMENT '手术时间'")
    private Long time;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '手术编码'")
    private String icd;

    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '手术名称'")
    private String operative;

    @Column(columnDefinition = "INT COMMENT '手术级别'")
    private Integer grade;

    @Column(columnDefinition = "varchar(10) DEFAULT NULL COMMENT '切口类型'")
    private String incisionType;

    @Column(columnDefinition = "varchar(10) DEFAULT NULL COMMENT '愈合等级'")
    private String healingCourse;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '主刀编号'")
    private String operatorSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '主刀医生'")
    private String operatorName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '一助编号'")
    private String firstSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '一助姓名'")
    private String firstName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '二助编号'")
    private String secondSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '二助姓名'")
    private String secondName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '麻醉方式'")
    private String anesthesiaType;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '麻醉医师编号'")
    private String anesthesiaSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '麻醉医师'")
    private String anesthesiaDoctor;

    @Column(columnDefinition = "INT COMMENT '1:主要手术,2:附加手术'")
    private Integer tag;

    @Column(columnDefinition = "INT DEFAULT NULL COMMENT '手术次序'")
    private Integer orderNumber;
}
