package com.biboheart.dexcel.service;

import com.biboheart.dexcel.model.SerialDetailsModel;

import java.util.List;
import java.util.Map;

public interface SerialDetailsService {
    void save(List<SerialDetailsModel> list);

    Map<String, Object> list(Long serialId);
}
