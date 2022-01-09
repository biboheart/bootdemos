package com.biboheart.dhttpclient.dock.mode;

public enum DiffCodeEnum {
    DIFF_CODE_9201(9201, 1, "获取人员信息"),
    DIFF_CODE_9202(9202, 2, "门诊预结算"),
    DIFF_CODE_9203(9203, 1, "门诊结算"),
    DIFF_CODE_9204(9204, 1, "门诊退费"),
    DIFF_CODE_9205(9205, 1, "住院登记"),
    DIFF_CODE_9207(9207, 2, "住院预结算"),
    DIFF_CODE_9208(9208, 1, "住院结算"),
    DIFF_CODE_9209(9209, 1, "住院退费"),
    DIFF_CODE_9210(9210, 2, "住院信息变动"),
    DIFF_CODE_9211(9211, 2, "取消住院登记"),
    DIFF_CODE_9212(9212, 2, "住院明细上传"),
    DIFF_CODE_9213(9213, 2, "住院明细退费"),
    DIFF_CODE_9214(9214, 2, "定点机构日结算对账"),
    DIFF_CODE_9215(9215, 2, "住院明细查询"),
    DIFF_CODE_9224(9224, 2, "日结算交易明细下载"),
    DIFF_CODE_9227(9227, 2, "住院明细删除"),
    DIFF_CODE_9228(9228, 2, "结算单据下载"),
    DIFF_CODE_9247(9247, 2, "交易作废"),
    DIFF_CODE_9252(9252, 2, "定点机构操作员注册"),
    DIFF_CODE_9253(9253, 2, "定点机构操作员签到"),
    DIFF_CODE_9254(9254, 2, "定点机构操作员日对账"),
    DIFF_CODE_9255(9255, 2, "操作员日结算交易明细下载"),
    DIFF_CODE_9256(9256, 2, "定点机构操作员注销"),
    DIFF_CODE_9261(9261, 2, "近三个月就诊记录查询"),
    DIFF_CODE_9262(9262, 2, "剩余用药天数查询"),
    DIFF_CODE_9263(9263, 2, "定点机构结算明细对账"),
    DIFF_CODE_3019(3019, 2, "外省住院信息上传"),
    DIFF_CODE_3020(3020, 2, "上传信息撤销"),
    DIFF_CODE_3101(3101, 2, "药品目录下载"),
    DIFF_CODE_3102(3102, 2, "诊疗目录下载"),
    DIFF_CODE_3103(3103, 2, "疾病目录下载"),
    DIFF_CODE_3104(3104, 2, "行政区划下载"),
    DIFF_CODE_3105(3105, 2, "跨省月结算数据下载"),
    DIFF_CODE_3106(3106, 2, "跨省门诊月结算数据下载"),
    DIFF_CODE_3107(3107, 2, "罕见病目录下载"),
    DIFF_CODE_3108(3108, 2, "罕见病用药目录下载"),
    ;

    private final Integer code;
    private final Integer type; // 1: 参保人业务相关, 2: 机构相关
    private final String desc;

    DiffCodeEnum(Integer code, Integer type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public Integer valueOf() {
        return this.code;
    }

    public Integer getCode() {
        return this.code;
    }

    public Integer getType() {
        return this.type;
    }

    public String getDesc() {
        return desc;
    }

    public static DiffCodeEnum getDiffCode(Integer code) {
        DiffCodeEnum[] diffCodeEnums = DiffCodeEnum.values();
        for (DiffCodeEnum diffCode : diffCodeEnums) {
            if (diffCode.getCode().equals(code)) {
                return diffCode;
            }
        }
        return null;
    }
}
