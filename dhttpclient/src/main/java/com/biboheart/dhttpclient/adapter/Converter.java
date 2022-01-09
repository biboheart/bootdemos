package com.biboheart.dhttpclient.adapter;

public interface Converter {
    /**
     * 转换数值
     * @param input 输入
     * @return 转换后的值
     */
    <T> T convert(Object input, String auxiliary, Class<T> valueType);
}
