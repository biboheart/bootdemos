package com.biboheart.ditems.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ditems.entity.ItemsStore;
import com.biboheart.ditems.service.ItemsStoreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsStoreController {
    private final ItemsStoreService itemsStoreService;

    public ItemsStoreController(ItemsStoreService itemsStoreService) {
        this.itemsStoreService = itemsStoreService;
    }

    @RequestMapping(value = "/items/store/save", method = {RequestMethod.POST})
    public BhResponseResult<?> save(ItemsStore itemsStore) {
        return new BhResponseResult<>(0, "success", itemsStoreService.save(itemsStore));
    }

    @RequestMapping(value = "/items/store/bill/find", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(String billDept, String patientArea) {
        return new BhResponseResult<>(0, "success", itemsStoreService.findToBill(billDept, patientArea));
    }
}
