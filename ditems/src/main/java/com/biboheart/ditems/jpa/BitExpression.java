package com.biboheart.ditems.jpa;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.ParameterRegistry;
import org.hibernate.query.criteria.internal.Renderable;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.ExpressionImpl;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;

import javax.persistence.criteria.Expression;
import java.io.Serializable;

public class BitExpression extends ExpressionImpl<Number> implements Serializable {
    private final BitOperator bitOperator;
    private final Expression<Number> leftHandSide;
    private final Expression<Number> rightHandSide;

    public BitExpression(
            CriteriaBuilderImpl criteriaBuilder,
            BitOperator bitOperator,
            Expression<Number> leftHandSide,
            Expression<Number> rightHandSide) {
        super( criteriaBuilder, Number.class );
        this.bitOperator = bitOperator;
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public BitExpression(
            CriteriaBuilderImpl criteriaBuilder,
            BitOperator bitOperator,
            Expression<Number> leftHandSide,
            Number rightHandSide) {
        this(criteriaBuilder, bitOperator, leftHandSide, wrap(criteriaBuilder, rightHandSide));
    }

    private static Expression<Number> wrap(CriteriaBuilderImpl criteriaBuilder, Number number) {
        return new LiteralExpression<>(criteriaBuilder, number);
    }

    public BitOperator getBitOperator() {
        return bitOperator;
    }

    public Expression<Number> getRightHandOperand() {
        return rightHandSide;
    }

    public Expression<Number> getLeftHandOperand() {
        return leftHandSide;
    }

    @Override
    public String render(RenderingContext renderingContext) {
        return ((Renderable) getLeftHandOperand() ).render( renderingContext )
                + getBitOperator().rendered()
                + ((Renderable)getRightHandOperand()).render(renderingContext);
    }

    @Override
    public void registerParameters(ParameterRegistry registry) {
        Helper.possibleParameter( getLeftHandOperand(), registry );
        Helper.possibleParameter( getRightHandOperand(), registry );
    }

    public static enum BitOperator {
        AND {
            public String rendered() {
                return "&";
            }
        },
        OR {
            public String rendered() {
                return "|";
            }
        },
        XOR {
            public String rendered() {
                return "^";
            }
        };

        public abstract String rendered();
    }
}
