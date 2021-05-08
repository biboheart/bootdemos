package com.biboheart.ddatabase.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.ddatabase.entity.Items;
import com.biboheart.ddatabase.jpa.BitExpression;
import com.biboheart.ddatabase.jpa.BitFunction;
import com.biboheart.ddatabase.jpa.JpaUtils;
import com.biboheart.ddatabase.jpa.OperationPredicate;
import com.biboheart.ddatabase.repository.ItemsRepository;
import com.biboheart.ddatabase.service.ItemsService;
import com.biboheart.ddatabase.utils.FlagUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.cosh;
import static java.lang.Math.random;

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
        List<Items> itemsList = new ArrayList<>();
        for (int i = 0; i < 800; i++) {
            itemsList.clear();
            for (int j = 0; j < 10000; j++) {
                Items items = new Items(null, "items" + (i * j), "", "0", 1|2|4);
                itemsList.add(items);
            }
            itemsRepository.saveAll(itemsList);
        }
    }

    @Override
    public void addFlag() {
        List<Long> inIdList = new ArrayList<>();
        for (int i = 0; i < 1000; i ++) {
            long tid = (long) (Math.random() * 100);
            int mu = (int) (Math.random() * 100);
            if (tid == 0 || mu == 0) {
                continue;
            }
            inIdList.add(tid * mu);
        }
        List<Items> itemsList = itemsRepository.findAllById(inIdList);
        if (CheckUtils.isEmpty(itemsList)) {
            log.info("没有取到items");
            return;
        }
        for (Items items : itemsList) {
            /*int orderNumber = (int) (Math.random() * 100);
            if (orderNumber == 0) {
                continue;
            }*/
            String flag = FlagUtils.addFlag(items.getFlag(), 5);
            items.setFlag(flag);
            StringBuilder binstr = new StringBuilder();
            for (int i = 0; i < flag.length(); i++) {
                binstr.append(Integer.toBinaryString(flag.charAt(i))).append(" ");
            }
            items.setRemark(binstr.toString());
        }
        itemsRepository.saveAll(itemsList);
        log.info("end update, items size: {}", itemsList.size());
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
