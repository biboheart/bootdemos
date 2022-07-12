package com.biboheart.dhelloworld.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_main_aims_dictionary")
@org.hibernate.annotations.Table(appliesTo = "ehis_main_aims_dictionary", comment = "手麻系统-数据字典")
public class Dictionary {

    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '目录编号'")
    private String catalogSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '目录名称'")
    private String catalogName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '名称'")
    private String name;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '拼音首字母'")
    private String initials;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '字典值'")
    private String value;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '附加值'")
    private String addValue;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '附加名称'")
    private String addName;

    @Column(columnDefinition = "int COMMENT '排序'")
    private Integer orderNumber;

}
