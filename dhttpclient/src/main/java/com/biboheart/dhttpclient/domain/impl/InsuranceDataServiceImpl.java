package com.biboheart.dhttpclient.domain.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.dhttpclient.adapter.support.TypeConverter;
import com.biboheart.dhttpclient.dock.mode.DiffCodeEnum;
import com.biboheart.dhttpclient.domain.InsuranceDataService;
import com.biboheart.dhttpclient.domain.datas.DiffInsuranceDataLocator;
import com.biboheart.dhttpclient.domain.model.DiffInsuranceData;
import com.biboheart.dhttpclient.domain.model.DiffInsuranceDataItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class InsuranceDataServiceImpl implements InsuranceDataService {
    private final DiffInsuranceDataLocator locator;

    private final List<String> commonList = Arrays.asList("330799108331", "金华田氏医院", "00", "330799");

    public InsuranceDataServiceImpl(DiffInsuranceDataLocator locator) {
        this.locator = locator;
    }

    @Override
    public String packageData(String code, Map<String, Object> input) throws BhException {
        Integer nCode = Integer.valueOf(code);
        DiffCodeEnum diffCode = DiffCodeEnum.getDiffCode(nCode);
        if (null == diffCode) {
            throw new BhException("code值不正确");
        }
        DiffInsuranceData insuranceData = locator.getInsuranceData(diffCode);
        if (null == insuranceData) {
            throw new BhException("未定义数据模型");
        }
        List<DiffInsuranceDataItem> itemList = insuranceData.getItemList();
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i ++) {
            DiffInsuranceDataItem item = itemList.get(i);
            if (i < 4) {
                dataList.add(item.getConverter().convert(commonList.get(i), item.getAuxiliary(), String.class));
                continue;
            }
            if (null != item.getSubData()) {
                Object subObj = input.get(item.getSourceName());
                if (null != subObj) {
                    List<Map<String, Object>> inputList = TypeConverter.convert(subObj, List.class);
                    dataList.add(subData(inputList, item.getSubData()));
                } else {
                    dataList.add("");
                }
            } else {
                Object inputObj = input.get(item.getSourceName());
                Object value = item.getConverter().convert(inputObj, item.getAuxiliary(), item.getCls());
                dataList.add(null == value ? "" : String.valueOf(value));
            }
        }
        return "$$" + String.join("~", dataList) + "$$";
    }

    // 组装子结构数据
    private String subData(List<Map<String, Object>> inputList, DiffInsuranceData insuranceData) {
        List<DiffInsuranceDataItem> itemList = insuranceData.getItemList();
        if (null == inputList || inputList.isEmpty()) {
            return "";
        }
        List<String> dataList = new ArrayList<>();
        for (Map<String, Object> input : inputList) {
            dataList.add(subData(input, itemList));
        }
        return String.join("^", dataList);
    }

    private String subData(Map<String, Object> input, List<DiffInsuranceDataItem> itemList) {
        List<String> dataList = new ArrayList<>();
        for (DiffInsuranceDataItem item : itemList) {
            Object inputObj = input.get(item.getSourceName());
            Object value = item.getConverter().convert(inputObj, item.getAuxiliary(), item.getCls());
            dataList.add(null == value ? "" : String.valueOf(value));
        }
        return String.join("|", dataList);
    }
}
