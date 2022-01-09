package com.biboheart.dhttpclient.domain.model;

import com.biboheart.dhttpclient.adapter.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiffInsuranceDataItem {
    private String desc;
    private String sourceName;
    private String auxiliary;
    private Converter converter;
    private Class cls;
    private DiffInsuranceData subData;
}
