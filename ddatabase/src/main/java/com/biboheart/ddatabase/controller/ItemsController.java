package com.biboheart.ddatabase.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ddatabase.entity.Items;
import com.biboheart.ddatabase.service.ItemsService;
import com.biboheart.ddatabase.utils.FlagUtils;
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
        if (null == items.getName()) {
            items = itemsService.load(120);
            String flag = FlagUtils.addFlag(items.getFlag(), 5, 253);
            items.setFlag(flag);
            StringBuilder binstr = new StringBuilder();
            for (int i = 0; i < flag.length(); i++) {
                binstr.append(Integer.toBinaryString(flag.charAt(i))).append(" ");
            }
            items.setRemark(binstr.toString());
        }
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
