package com.biboheart.dmultipledatabase.repository;

import com.biboheart.dmultipledatabase.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
