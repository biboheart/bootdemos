package com.biboheart.ditems.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.ditems.entity.Dept;
import com.biboheart.ditems.jpa.BitFunction;
import com.biboheart.ditems.jpa.JpaUtils;
import com.biboheart.ditems.repository.DeptRepository;
import com.biboheart.ditems.service.DeptService;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    @Override
    public Dept save(Dept dept) {
        Dept source = load(dept.getSn());
        if (null != source) {
            dept.setId(source.getId());
        }
        dept = deptRepository.save(dept);
        return dept;
    }

    @Override
    public Dept load(String sn) {
        Dept source = null;
        if (!CheckUtils.isEmpty(sn)) {
            source = deptRepository.findBySn(sn);
        }
        return source;
    }

    @Override
    public List<Dept> list(Collection<String> inSnList, Long scope, Long classify) {
        Specification<Dept> spec = spec(inSnList, scope, classify);
        return deptRepository.findAll(spec);
    }

    @Override
    public Long count() {
        return deptRepository.count();
    }

    private Specification<Dept> spec(Collection<String> inSnList, Long scope, Long classify) {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            if (!CheckUtils.isEmpty(inSnList)) {
                pres.add(root.get("sn").in(inSnList));
            }
            if (!CheckUtils.isEmpty(scope)) {
                pres.add(builder.ge(new BitFunction<>((CriteriaBuilderImpl) builder, Long.class, BitFunction.FUNC_AND, root.get("scope"), scope), 1));
            }
            if (!CheckUtils.isEmpty(classify)) {
                pres.add(builder.ge(new BitFunction<>((CriteriaBuilderImpl) builder, Long.class, BitFunction.FUNC_AND, root.get("classify"), classify), 1));
            }
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.and(JpaUtils.listPredicateToArray(pres));
        };
    }
}
