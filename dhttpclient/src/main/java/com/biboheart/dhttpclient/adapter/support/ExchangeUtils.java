package com.biboheart.dhttpclient.adapter.support;

public final class ExchangeUtils {
    public static Double moveRadixPoint(String val, Integer moveLen, Integer fractionalLenth) {
        if (null == val || "".equals(val)) {
            return null;
        }
        double doubleVal = Double.parseDouble(val);
        if (moveLen == 0) {
            return doubleVal;
        }
        doubleVal = doubleVal * Math.pow(10, moveLen);
        if (moveLen > 0) {
            doubleVal = Math.round(doubleVal);
        }
        if (null != fractionalLenth && fractionalLenth > 0) {
            doubleVal = Math.round(doubleVal * (Math.pow(10, fractionalLenth))) / Math.pow(10, fractionalLenth);
        }
        return doubleVal;
    }
}
