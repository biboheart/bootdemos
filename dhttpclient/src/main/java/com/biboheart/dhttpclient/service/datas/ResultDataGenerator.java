package com.biboheart.dhttpclient.service.datas;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;
import com.biboheart.dhttpclient.service.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResultDataGenerator {
    public static ResultData patientInfo(String body) throws BhException {
        if (CheckUtils.isEmpty(body)) {
            return null;
        }
        List<ResultDataItem> itemList = new ArrayList<>();
        ResultData data = new ResultData(itemList);
        body = body.replace("$$", "");
        String[] bodyArr = body.split("~");
        if (!"0".equals(bodyArr[0])) {
            throw new BhException(bodyArr[1]);
        }
        itemList.add(new ResultDataItem("5", "cardNumber", "社会保障卡卡号", bodyArr[4], null));
        itemList.add(new ResultDataItem("6", "informationDomain", "社会保障卡识别码", bodyArr[5], null));
        itemList.add(new ResultDataItem("8", "patientName", "姓名", bodyArr[7], null));
        itemList.add(new ResultDataItem("9", "gender", "性别", TypeConverter.convertToInteger(bodyArr[8]), null));
        itemList.add(new ResultDataItem("10", "nation", "民族", NationEnum.getNation(bodyArr[9]).getName(), null));
        itemList.add(new ResultDataItem("11", "birthday", "出生日期", TimeUtils.getDateMillis(bodyArr[10], "yyyy-MM-dd"), null));
        itemList.add(new ResultDataItem("12", "identityNumber", "证件号", bodyArr[11], null));
        itemList.add(new ResultDataItem("13", "insuranceAddressSn", "参保地行政区划代码", bodyArr[12], null));
        itemList.add(new ResultDataItem("16", "organizationName", "参保地单位名称", bodyArr[15], null));
        itemList.add(new ResultDataItem("17", "organizationName", "参保医疗险种", InsuranceTypeEnum.getInsuranceType(bodyArr[16]).getName(), null));
        itemList.add(new ResultDataItem("18", "personTypeName", "参保人员类别", PatientTypeEnum.getPatientType(bodyArr[17]).getName(), null));
        AdditionalPatientTypeEnum addTypeEnum = CheckUtils.isEmpty(bodyArr[18]) ? null : AdditionalPatientTypeEnum.getPatientType(bodyArr[18]);
        itemList.add(new ResultDataItem("19", "additionalPersonType", "附加人员类别", null != addTypeEnum ? addTypeEnum.getName() : null, null));
        itemList.add(new ResultDataItem("20", "additionalPersonType", "当年账户余额", TypeConverter.convertToDouble(bodyArr[19]), null));
        itemList.add(new ResultDataItem("21", "overYearsAccountBalance", "历年账户余额", TypeConverter.convertToDouble(bodyArr[20]), null));
        itemList.add(new ResultDataItem("26", "thisYearsAccountBalance", "本年门诊医保费用累计", TypeConverter.convertToDouble(bodyArr[25]), null));
        itemList.add(new ResultDataItem("27", "thisYearsHospitalizationCumulative", "本年住院医保费用累计", TypeConverter.convertToDouble(bodyArr[26]), null));
        itemList.add(new ResultDataItem("28", "thisYearsDiseasesCumulative", "本年规定病种医保费用累计", TypeConverter.convertToDouble(bodyArr[27]), null));
        itemList.add(new ResultDataItem("32", "allowanceSubsistence", "低保对象标识", bodyArr.length >= 32 && "1".equals(bodyArr[31]) ? "低保对象" : "非低保对象", null));
        return data;
    }
}
