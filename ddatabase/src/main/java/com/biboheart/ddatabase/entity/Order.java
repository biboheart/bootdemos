package com.biboheart.ddatabase.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ehis_main_invoice_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank(message = "订单编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '订单编号'")
    private String orderSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '源结算编号'")
    private String sourceSn;

    //    @NotNull(message = "订单类型不能为空")
    @Column(columnDefinition = "int DEFAULT 1 COMMENT '订单类型{1: 收费, 2: 退费}'")
    private Integer orderType;

    //    @NotBlank(message = "账单编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '账单编号'")
    private String billSn;

    //    @NotNull(message = "患者费别不能为空")
    @Column(columnDefinition = "INT COMMENT '患者费别{1: 自费, 2: 本地医保, 3: 异地医保, 4: 工伤}'")
    private Integer cost;

    //    @NotNull(message = "业务类型不能为空")
    @Column(columnDefinition = "INT COMMENT '业务类型{1: 门诊, 2: 住院, 3: 体检, 4: 急诊, 9: 其它}'")
    private Integer classify;

    //    @NotBlank(message = "就诊业务编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '就诊业务编号'")
    private String number;

    //    @NotBlank(message = "患者索引号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者索引号'")
    private String patientSn;

    //    @NotBlank(message = "姓名不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '姓名'")
    private String patientName;

    //    @NotBlank(message = "拼音首字母不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '拼音首字母'")
    private String initials;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '患者出生日期'")
    private Long birthday;

    //    @NotNull(message = "性别不能为空")
    @Column(columnDefinition = "INT COMMENT '性别{1: 男, 2: 女, 9: 未知}'")
    private Integer gender;

    @Column(columnDefinition = "INT COMMENT '年龄'")
    private Integer age;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '联系电话'")
    private String phone;

    //    @NotBlank(message = "账单类型编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '账单类型编号'")
    private String typeSn;

    //    @NotBlank(message = "账单类型名称不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '账单类型名称'")
    private String typeName;

    //    @NotBlank(message = "账单名称不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '账单名称'")
    private String billName;

    //    @NotNull(message = "订单金额不能为空")
    @Column(columnDefinition = "BIGINT COMMENT '订单金额'")
    private Long orderPayment;

    //    @NotNull(message = "支付金额不能为空")
    @Column(columnDefinition = "BIGINT COMMENT '支付金额'")
    private Long payment;

    //    @NotNull(message = "折扣金额不能为空")
    @Column(columnDefinition = "BIGINT COMMENT '折扣金额'")
    private Long discountPayment = 0L;

    @Column(columnDefinition = "BIGINT DEFAULT NULL COMMENT '结算时间'")
    private Long endTime;

    @Column(columnDefinition = "INT COMMENT '发票类型{1：普通发票，2：冲红发票}'")
    private Integer invoiceType;

    //    @NotNull(message = "状态不能为空")
    @Column(columnDefinition = "INT COMMENT '状态{1：完成收费，2：申请开票，5：完成开票，6：开票失败，7：已冲红，9：取消}'")
    private Integer state;

    @Column(columnDefinition = "INT COMMENT '同步项目状态{1：待同步，5：同步成功，6：同步失败}'")
    private Integer syncState;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL COMMENT '状态描述'")
    private String stateRemark;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '发票号'")
    private String invoiceSn;

    //    @NotNull(message = "创建时间不能为空")
    @Column(columnDefinition = "BIGINT COMMENT '创建时间'")
    private Long createTime;

    @Column(columnDefinition = "BIGINT COMMENT '开票时间'")
    private Long applyTime;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '收款人编号'")
    private String payeeSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '收款人姓名'")
    private String payeeName;

    @Column(columnDefinition = "text DEFAULT NULL COMMENT '备注'")
    private String remark;
}
