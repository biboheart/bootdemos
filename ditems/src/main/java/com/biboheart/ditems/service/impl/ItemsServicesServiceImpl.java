package com.biboheart.ditems.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.ditems.entity.ItemsServices;
import com.biboheart.ditems.events.ItemsServicesEvent;
import com.biboheart.ditems.jpa.BitFunction;
import com.biboheart.ditems.jpa.JpaUtils;
import com.biboheart.ditems.repository.ItemsServicesRepository;
import com.biboheart.ditems.service.ItemsServicesService;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsServicesServiceImpl implements ItemsServicesService {
    private final ItemsServicesRepository itemsServicesRepository;
    private final ApplicationContext applicationContext;

    public ItemsServicesServiceImpl(ItemsServicesRepository itemsServicesRepository, ApplicationContext applicationContext) {
        this.itemsServicesRepository = itemsServicesRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    public ItemsServices save(ItemsServices itemsServices) {
        ItemsServices source = load(itemsServices.getItemsSn(), itemsServices.getDeptType(), itemsServices.getDeptSn());
        if (null != source) {
            itemsServices.setId(source.getId());
        }
        List<String> diff = null;
        if (null != source) diff = BeanUtils.compareObjDiffParams(source, itemsServices, null, null);
        itemsServices = itemsServicesRepository.save(itemsServices);
        if (null == source || !CheckUtils.isEmpty(diff)) {
            applicationContext.publishEvent(new ItemsServicesEvent("items_services_save", itemsServices));
        }
        return itemsServices;
    }

    @Override
    public ItemsServices load(String itemsSn, Integer deptType, String deptSn) {
        return itemsServicesRepository.findByItemsSnAndDeptTypeAndDeptSn(itemsSn, deptType, deptSn);
    }

    @Override
    public List<ItemsServices> list(String itemsSn, String deptSn, Long scope) {
        Specification<ItemsServices> spec = spec(itemsSn).and(orSpec(deptSn, scope));
        return itemsServicesRepository.findAll(spec);
    }

    private Specification<ItemsServices> spec(String itemsSn) {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            if (!CheckUtils.isEmpty(itemsSn)) {
                pres.add(builder.equal(root.get("itemsSn"), itemsSn));
            }
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.and(JpaUtils.listPredicateToArray(pres));
        };
    }

    private Specification<ItemsServices> orSpec(String deptSn, Long scope) {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            if (!CheckUtils.isEmpty(deptSn)) {
                pres.add(builder.equal(root.get("deptSn"), deptSn));
                pres.add(builder.like(root.get("targetSn"), "%(" + deptSn + ")%"));
            }
            if (!CheckUtils.isEmpty(scope)) {
                pres.add(builder.ge(new BitFunction<>((CriteriaBuilderImpl) builder, Long.class, BitFunction.FUNC_AND, root.get("targetScope"), scope), 1));
            }
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.or(JpaUtils.listPredicateToArray(pres));
        };
    }
}
