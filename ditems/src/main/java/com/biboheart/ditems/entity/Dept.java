package com.biboheart.ditems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bh_dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sn; // 科室编号
    private String name; // 科室编号
    private Long scope; // 业务范围
    private Long classify; // 服务范围
    private Integer orderNumber; // 科室序号
}
