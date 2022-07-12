package com.biboheart.dexcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bh_tmp_diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '医保码'")
    private String insuranceSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '医保码'")
    private String countryNumber;
}
