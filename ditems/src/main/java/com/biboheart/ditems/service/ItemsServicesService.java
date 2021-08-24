package com.biboheart.ditems.service;

import com.biboheart.ditems.entity.ItemsServices;

import java.util.List;

public interface ItemsServicesService {
    ItemsServices save(ItemsServices itemsServices);

    ItemsServices load(String itemsSn, Integer deptType, String deptSn);

    List<ItemsServices> list(String itemsSn, String deptSn, Long scope);
}
