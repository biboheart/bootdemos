package com.biboheart.ditems.jpa;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Specifications {
    public static <S> Specification<S> substringOrderNumber(String name, Integer orderNumber) {
        return (root, query, cb) -> {
            if (null == orderNumber) {
                return null;
            }
            return cb.gt(cb.substring(root.get(name), orderNumber, 1).as(Integer.class), 0);
        };
    }

    public static Predicate substringOrderNumberPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder, String name, Integer orderNumber) {
        return builder.gt(builder.substring(root.get(name), orderNumber, 1).as(Integer.class), 0);
    }
}
