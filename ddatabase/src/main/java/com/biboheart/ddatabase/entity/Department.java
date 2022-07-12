package com.biboheart.ddatabase.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_platform_archives_department")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_department", comment = "病案中心-病案科室")
public class Department {
    @Id
    private Long id;

    @Column(columnDefinition = "varchar(50) default null comment '住院号'")
    private String number;

    @Column(columnDefinition = "varchar(50) default null comment '病案号'")
    private String hospitalizedSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者索引号'")
    private String patientSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者编号'")
    private String patientNumber;

    @Column(columnDefinition = "int comment '类型 1: 入院科室, 2: 出院科室, 3: 转科科室, 9: 其它'")
    private Integer type;

    @Column(columnDefinition = "varchar(50) default null comment '科室编号'")
    private String deptSn;

    @Column(columnDefinition = "varchar(50) default null comment '科室名称'")
    private String deptName;

    @Column(columnDefinition = "varchar(50) default null comment '科别编号'")
    private String categorySn;

    @Column(columnDefinition = "varchar(50) default null comment '科别名称'")
    private String categoryName;

    @Column(columnDefinition = "varchar(50) default null comment '病房'")
    private String roomName;

    @Column(columnDefinition = "varchar(50) default null comment '床位'")
    private String bed;
}
