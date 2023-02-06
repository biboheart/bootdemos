package com.biboheart.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bh_test_insurance_patient")
public class InsurancePatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String patientName;
    private String hospitalizedSn;
    private String centreNumber;
    private String insurancePersonSn;
    private String insuranceOrderSn;
    private Long admissionTime;
    private Long leaveTime;
    private Long settleTime;
    @Lob
    private String homeData;
    @Lob
    private String settleData;
    private Integer state;
}
