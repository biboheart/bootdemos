package com.biboheart.demos.service.impl;

import com.biboheart.demos.entity.Registeredtype;
import com.biboheart.demos.mapper.RegisteredTypeMapper;
import com.biboheart.demos.service.RegisteredTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class RegisteredTypeServiceImpl implements RegisteredTypeService {
    @Resource
    private RegisteredTypeMapper registeredTypeMapper;
    @Override
    public List<Registeredtype> registeredTypeList(Registeredtype registeredType) {
        return registeredTypeMapper.registeredTypeList(registeredType);
    }

    @Override
    public int deleteType(Integer registeredId) {
        return registeredTypeMapper.deleteType(registeredId);
    }

    @Override
    public int editRegisteredType(Registeredtype registeredType) {
        return registeredTypeMapper.editRegisteredType(registeredType);
    }

    @Override
    public int addRegisteredType(Registeredtype registeredType) {
        return registeredTypeMapper.addRegisteredType(registeredType);
    }

    @Override
    public int count(Registeredtype registeredType) {
        return registeredTypeMapper.count(registeredType);
    }
}
