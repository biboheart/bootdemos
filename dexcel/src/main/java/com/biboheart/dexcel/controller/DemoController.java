package com.biboheart.dexcel.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dexcel.service.ExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DemoController {
    private final ExcelService excelService;

    public DemoController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @RequestMapping(value = "/demo")
    public BhResponseResult<?> hello() throws IOException, InvalidFormatException {
        excelService.readExcelDrug();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/drug")
    public BhResponseResult<?> drug() throws IOException, InvalidFormatException {
        excelService.readExcelDrug();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/drugFull")
    public BhResponseResult<?> drugFull() throws IOException, InvalidFormatException {
        excelService.readExcelDrugFull();
        return new BhResponseResult<>(0, "success", true);
    }
}
