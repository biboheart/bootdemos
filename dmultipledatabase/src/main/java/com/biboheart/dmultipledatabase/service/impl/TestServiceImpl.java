package com.biboheart.dmultipledatabase.service.impl;

import com.biboheart.dmultipledatabase.datasource.TargetDataSource;
import com.biboheart.dmultipledatabase.entity.Test;
import com.biboheart.dmultipledatabase.repository.TestRepository;
import com.biboheart.dmultipledatabase.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    @TargetDataSource("his")
    public Test load(Integer id) {
        return testRepository.findById(id).orElse(null);
    }
}
