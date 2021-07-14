package com.biboheart.ddatabase.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "v_ehis_invoice_order_items")
public class Order {
    @Id
//    @Column(name = "ID")
    private String id;
//    @Column(name = "ORDER_SN")
    private String orderSn;//订单编号
//    @Column(name = "DISPOSAL_TYPE")
    private Integer disposalType;//处置类型
//    @Column(name = "ORDER_TYPE")
    private Integer orderType;//订单类型
//    @Column(name = "PROJECT_SN")
    private String projectSn;//项目编号
//    @Column(name = "PROJECT_NAME")
    private String projectName;//项目名称
//    @Column(name = "REIMBURSEMENT_TYPE")
    private Integer reimbursementType;//医保偿付等级
//    @Column(name = "NUM")
    private Long num;//数量
//    @Column(name = "SALE_UNIT")
    private String saleUnit;//单位
//    @Column(name = "PRICE")
    private Long price;//单价
//    @Column(name = "PAYMENT")
    private Long payment;//金额
//    @Column(name = "SELF_PAYMENT")
    private Long selfPayment;//自付金额
//    @Column(name = "CHARGE_TYPE_SN")
    private String chargeTypeSn;//收费类别编号
//    @Column(name = "CHARGE_TYPE_NAME")
    private String chargeTypeName;//收费类别名称
//    @Column(name = "PAYEE_SN")
    private String payeeSn;//收款人编号
//    @Column(name = "PAYEE_NAME")
    private String payeeName;//收款人姓名
//    @Column(name = "SPECIFICATIONS")
    private String specifications;//规格型号
//    @Column(name = "END_TIME")
    private Long endTime;//结算时间
}
