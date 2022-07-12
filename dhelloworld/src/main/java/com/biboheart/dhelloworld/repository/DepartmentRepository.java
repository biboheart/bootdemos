package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByNumberIn(Collection<String> numberList);
}
