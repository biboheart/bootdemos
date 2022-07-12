package com.biboheart.dhelloworld.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_platform_archives_person")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_person", comment = "病案中心-病案人员")
public class Person {
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

    @Column(columnDefinition = "int comment '类型 11: 科主任, 12: 主任(副主任)医师, 13: 主治医师, 14: 住院医师, 21: 责任护士, 31: 进修医师, 32 实习医师, 51: 编码员, 99: 其它'")
    private Integer type;

    @Column(columnDefinition = "varchar(50) default null comment '人员编号'")
    private String personSn;

    @Column(columnDefinition = "varchar(50) default null comment '人员名称'")
    private String personName;
}
