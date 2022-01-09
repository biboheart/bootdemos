package com.biboheart.dhttpclient.controller;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dhttpclient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private final PatientService patientService;

    public InsuranceController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "patient", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> patient() throws BhException {
        return new BhResponseResult<>(0, "success", patientService.patientInfo());
    }
}
