package com.biboheart.ditems.repository;

import com.biboheart.ditems.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeptRepository extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept> {
    Dept findBySn(String sn);
}
