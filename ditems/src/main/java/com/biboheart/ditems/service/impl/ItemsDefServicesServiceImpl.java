package com.biboheart.ditems.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.ditems.entity.ItemsDefServices;
import com.biboheart.ditems.events.ItemsDefServicesEvent;
import com.biboheart.ditems.jpa.BitFunction;
import com.biboheart.ditems.jpa.JpaUtils;
import com.biboheart.ditems.repository.ItemsDefServicesRepository;
import com.biboheart.ditems.service.ItemsDefServicesService;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsDefServicesServiceImpl implements ItemsDefServicesService {
    private final ItemsDefServicesRepository itemsDefServicesRepository;
    private final ApplicationContext applicationContext;

    public ItemsDefServicesServiceImpl(ItemsDefServicesRepository itemsDefServicesRepository, ApplicationContext applicationContext) {
        this.itemsDefServicesRepository = itemsDefServicesRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    public ItemsDefServices save(ItemsDefServices itemsDefServices) {
        ItemsDefServices source = load(itemsDefServices.getTypeSn(), itemsDefServices.getDeptType(), itemsDefServices.getDeptSn());
        if (null != source) {
            itemsDefServices.setId(source.getId());
        }
        List<String> diff = null;
        if (null != source) diff = BeanUtils.compareObjDiffParams(source, itemsDefServices, null, null);
        itemsDefServices = itemsDefServicesRepository.save(itemsDefServices);
        if (null == source || !CheckUtils.isEmpty(diff)) {
            applicationContext.publishEvent(new ItemsDefServicesEvent("items_def_services_save", itemsDefServices));
        }
        return itemsDefServices;
    }

    @Override
    public ItemsDefServices load(String typeSn, Integer deptType, String deptSn) {
        return itemsDefServicesRepository.findByTypeSnAndDeptTypeAndDeptSn(typeSn, deptType, deptSn);
    }

    @Override
    public List<ItemsDefServices> list(String typeSn, String deptSn, Long scope) {
        Specification<ItemsDefServices> spec = spec(typeSn).and(orSpec(deptSn, scope));
        return itemsDefServicesRepository.findAll(spec);
    }

    private Specification<ItemsDefServices> spec(String typeSn) {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            if (!CheckUtils.isEmpty(typeSn)) {
                pres.add(builder.equal(root.get("typeSn"), typeSn));
            }
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.and(JpaUtils.listPredicateToArray(pres));
        };
    }

    private Specification<ItemsDefServices> orSpec(String deptSn, Long scope) {
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
