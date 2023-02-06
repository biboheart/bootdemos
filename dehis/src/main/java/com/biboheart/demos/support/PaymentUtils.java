package com.biboheart.demos.support;

import com.biboheart.brick.utils.CheckUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentUtils {
    private static final BigDecimal TEN_THOUSAND = BigDecimal.valueOf(10000);

    public static Double roundMultiply(Long payment) {
        if (CheckUtils.isEmpty(payment)) {
            payment = 0L;
        }
        return BigDecimal.valueOf(payment).divide(TEN_THOUSAND, 2, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double strToDouble(String payment) {
        if (CheckUtils.isEmpty(payment)) {
            return 0.00;
        }
        return new BigDecimal(payment).doubleValue();
    }
}
