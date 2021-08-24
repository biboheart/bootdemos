package com.biboheart.ditems.jpa;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class EberMysql5Dialect extends MySQL5Dialect {
    public EberMysql5Dialect() {
        super();
        registerFunction(BitFunction.FUNC_AND, new SQLFunctionTemplate(StandardBasicTypes.LONG, "(?1 & ?2)"));
        registerFunction(BitFunction.FUNC_OR, new SQLFunctionTemplate(StandardBasicTypes.LONG, "(?1 | ?2)"));
        registerFunction(BitFunction.FUNC_XOR, new SQLFunctionTemplate(StandardBasicTypes.LONG, "(?1 ^ ?2)"));
    }
}
