package com.biboheart.ditems.process;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.ListUtils;
import com.biboheart.ditems.entity.Dept;
import com.biboheart.ditems.entity.ItemsDefServices;
import com.biboheart.ditems.entity.ItemsServices;
import com.biboheart.ditems.entity.ItemsStore;
import com.biboheart.ditems.service.DeptService;
import com.biboheart.ditems.service.ItemsDefServicesService;
import com.biboheart.ditems.service.ItemsServicesService;
import com.biboheart.ditems.service.ItemsStoreService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ItemsStoreProcess {
    private final DeptService deptService;
    private final ItemsStoreService itemsStoreService;
    private final ItemsDefServicesService itemsDefServicesService;
    private final ItemsServicesService itemsServicesService;

    public ItemsStoreProcess(DeptService deptService, ItemsStoreService itemsStoreService, ItemsDefServicesService itemsDefServicesService, ItemsServicesService itemsServicesService) {
        this.deptService = deptService;
        this.itemsStoreService = itemsStoreService;
        this.itemsDefServicesService = itemsDefServicesService;
        this.itemsServicesService = itemsServicesService;
    }

    public void updateItemsStoreTargetFromItemsSn(String itemsSn) {
        if (CheckUtils.isEmpty(itemsSn)) return;
        List<ItemsStore> itemsStoreList = itemsStoreService.list(itemsSn);
        Integer source = !CheckUtils.isEmpty(itemsStoreList) ? 2: 1;
        for (ItemsStore itemsStore : itemsStoreList) {
            ItemsStore nis = new ItemsStore();
            BeanUtils.copy(itemsStore, nis, null, null);
            updateItemsStoreTarget(nis, source);
        }
    }

    public void updateItemsStoreTargetFromTypeSn(String typeSn) {
        List<ItemsDefServices> itemsDefServicesList = itemsDefServicesService.list(typeSn, null, null);
        if (CheckUtils.isEmpty(itemsDefServicesList)) {
            itemsStoreService.updateTarget(typeSn, 0L, null);
            return;
        }
        StringBuilder targetDept = new StringBuilder();
        for (int i = 0; i < deptService.count().intValue(); i++) {
            targetDept.append("0");
        }
        Long allTargetScope = 0L;
        Set<String> allTargetDeptSnList = new HashSet<>();
        for (ItemsDefServices ids : itemsDefServicesList) {
            allTargetScope |= ids.getTargetScope();
            if (Integer.valueOf(0).equals(ids.getDeptType()) && !CheckUtils.isEmpty(ids.getDeptSn())) {
                // 指定科室的情况
                Dept dept = deptService.load(ids.getDeptSn());
                long targetScope = 0;
                String targetDeptStr = null;
                if (!CheckUtils.isEmpty(ids.getTargetScope())) {
                    targetScope |= ids.getTargetScope();
                } else if (!CheckUtils.isEmpty(ids.getTargetSn())) {
                    List<String> list = ListUtils.string2ListString(ids.getTargetSn().substring(1, ids.getTargetSn().length() - 1), ")(");
                    allTargetDeptSnList.addAll(list);
                    List<Dept> deptList = deptService.list(list, null, null);
                    if (!CheckUtils.isEmpty(deptList)) {
                        StringBuilder targetDeptTemp = new StringBuilder(targetDept.toString());
                        for (Dept temp : deptList) {
                            targetDeptTemp.replace(temp.getOrderNumber(), temp.getOrderNumber() + 1, "1");
                        }
                        targetDeptStr = targetDeptTemp.toString();
                    }
                }
                if (null != dept) {
                    itemsStoreService.updateTarget(typeSn, dept.getSn(), targetScope, targetDeptStr);
                }
            } else if (Arrays.asList(1, 2).contains(ids.getDeptType())){
                // 就诊科室或就诊病区
                Long scope = Integer.valueOf(1).equals(ids.getDeptType()) ? 4L : 8L;
                Long classify = ids.getTargetScope();
                List<Dept> deptList = deptService.list(null, scope, classify);
                if (!CheckUtils.isEmpty(deptList)) {
                    for (Dept dept : deptList) {
                        StringBuilder sb = new StringBuilder(targetDept.toString());
                        sb.replace(dept.getOrderNumber(), dept.getOrderNumber() + 1, "1");
                        itemsStoreService.updateTarget(typeSn, dept.getSn(), 0L, sb.toString());
                    }
                }
            }
        }
        List<Dept> allDeptList = deptService.list(allTargetDeptSnList, null, null);
        String targetDeptStr = null;
        if (!CheckUtils.isEmpty(allDeptList)) {
            StringBuilder sb = new StringBuilder(targetDept.toString());
            for (Dept temp : allDeptList) {
                sb.replace(temp.getOrderNumber(), temp.getOrderNumber() + 1, "1");
            }
            targetDeptStr = sb.toString();
        }
        itemsStoreService.updateTarget(typeSn, allTargetScope, targetDeptStr);
    }

    public void updateItemsStoreTarget(ItemsStore itemsStore, Integer source) {
        String itemsType = itemsStore.getTypeSn();
        boolean isEquipment = Integer.parseInt(itemsType) < 30;
        if (isEquipment && CheckUtils.isEmpty(itemsStore.getStoreSn())) {
            // 药品和材料需要库房编号
            return;
        }
        // 项目服务关系
        List<ItemsServices> itemsServicesList = null;
        if (CheckUtils.isEmpty(source) || Integer.valueOf(2).equals(source)) {
            itemsServicesList = itemsServicesService.list(itemsStore.getItemsSn(), null, null);
        }
        List<ItemsDefServices> itemsDefServicesList = null;
        if (CheckUtils.isEmpty(source) || Integer.valueOf(1).equals(source)) {
            itemsDefServicesList = itemsDefServicesService.list(itemsStore.getTypeSn(), null, null);
        }
        Dept dept = deptService.load(itemsStore.getStoreSn());
        long targetScope = 0;
        Set<String> deptSnList = new HashSet<>();
        boolean matching = false;
        if (!CheckUtils.isEmpty(itemsServicesList)) {
            itemsStore.setTargetSource(2);
            if (null != dept) {
                // 如果项目有库房，则找出库房可服务的科室
                for (ItemsServices is : itemsServicesList) {
                    if (Integer.valueOf(0).equals(is.getDeptType()) && dept.getSn().equals(is.getDeptSn())) {
                        // 如果服务方式为指定科室且科室编号为库房编号，将服务对象设置到库房可开单科室
                        matching = true;
                        if (!CheckUtils.isEmpty(is.getTargetScope())) {
                            targetScope |= is.getTargetScope();
                        } else if (!CheckUtils.isEmpty(is.getTargetSn())) {
                            deptSnList.addAll(ListUtils.string2ListString(is.getTargetSn().substring(1, is.getTargetSn().length() - 1), ")("));
                        }
                    } else if (Arrays.asList(1, 2).contains(is.getDeptType())) {
                        // 服务方式为就诊科室或就诊病区的情况
                        Long scope = Integer.valueOf(1).equals(is.getDeptType()) ? 4L : 8L;
                        Long classify = is.getTargetScope();
                        if ((dept.getScope() & scope) > 0 && (dept.getClassify() & classify) > 0) matching = true;
                        targetScope = 0;
                        if (matching && !CheckUtils.isEmpty(is.getTargetScope()) && !CheckUtils.isEmpty(dept.getClassify())) {
                            deptSnList.add(dept.getSn());
                        } else if (matching && !CheckUtils.isEmpty(is.getTargetSn()) && is.getTargetSn().contains("(" + dept.getSn() + ")")) {
                            deptSnList.add(dept.getSn());
                        }
                    }
                }
            } else {
                // 如果项目没有库房，则取所有服务对象为可开单科室
                matching = true;
                for (ItemsServices is : itemsServicesList) {
                    if (!CheckUtils.isEmpty(is.getTargetScope())) {
                        targetScope |= is.getTargetScope();
                    } else if (!CheckUtils.isEmpty(is.getTargetSn())) {
                        deptSnList.addAll(ListUtils.string2ListString(is.getTargetSn().substring(1, is.getTargetSn().length() - 1), ")("));
                    }
                }
            }
        }
        if (!CheckUtils.isEmpty(itemsDefServicesList) && (CheckUtils.isEmpty(itemsServicesList) || !matching)) {
            // 如果有默认配置且没有相应的个性化配置
            matching = true;
            if (null != dept) {
                for (ItemsDefServices ids : itemsDefServicesList) {
                    if (Integer.valueOf(0).equals(ids.getDeptType()) && dept.getSn().equals(ids.getDeptSn())) {
                        // 如果服务方式为指定科室且科室编号为库房编号，将服务对象设置到库房可开单科室
                        if (!CheckUtils.isEmpty(ids.getTargetScope())) {
                            targetScope |= ids.getTargetScope();
                        } else if (!CheckUtils.isEmpty(ids.getTargetSn())) {
                            deptSnList.addAll(ListUtils.string2ListString(ids.getTargetSn().substring(1, ids.getTargetSn().length() - 1), ")("));
                        }
                    } else if (Arrays.asList(1, 2).contains(ids.getDeptType())) {
                        // 服务方式为就诊科室或就诊病区的情况
                        targetScope = 0;
                        if (!CheckUtils.isEmpty(ids.getTargetScope()) && !CheckUtils.isEmpty(dept.getClassify()) && ((dept.getClassify() & ids.getTargetScope()) > 0)) {
                            deptSnList.add(dept.getSn());
                        } else if (!CheckUtils.isEmpty(ids.getTargetSn()) && ids.getTargetSn().contains("(" + dept.getSn() + ")")) {
                            deptSnList.add(dept.getSn());
                        }
                    }
                }
            } else {
                // 如果项目没有库房，则取所有服务对象为可开单科室
                for (ItemsDefServices ids : itemsDefServicesList) {
                    if (!CheckUtils.isEmpty(ids.getTargetScope())) {
                        targetScope |= ids.getTargetScope();
                    } else if (!CheckUtils.isEmpty(ids.getTargetSn())) {
                        deptSnList.addAll(ListUtils.string2ListString(ids.getTargetSn().substring(1, ids.getTargetSn().length() - 1), ")("));
                    }
                }
            }
        }
        if (!matching){
            itemsStore.setTargetSource(1);
            targetScope = 0;
        }
        itemsStore.setTargetScope(targetScope);
        String targetDeptStr = null;
        if (matching && !CheckUtils.isEmpty(deptSnList)) {
            List<Dept> deptList = deptService.list(deptSnList, null, null);
            if (!CheckUtils.isEmpty(deptList)) {
                int[] arr = new int[deptService.count().intValue()];
                for (Dept temp : deptList) {
                    arr[temp.getOrderNumber()] = 1;
                }
                StringBuilder sb = new StringBuilder();
                for (int i : arr) {
                    sb.append(i);
                }
                targetDeptStr = sb.toString();
            }
        }
        itemsStore.setTargetDept(targetDeptStr);
        itemsStoreService.save(itemsStore);
    }
}
