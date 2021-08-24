package com.biboheart.ditems.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.ditems.entity.Dept;
import com.biboheart.ditems.entity.ItemsStore;
import com.biboheart.ditems.events.ItemsStoreEvent;
import com.biboheart.ditems.jpa.BitFunction;
import com.biboheart.ditems.jpa.JpaUtils;
import com.biboheart.ditems.jpa.Specifications;
import com.biboheart.ditems.jpa.UnifyPageableSort;
import com.biboheart.ditems.repository.DeptRepository;
import com.biboheart.ditems.repository.ItemsStoreRepository;
import com.biboheart.ditems.service.ItemsStoreService;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemsStoreServiceImpl implements ItemsStoreService {
    private final ItemsStoreRepository itemsStoreRepository;
    private final DeptRepository deptRepository;
    private final ApplicationContext applicationContext;

    public ItemsStoreServiceImpl(ItemsStoreRepository itemsStoreRepository, DeptRepository deptRepository, ApplicationContext applicationContext) {
        this.itemsStoreRepository = itemsStoreRepository;
        this.deptRepository = deptRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    public ItemsStore save(ItemsStore itemsStore) {
        ItemsStore source = load(itemsStore.getStoreSn(), itemsStore.getItemsSn());
        if (null != source) {
            itemsStore.setId(source.getId());
        }
        List<String> diff = null;
        List<String> exclude = Arrays.asList("targetDept", "targetScope", "targetSource");
        if (null != source) diff = BeanUtils.compareObjDiffParams(source, itemsStore, null, exclude);
        itemsStore = itemsStoreRepository.save(itemsStore);
        if (null == source || !CheckUtils.isEmpty(diff)) {
            applicationContext.publishEvent(new ItemsStoreEvent("items_store_save", itemsStore));
        }
        return itemsStore;
    }

    @Override
    public void updateTarget(String typeSn, Long targetScope, String targetDept) {
        itemsStoreRepository.updateByTypeSn(typeSn, targetScope, targetDept);
    }

    @Override
    public void updateTarget(String typeSn, String storeSn, Long targetScope, String targetDept) {
        itemsStoreRepository.updateByTypeSnAndStoreSn(typeSn, storeSn, targetScope, targetDept);
    }

    @Override
    public ItemsStore load(String storeSn, String itemsSn) {
        if (CheckUtils.isEmpty(itemsSn)) return null;
        return itemsStoreRepository.findByStoreSnAndItemsSn(storeSn, itemsSn);
    }

    @Override
    public List<ItemsStore> list(String itemsSn) {
        return itemsStoreRepository.findByItemsSn(itemsSn);
    }

    @Override
    public Page<ItemsStore> find(String storeSn) {
        return null;
    }

    @Override
    public Page<ItemsStore> findToBill(String billDept, String patientArea) {
        Dept billDeptObj = deptRepository.findBySn(billDept);
        Dept patientAreaObj = deptRepository.findBySn(patientArea);
        Integer billNumber = null == billDeptObj ? null : billDeptObj.getOrderNumber();
        Integer areaNumber = null == patientAreaObj ? null : patientAreaObj.getOrderNumber();
        long scope = 0;
        if (null != billDeptObj) scope |= billDeptObj.getClassify();
        Specification<ItemsStore> spec = orSpec(billNumber, areaNumber, scope);
        return itemsStoreRepository.findAll(spec, UnifyPageableSort.getRequestPageable(ItemsStore.class));
    }

    private Specification<ItemsStore> spec(String billDept) {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            if (CheckUtils.isEmpty(billDept)) {
                builder.equal(root.get("id"), 0);
            }
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.and(JpaUtils.listPredicateToArray(pres));
        };
    }

    private Specification<ItemsStore> orSpec(Integer billNumber, Integer areaNumber, long scope) {
        return (root, query, builder) -> {
            List<Predicate> pres = new ArrayList<>();
            if (null != billNumber) {
                pres.add(Specifications.substringOrderNumberPredicate(root, query, builder, "targetDept", billNumber + 1));
            }
            if (null != areaNumber) {
                pres.add(Specifications.substringOrderNumberPredicate(root, query, builder, "targetDept", areaNumber + 1));
            }
            if (scope > 0) {
                pres.add(builder.ge(new BitFunction<>((CriteriaBuilderImpl) builder, Long.class, BitFunction.FUNC_AND, root.get("targetScope"), scope), 1));
            }
            if (CheckUtils.isEmpty(pres)) {
                return null;
            }
            return builder.or(JpaUtils.listPredicateToArray(pres));
        };
    }
}
