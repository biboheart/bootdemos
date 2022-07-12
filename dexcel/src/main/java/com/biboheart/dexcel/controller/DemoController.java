package com.biboheart.dexcel.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dexcel.service.ExcelService;
import com.biboheart.dexcel.service.SerialDetailsService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DemoController {
    private final ExcelService excelService;
    private final SerialDetailsService serialDetailsService;

    public DemoController(ExcelService excelService, SerialDetailsService serialDetailsService) {
        this.excelService = excelService;
        this.serialDetailsService = serialDetailsService;
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

    @RequestMapping(value = "/diagnosis")
    public BhResponseResult<?> diagnosis() throws IOException, InvalidFormatException {
        excelService.readExcelDiagnosis();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/expiration")
    public BhResponseResult<?> readExpirationTime() throws IOException, InvalidFormatException {
        excelService.readExpirationTime();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/serial")
    public BhResponseResult<?> serial() throws IOException, InvalidFormatException {
        excelService.readSerial();
        return new BhResponseResult<>(0, "success", true);
    }

    @RequestMapping(value = "/serial/list")
    public BhResponseResult<?> listSerial(Long id) {
        return new BhResponseResult<>(0, "success", serialDetailsService.list(id));
    }
}
