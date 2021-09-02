package com.biboheart.ddatabase.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.ddatabase.entity.Items;
import com.biboheart.ddatabase.jpa.BitFunction;
import com.biboheart.ddatabase.jpa.JpaUtils;
import com.biboheart.ddatabase.repository.ItemsRepository;
import com.biboheart.ddatabase.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public Items save(Items items) {
        return itemsRepository.save(items);
    }

    @Override
    public Items load(long id) {
        return itemsRepository.findById(id).orElse(null);
    }

    @Override
    public void init() {
    }

    @Override
    public void addFlag() {
    }

    @Override
    public List<Items> list(Integer orderNumber) {
        return itemsRepository.findAll(spec());
    }

    private Specification<Items> spec() {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            /*Expression<Number> bitExpression = new BitExpression((CriteriaBuilderImpl) builder, BitExpression.BitOperator.AND, root.get("type"), 5);
            pres.add(builder.ge(bitExpression, 1));*/
            /*pres.add(new OperationPredicate((CriteriaBuilderImpl) builder, OperationPredicate.OperationOperator.AND, root.get("type"), 5));*/
            //pres.add(builder.equal(builder.function("bitand", Long.class, root.get("type"), new LiteralExpression<Number>((CriteriaBuilderImpl) builder, 5)), 5));
            pres.add(builder.ge(new BitFunction<>((CriteriaBuilderImpl) builder, Long.class, BitFunction.FUNC_AND, root.get("type"), 5), 1));
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.and(JpaUtils.listPredicateToArray(pres));
        };
    }
}
