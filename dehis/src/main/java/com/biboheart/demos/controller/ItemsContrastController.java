package com.biboheart.demos.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.demos.collect.ItemsDataCollector;
import com.biboheart.demos.service.ItemsContrastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/items/contrast")
public class ItemsContrastController {
    private final ItemsContrastService itemsContrastService;
    private final ItemsDataCollector itemsDataCollector;

    @RequestMapping(value = "compare", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> compare() {
        return new BhResponseResult<>(0, "success", itemsContrastService.compare());
    }

    @RequestMapping(value = "import", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> importItems(String filePath, Integer headRowNumber) {
        return new BhResponseResult<>(0, "success", itemsDataCollector.collectItemsFromCsv(filePath, headRowNumber));
    }
}
