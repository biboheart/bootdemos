package com.biboheart.ddatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ehis_main_invoice_order_items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank(message = "来源id不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '来源id'")
    private String sourceId;

    //    @NotBlank(message = "订单编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '订单编号'")
    private String orderSn;

    //    @NotNull(message = "处置类型不能为空")
    @Column(columnDefinition = "int DEFAULT 1 COMMENT '处置类型{1: 记账, 2: 销账}'")
    private Integer disposalType;

    //    @NotBlank(message = "项目编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '项目编号'")
    private String projectSn;

    //    @NotBlank(message = "项目名称不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '项目名称'")
    private String projectName;

    //    @NotNull(message = "医保偿付等级不能为空")
    @Column(columnDefinition = "INT COMMENT '医保偿付等级{1: 甲, 2: 乙, 3: 丙}'")
    private Integer reimbursementType;

    //    @NotNull(message = "数量不能为空")
    @Column(columnDefinition = "bigint COMMENT '数量'")
    private Long num;

    //    @NotBlank(message = "单位不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '单位'")
    private String saleUnit;

    //    @NotNull(message = "单价不能为空")
    @Column(columnDefinition = "bigint COMMENT '单价'")
    private Long price;

    //    @NotNull(message = "金额不能为空")
    @Column(columnDefinition = "bigint COMMENT '金额'")
    private Long payment;

    //    @NotNull(message = "折扣金额不能为空")
    @Column(columnDefinition = "BIGINT COMMENT '折扣金额'")
    private Long discountPayment;

    //    @NotNull(message = "自付金额不能为空")
    @Column(columnDefinition = "bigint COMMENT '自付金额'")
    private Long selfPayment;

    @Column(columnDefinition = "INT COMMENT '状态{1：记录，5：完成结算，9：取消}'")
    private Integer state;

    //    @NotBlank(message = "收费类别编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '收费类别编号'")
    private String chargeTypeSn;

    //    @NotBlank(message = "收费类别名称不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '收费类别名称'")
    private String chargeTypeName;

    //    @NotBlank(message = "税收分类编号不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '税收分类编号'")
    private String invoiceTypeSn;

    //    @NotBlank(message = "税收分类名称不能为空")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '税收分类名称'")
    private String invoiceTypeName;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '规格'")
    private String specifications;
}
