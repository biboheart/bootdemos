package com.biboheart.dcrypto.utils;

import org.junit.jupiter.api.Test;

class CryptoUtilsTest {

    @Test
    void encrypt() {
        String content = "1641627442019\n{\"insuplc_admdvs\":\"\",\"dev_no\":\"1\",\"inf_time\":\"2022-01-08 19:01:47\",\"msgid\":\"H33070300089202201081901470019\",\"infver\":\"V1.0\",\"signtype\":\"\",\"opter\":\"test\",\"input\":{\"data\":{\"ver\":\"0\"}},\"fixmedins_code\":\"H33070300089\",\"cainfo\":\"\",\"opter_name\":\"测试员\",\"dev_safe_info\":\"\",\"infno\":\"1301\",\"mdtrtarea_admvs\":\"330799\",\"opter_type\":1,\"fixmedins_name\":\"金华田氏医院\",\"sign_no\":\"767026\",\"recer_sys_code\":\"FSI_LOCAL\"}";
        String secret = "KAuxfpVyXO46dYq";
        String key = "P12011600622";
        String digest256 = CryptoUtils.encryptSHA256(content, secret);
        String digestsm3 = CryptoUtils.encryptSm3(content, secret);
        System.out.println("digest256:" + digest256);
        System.out.println("digestsm3:" + digestsm3);
    }
}