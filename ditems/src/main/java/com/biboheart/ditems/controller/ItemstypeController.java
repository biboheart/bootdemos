package com.biboheart.ditems.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ditems.enums.ItemsTypeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemstypeController {
    @RequestMapping(value = "/items/type/list", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> list() {
        return new BhResponseResult<>(0, "success", ItemsTypeEnum.toList());
    }
}
