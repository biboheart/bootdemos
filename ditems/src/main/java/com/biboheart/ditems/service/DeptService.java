package com.biboheart.ditems.service;

import com.biboheart.ditems.entity.Dept;

import java.util.Collection;
import java.util.List;

public interface DeptService {
    Dept save(Dept dept);

    Dept load(String sn);

    List<Dept> list(Collection<String> inSnList, Long scope, Long classify);

    Long count();
}
