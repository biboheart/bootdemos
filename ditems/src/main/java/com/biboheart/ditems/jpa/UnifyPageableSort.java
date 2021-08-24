package com.biboheart.ditems.jpa;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UnifyPageableSort {
    public static Sort getRequestSort(@NotNull Class<?> clazz) {
        SystemRequest sr = SystemRequestHolder.getSystemRequest();
        if(null == sr) {
            return null;
        }
        String order = sr.getOrder();
        String sort = sr.getSort();
        if(null == sort || "".equals(sort)) {
            return null;
        }
        if(null != order && !"".equals(order)) {
            order = order.toLowerCase();
        }
        sort = sort.replaceAll("\\s*", "");
        String[] atts = sort.split(",");
        if(0 >= atts.length){
            return null;
        }
        if(null == order || "".equals(order)) {
            order = "desc";
        }
        String[] orderTypes = order.split(",");
        List<Sort.Order> orders = new ArrayList<>();
        for(int i = 0; i < atts.length; i ++) {
            String property;
            try {
                clazz.getDeclaredField(atts[i]);
                property = atts[i];
            } catch (NoSuchFieldException | SecurityException e) {
                property = null;
            }
            if(property == null) {
                continue;
            }
            Sort.Direction direction;
            if(i < orderTypes.length) {
                direction = "desc".equals(orderTypes[i]) ? Sort.Direction.DESC : Sort.Direction.ASC;
            } else {
                direction = "desc".equals(orderTypes[orderTypes.length - 1]) ? Sort.Direction.DESC : Sort.Direction.ASC;
            }
            orders.add(new Sort.Order(direction, property));
        }
        if(0 >= orders.size()) {
            return null;
        }
        return Sort.by(orders);
    }

    public static Pageable getRequestPageable(@NotNull Class<?> clazz) {
        Sort sort = getRequestSort(clazz);
        if(null == sort) {
            return PageRequest.of(getRequestPageOffset(), getRequestPageSize());
        } else {
            return PageRequest.of(getRequestPageOffset(), getRequestPageSize(), sort);
        }
    }

    private static SystemRequest getSystemRequest() {
        SystemRequest sr = SystemRequestHolder.getSystemRequest();
        if (sr == null){
            sr = new SystemRequest();
        }
        return sr;
    }

    private static int getRequestPageOffset() {
        int pageOffset = getSystemRequest().getPageOffset();
        if (pageOffset < 1) {
            pageOffset = 1;
        }
        return pageOffset - 1;
    }

    private static int getRequestPageSize() {
        int pageSize = getSystemRequest().getPageSize();
        if (pageSize < 0) {
            pageSize = SystemRequest.getDefaultPageSize();
        }
        return pageSize;
    }
}
