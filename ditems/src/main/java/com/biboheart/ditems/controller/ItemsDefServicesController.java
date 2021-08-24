package com.biboheart.ditems.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ditems.entity.ItemsDefServices;
import com.biboheart.ditems.service.ItemsDefServicesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsDefServicesController {
    private final ItemsDefServicesService itemsDefServicesService;

    public ItemsDefServicesController(ItemsDefServicesService itemsDefServicesService) {
        this.itemsDefServicesService = itemsDefServicesService;
    }

    @RequestMapping(value = "/items/def/services/save", method = {RequestMethod.POST})
    public BhResponseResult<?> save(ItemsDefServices itemsDefServices) {
        return new BhResponseResult<>(0, "success", itemsDefServicesService.save(itemsDefServices));
    }

    @RequestMapping(value = "/items/def/services/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(String typeSn, Integer deptType, String deptSn) {
        return new BhResponseResult<>(0, "success", itemsDefServicesService.load(typeSn, deptType, deptSn));
    }
}
