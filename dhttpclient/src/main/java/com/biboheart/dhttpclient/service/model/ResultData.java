package com.biboheart.dhttpclient.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {
    private List<ResultDataItem> itemList;
}
