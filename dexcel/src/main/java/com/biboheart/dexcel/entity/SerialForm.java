package com.biboheart.dexcel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bh_tmp_serial_form")
public class SerialForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String billSn; // 库房系统单据号
    private String contrastBillSn; // 相关单据号
    private String businessSn; // 业务流水号
    private String systemSn; // 业务系统编号
    private String storeSn; // 库房编号
    private String storeName; // 库房名称
    private String businessType; // 业务类型
    private String businessName; // 业务名称
    private String otherOrg; // 对方机构
    private String otherOrgName; // 对方机构名称
    private String otherDeptSn; // 对方科室编号
    private String otherDeptName; // 对方科室名称
    private String otherPersonSn; // 对方人员编号
    private String otherPersonName; // 对方人员名称
    private String supplier; // 供应商
    private String digested; // 摘要
    private Long startTime; // 业务开始时间
    private Long endTime; // 业务完成时间
    private String storekeeperSn; // 仓管员编号
    private String storekeeperName; // 仓管员名称
    private String personSn; // 业务员编号
    private String personName; // 业务员名称
    private String auditorSn; // 审核员编号
    private String auditorName; // 审核员
    private Long examineTime; // 审核时间
}
