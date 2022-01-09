package com.biboheart.dhttpclient.domain.datas;

import com.biboheart.dhttpclient.adapter.ConverterLocator;
import com.biboheart.dhttpclient.adapter.structure.ConverterType;
import com.biboheart.dhttpclient.domain.model.DiffInsuranceData;
import com.biboheart.dhttpclient.domain.model.DiffInsuranceDataItem;

import java.util.ArrayList;
import java.util.List;

public class DiffDatas9201 {
    public static DiffInsuranceData create(ConverterLocator converterLocator) {
        List<DiffInsuranceDataItem> itemList = new ArrayList<>();
        DiffInsuranceData data = new DiffInsuranceData(itemList);
        itemList.add(new DiffInsuranceDataItem("交易发起机构代码", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("交易发起机构名称", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("交易发起机构类型", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("交易发起机构统筹区", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("读卡方式", null, "1", converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("身份凭证信息", "6", null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("预留1", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("预留2", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("参保地行政区划代码", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("就医地行政区划代码", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("医疗类别", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("附加信息", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("终端机编号", null, "0", converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("卡规范版本", null, null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("证件号", "15", null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        itemList.add(new DiffInsuranceDataItem("姓名", "16", null, converterLocator.get(ConverterType.CONVERTER_STATIC), String.class, null));
        return data;
    }
}
