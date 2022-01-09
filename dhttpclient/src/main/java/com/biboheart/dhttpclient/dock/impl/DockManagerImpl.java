package com.biboheart.dhttpclient.dock.impl;

import com.biboheart.dhttpclient.dock.DockManager;
import com.biboheart.dhttpclient.dock.InsuranceDock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DockManagerImpl implements DockManager {
    private final List<InsuranceDock> insuranceDockList;

    public DockManagerImpl() {
        this.insuranceDockList = new ArrayList<>();
        this.insuranceDockList.add(new InsuranceDockImpl());
    }

    @Override
    public InsuranceDock getInsuranceDock(Integer route) {
        InsuranceDock insuranceDock = null;
        if (null == route) return null;
        for (InsuranceDock dock : this.insuranceDockList) {
            if (route.equals(dock.getRoute())) insuranceDock = dock;
        }
        return insuranceDock;
    }
}
