package com.biboheart.dexcel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bh_tmp_serial_expiration_time")
public class ExpirationTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemsSn; // 项目编号不能为空
    private String itemsName; // 项目名称不能为空
    private String reservedField; // 坐标编号
    private String batchNumber; // 批号
    private Long expirationTime; // 失效时间
}
