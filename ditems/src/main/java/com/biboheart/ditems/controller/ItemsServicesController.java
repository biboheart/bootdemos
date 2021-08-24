package com.biboheart.ditems.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ditems.entity.ItemsServices;
import com.biboheart.ditems.service.ItemsServicesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsServicesController {
    private final ItemsServicesService itemsServicesService;

    public ItemsServicesController(ItemsServicesService itemsServicesService) {
        this.itemsServicesService = itemsServicesService;
    }

    @RequestMapping(value = "/items/services/save", method = {RequestMethod.POST})
    public BhResponseResult<?> save(ItemsServices itemsServices) {
        return new BhResponseResult<>(0, "success", itemsServicesService.save(itemsServices));
    }

    @RequestMapping(value = "/items/services/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(String itemsSn, Integer deptType, String deptSn) {
        return new BhResponseResult<>(0, "success", itemsServicesService.load(itemsSn, deptType, deptSn));
    }
}
