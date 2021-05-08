package com.biboheart.ddatabase.jpa;

import com.biboheart.brick.utils.CheckUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JpaUtils {
    public static Predicate[] listPredicateToArray(List<Predicate> list) {
        if (null == list || list.isEmpty()) {
            return null;
        }
        Predicate[] preArr = new Predicate[list.size()];
        return list.toArray(preArr);
    }

    public static Predicate[] matchPredicates(Root<?> root, CriteriaBuilder builder, String match, Collection<String> names) {
        if (CheckUtils.isEmpty(match) || CheckUtils.isEmpty(names)) {
            return null;
        }
        List<Predicate> pres = new ArrayList<>();
        String pattern = "%" + match + "%";
        names.forEach(name -> {
            if (!CheckUtils.isEmpty(name)) {
                pres.add(builder.like(root.get(name), pattern));
            }
        });
        return JpaUtils.listPredicateToArray(pres);
    }

    public static Predicate[] timeSectionPredicates(Root<?> root, CriteriaBuilder builder, String name, Long start, Long end) {
        List<Predicate> pres = new ArrayList<>();
        if (CheckUtils.isEmpty(name)) {
            return null;
        }
        if (!CheckUtils.isEmpty(start)) {
            pres.add(builder.ge(root.get(name), start));
        }
        if (!CheckUtils.isEmpty(end)) {
            pres.add(builder.le(root.get(name), end));
        }
        if (pres.isEmpty()) {
            return null;
        }
        Predicate[] preArr = new Predicate[pres.size()];
        preArr = pres.toArray(preArr);
        return preArr;
    }
}
