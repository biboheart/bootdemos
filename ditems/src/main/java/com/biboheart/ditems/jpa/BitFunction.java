package com.biboheart.ditems.jpa;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.hibernate.query.criteria.internal.expression.function.ParameterizedFunctionExpression;

import javax.persistence.criteria.Expression;
import java.io.Serializable;

public class BitFunction<N extends Number>
        extends ParameterizedFunctionExpression<N>
        implements Serializable {
    public static final String FUNC_AND = "bitand"; //与
    public static final String FUNC_OR = "bitor";  //或
    public static final String FUNC_XOR = "bitxor"; //非

    public BitFunction(CriteriaBuilderImpl criteriaBuilder, Class<N> javaType, String functionName, Expression<Number> leftHandSide, Expression<Number> rightHandSide) {
        super(criteriaBuilder, javaType, functionName, leftHandSide, rightHandSide);
    }

    public BitFunction(CriteriaBuilderImpl criteriaBuilder, Class<N> javaType, String functionName, Expression<Number> leftHandSide, Number rightHandSide) {
        this(criteriaBuilder, javaType, functionName, leftHandSide, new LiteralExpression<>(criteriaBuilder, rightHandSide));
    }
}
