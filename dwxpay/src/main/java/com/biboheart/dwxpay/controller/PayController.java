package com.biboheart.dwxpay.controller;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dwxpay.domain.PayDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/public/payapi/pay")
public class PayController {
    private final PayDomain payDomain;

    @RequestMapping(value = "order", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> order() throws BhException {
        return new BhResponseResult<>(0, "success", payDomain.order());
    }
}
