package com.biboheart.ddatabase.service;

import com.biboheart.ddatabase.entity.Items;

import java.util.List;

public interface ItemsService {
    Items save(Items items);

    Items load(long id);

    void init();

    void addFlag();

    List<Items> list(Integer orderNumber);
}
