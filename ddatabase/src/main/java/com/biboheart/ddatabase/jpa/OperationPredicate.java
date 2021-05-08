package com.biboheart.ddatabase.jpa;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.ParameterRegistry;
import org.hibernate.query.criteria.internal.Renderable;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.BinaryOperatorExpression;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.hibernate.query.criteria.internal.predicate.AbstractSimplePredicate;

import javax.persistence.criteria.Expression;
import java.io.Serializable;

public class OperationPredicate
        extends AbstractSimplePredicate
        implements BinaryOperatorExpression<Boolean>, Serializable {
    private final OperationOperator operationOperator;
    private final Expression<?> leftHandSide;
    private final Expression<?> rightHandSide;

    public OperationPredicate(CriteriaBuilderImpl criteriaBuilder,
                              OperationOperator operationOperator,
                              Expression<?> leftHandSide,
                              Expression<?> rightHandSide) {
        super(criteriaBuilder);
        this.operationOperator = operationOperator;
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public OperationPredicate(
            CriteriaBuilderImpl criteriaBuilder,
            OperationOperator operationOperator,
            Expression<?> leftHandSide,
            Number rightHandSide) {
        super(criteriaBuilder);
        this.operationOperator = operationOperator;
        this.leftHandSide = leftHandSide;
        this.rightHandSide = new LiteralExpression<>(criteriaBuilder, rightHandSide);
    }

    @Override
    public void registerParameters(ParameterRegistry registry) {
        Helper.possibleParameter( getLeftHandOperand(), registry );
        Helper.possibleParameter( getRightHandOperand(), registry );
    }

    @Override
    public Expression<?> getRightHandOperand() {
        return rightHandSide;
    }

    @Override
    public Expression<?> getLeftHandOperand() {
        return leftHandSide;
    }

    public OperationOperator getOperationOperator() {
        return operationOperator;
    }

    @Override
    public String render(boolean isNegated, RenderingContext renderingContext) {
        return ( (Renderable) getLeftHandOperand() ).render( renderingContext )
                + getOperationOperator().rendered()
                + ( (Renderable) getRightHandOperand() ).render( renderingContext );
    }

    public static enum OperationOperator {
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
