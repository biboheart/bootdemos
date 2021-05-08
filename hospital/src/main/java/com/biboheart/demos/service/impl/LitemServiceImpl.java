package com.biboheart.demos.service.impl;

import com.biboheart.demos.entity.Litem;
import com.biboheart.demos.entity.Lrecord;
import com.biboheart.demos.mapper.LitemMapper;
import com.biboheart.demos.service.LitemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LitemServiceImpl implements LitemService {

    @Resource
    private LitemMapper litemMapper;

    @Override
    public List<Litem> selItems(Litem litem) {
        return litemMapper.selItems(litem);
    }

}
