package com.biboheart.ditems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bh_items_def_services")
public class ItemsDefServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeSn; // 类型
    private Integer deptType; // 科室类型 0:指定科室, 1:就诊科室, 2:就诊病区, 4:院外执行
    private String deptSn; // 科室编号
    private String deptName; // 科室名称
    private Long targetScope; // 目标范围 1: 门诊, 2: 住院, 4: 体检, 8: 急诊
    private String targetSn; // 被服务的科室编号
    private String targetName; // 被服务的科室名称
}
