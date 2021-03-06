package com.biboheart.ddatabase.jpa;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.hibernate.query.criteria.internal.expression.function.ParameterizedFunctionExpression;

import javax.persistence.criteria.Expression;
import java.io.Serializable;

public class BitFunction<N extends Number>
        extends ParameterizedFunctionExpression<N>
        implements Serializable {
    public static final String FUNC_AND = "bitand"; //δΈ
    public static final String FUNC_OR = "bitor";  //ζ
    public static final String FUNC_XOR = "bitxor"; //ι

    public BitFunction(CriteriaBuilderImpl criteriaBuilder, Class<N> javaType, String functionName, Expression<Number> leftHandSide, Expression<Number> rightHandSide) {
        super(criteriaBuilder, javaType, functionName, leftHandSide, rightHandSide);
    }

    public BitFunction(CriteriaBuilderImpl criteriaBuilder, Class<N> javaType, String functionName, Expression<Number> leftHandSide, Number rightHandSide) {
        this(criteriaBuilder, javaType, functionName, leftHandSide, new LiteralExpression<>(criteriaBuilder, rightHandSide));
    }
}
