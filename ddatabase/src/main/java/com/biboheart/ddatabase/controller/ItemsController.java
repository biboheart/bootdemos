package com.biboheart.ddatabase.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ddatabase.entity.Items;
import com.biboheart.ddatabase.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemsController {
    private final ItemsService itemsService;

    @Autowired
    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @RequestMapping(value = "/items/save", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> save(Items items) {
        return new BhResponseResult<>(0, "success", itemsService.save(items));
    }

    @RequestMapping(value = "/items/init", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> init() {
        itemsService.init();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/items/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(Long id) {
        Items items = itemsService.load(id);
        return new BhResponseResult<>(0, "success", items);
    }

    @RequestMapping(value = "/items/flag", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> flag() {
        itemsService.addFlag();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/items/list", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> list(Integer orderNumber) {
        List<Items> itemsList = itemsService.list(orderNumber);
        return new BhResponseResult<>(0, "success", itemsList);
    }
}
