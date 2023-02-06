package com.biboheart.demos.controller;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.demos.collect.HomeDataCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/record")
public class InsuranceRecordController {
    private final HomeDataCollector homeDataCollector;

    @RequestMapping(value = "home/import", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> importRecordHome(String filePath, Integer headRowNumber) throws BhException {
        return new BhResponseResult<>(0, "success", homeDataCollector.collectRecordFromExcel(filePath, headRowNumber));
    }

    @RequestMapping(value = "patient/import", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> importRecordPatient(String filePath, Integer headRowNumber) throws BhException {
        return new BhResponseResult<>(0, "success", homeDataCollector.collectPatientFromExcel(filePath, headRowNumber));
    }
}
