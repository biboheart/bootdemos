package com.biboheart.ddatabase.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ehis_platform_archives_charge")
@org.hibernate.annotations.Table(appliesTo = "ehis_platform_archives_charge", comment = "病案中心-住院费用")
public class Charge {
    @Id
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '住院号'")
    private String number;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '病案号'")
    private String hospitalizedSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者索引号'")
    private String patientSn;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '患者编号'")
    private String patientNumber;

    @Column(columnDefinition = "bigint default null comment '医疗费总额'")
    private Long medfeeSumamt;

    @Column(columnDefinition = "bigint default null comment '自付金额'")
    private Long selfFee;

    @Column(columnDefinition = "bigint default null comment '一般医疗服务费'")
    private Long ordnMedServfee;

    @Column(columnDefinition = "bigint default null comment '一般治疗操作费'")
    private Long ordnTrtOprtFee;

    @Column(columnDefinition = "bigint default null comment '护理费'")
    private Long nursFee;

    @Column(columnDefinition = "bigint default null comment '综合医疗服务类其他费用'")
    private Long comMedServOthFee;

    @Column(columnDefinition = "bigint default null comment '病理诊断费'")
    private Long palgDiagFee;

    @Column(columnDefinition = "bigint default null comment '实验室诊断费'")
    private Long labDiagFee;

    @Column(columnDefinition = "bigint default null comment '影像学诊断费'")
    private Long rdhyDiagFee;

    @Column(columnDefinition = "bigint default null comment '临床诊断项目费'")
    private Long clncDiseFee;

    @Column(columnDefinition = "bigint default null comment '非手术治疗项目费'")
    private Long nsrgtrtItemFee;

    @Column(columnDefinition = "bigint default null comment '临床物理治疗费'")
    private Long clncPhysTrtFee;

    @Column(columnDefinition = "bigint default null comment '手术治疗费'")
    private Long rgtrtTrtFee;

    @Column(columnDefinition = "bigint default null comment '麻醉费'")
    private Long anstFee;

    @Column(columnDefinition = "bigint default null comment '手术费'")
    private Long oprnFee;

    @Column(columnDefinition = "bigint default null comment '康复费'")
    private Long rhabFee;

    @Column(columnDefinition = "bigint default null comment '中医治疗费'")
    private Long tcmTrtFee;

    @Column(columnDefinition = "bigint default null comment '西药费'")
    private Long wmfee;

    @Column(columnDefinition = "bigint default null comment '非抗菌药物费用'")
    private Long abtlMednFee;

    @Column(columnDefinition = "bigint default null comment '中成药费'")
    private Long tcmpatFee;

    @Column(columnDefinition = "bigint default null comment '中药饮片费'")
    private Long tcmherbFee;

    @Column(columnDefinition = "bigint default null comment '血费'")
    private Long bloFee;

    @Column(columnDefinition = "bigint default null comment '白蛋白类制品费'")
    private Long albuFee;

    @Column(columnDefinition = "bigint default null comment '球蛋白类制品费'")
    private Long glonFee;

    @Column(columnDefinition = "bigint default null comment '凝血因子类制品费'")
    private Long clotfacFee;

    @Column(columnDefinition = "bigint default null comment '细胞因子类制品费'")
    private Long cykiFee;

    @Column(columnDefinition = "bigint default null comment '检查用一次性医用材料费'")
    private Long examDspoMatlFee;

    @Column(columnDefinition = "bigint default null comment '治疗用一次性医用材料费'")
    private Long trtDspoMatlFee;

    @Column(columnDefinition = "bigint default null comment '手术用一次性医用材料费'")
    private Long oprnDspoMatlFee;

    @Column(columnDefinition = "bigint default null comment '其他费'")
    private Long othFee;
}
