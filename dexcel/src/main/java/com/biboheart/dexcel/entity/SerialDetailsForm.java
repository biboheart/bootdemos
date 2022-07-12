package com.biboheart.dexcel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bh_tmp_serial_details_form")
public class SerialDetailsForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serialId;
    private String itemsSn; // 项目编号不能为空
    private String reservedField; // 坐标编号
    private String itemsName; // 项目名称不能为空
    private String initials; // 项目名称首字母
    private String typeSn; // 分类编号不能为空
    private String typeName; // 分类名称不能为空
    private String additionalSn; // 附加类型编号
    private String additionalType; // 附加类型
    private Integer antibioticLevel; // 1: 一线, 2: 二线, 3: 三线
    private String pharmacology; // 药理毒理分类
    private String safetyLevel; // 药品安全等级
    private Integer manage; // 管理类别 1: 处方药, 2: 甲类非处方药, 3: 乙类非处方药
    private String manageLevel; // 管理等级 A
    private Integer reimbursementType; // 医保偿付等级 1: 甲, 2: 乙, 3: 丙, 9: 自费
    private String chargeTypeSn; // 费别编号
    private String chargeTypeName; // 费别名称
    private String batchNumber; // 批号
    private String placeOrigin; // 产地
    private String originType; // 产地类型
    private String specifications; // 规格
    private String basicUnit; // 基本单位
    private String packageUnit; // 包装单位
    private Integer basicCount; // 基本单位数量
    private Integer packageCount; // 包装单位数量
    private Integer conversionCoefficient; // 转换系数
    private Long buyingPrice; // 进价
    private Long sellingPrice; // 售价
    private Long pieceSellingPrice; // 拆零单价
    private Long pieceBuyingPrice; // 拆零单价
    private Long time; // 业务时间
    private String billingPersonSn; // 开单人员编号
    private String billingPersonName; // 开单人员姓名
    private String billingDeptSn; // 开单科室编号
    private String billingDeptName; // 开单科室名称
    private String pharmacyDeptSn; // 给药科室编号
    private String pharmacyDeptName; // 给药科室名称
    private String batchSn; // 入库批次号
    private Long storageTime; // 入库时间
    private Long manufactureTime; // 生产日期
    private Long expirationTime; // 失效时间
    private String shelves; // 货架
    private String storageLocation; // 货位
    private Integer saleUnit; // 0: 不拆零, 7: 拆零
}
