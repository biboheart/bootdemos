package com.biboheart.dhelloworld.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name = "ehis_platform_archives_address")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_address", comment = "病案中心-地址信息")
public class AddressInfo {
    @Id
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '病案号'")
    private String number;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者住院号'")
    private String hospitalizedSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者索引号'")
    private String patientSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者编号'")
    private String patientNumber;

    @Column(columnDefinition = "INT COMMENT '类型 1: 现住址, 2: 出生地, 3: 户口地地址, 4: 工作地址, 5: 籍贯, 9: 其它'")
    private Integer type;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '地址编号'")
    private String addressSn;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '地址'")
    private String address;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '详细地址'")
    private String house;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '邮编'")
    private String zip;
}
