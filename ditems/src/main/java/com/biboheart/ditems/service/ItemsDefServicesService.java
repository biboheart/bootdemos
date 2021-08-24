package com.biboheart.ditems.service;

import com.biboheart.ditems.entity.ItemsDefServices;

import java.util.List;

public interface ItemsDefServicesService {
    ItemsDefServices save(ItemsDefServices itemsDefServices);

    ItemsDefServices load(String typeSn, Integer deptType, String deptSn);

    List<ItemsDefServices> list(String typeSn, String deptSn, Long scope);
}
