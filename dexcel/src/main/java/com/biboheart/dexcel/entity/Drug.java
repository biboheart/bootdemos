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

    @Column(columnDefinition = "varchar(50) default null comment '预留字段'")
    private String reservedField;

    @Column(columnDefinition = "varchar(50) default null comment '项目编号'")
    private String sn;

    @Column(columnDefinition = "int default 1 comment '默认销售单位 1: 门诊, 2: 住院, 4: 急诊'")
    private Integer saleUnit;
}
