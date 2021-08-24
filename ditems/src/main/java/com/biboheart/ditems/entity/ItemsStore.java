package com.biboheart.ditems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bh_items_store")
public class ItemsStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storeSn; // 库房编号
    private String storeName; // 库房名称
    private String itemsSn; // 项目编号
    private String itemsName; // 项目名称
    private String typeSn; // 类型
    private Integer packageCount; // 数量
    private String targetDept; // 目标科室
    private Long targetScope; // 目标科室范围 1: 门诊, 2: 住院, 4: 体检, 8: 急诊
    private Integer targetSource; // 目标来源
}
