package com.biboheart.demos.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bh_test_diagnosis")
public class DictionaryDiagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NumberFormat
    @ExcelProperty(value = "诊断编号", index = 0)
    @NotBlank(message = "诊断icd码不能为空")
    @Column(columnDefinition = "varchar(50) default null comment '编号'")
    private String icd;

    @ExcelProperty("附加编码")
    @Column(columnDefinition = "varchar(50) default null comment '附加编号'")
    private String additional;

    @ExcelProperty(value = "诊断名称", index = 1)
    @NotBlank(message = "诊断名称不能为空")
    @Column(columnDefinition = "varchar(200) default null comment '名称'")
    private String name;

    @Column(columnDefinition = "varchar(100) default null comment '拼音首字母'")
    private String initials;

    @NotNull(message = "诊断类型不能为空")
    @Column(columnDefinition = "int comment '诊断类型 1: icd-10疾病编码, 2: 损伤中毒的外部原因, 3: 肿瘤形态学编码, 4: 中医疾病编码, 5: 中医病征编码'")
    private Integer type;

    @Column(columnDefinition = "varchar(50) default null comment '版本号'")
    private String versionNumber;

    @Column(columnDefinition = "varchar(50) default null comment '发行方'")
    private String issuer;

    @Column(columnDefinition = "varchar(50) default null comment '对照码'")
    private String contrastIcd;

    @Column(columnDefinition = "varchar(50) default null comment '对照名称'")
    private String contrastName;
}
