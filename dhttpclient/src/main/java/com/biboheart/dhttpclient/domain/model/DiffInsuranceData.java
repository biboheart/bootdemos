package com.biboheart.dhttpclient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiffInsuranceData {
    private List<DiffInsuranceDataItem> itemList;
}
