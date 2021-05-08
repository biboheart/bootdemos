package com.biboheart.demos.service.impl;

import com.biboheart.demos.entity.Lrecord;
import com.biboheart.demos.entity.Pay;
import com.biboheart.demos.entity.Register;
import com.biboheart.demos.mapper.LpayMapper;
import com.biboheart.demos.service.LpayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LpayServiceImpl implements LpayService {

    @Resource
    private LpayMapper lpayMapper;

    @Override
    public int updPay(Register register) {
        return lpayMapper.updPay(register);
    }

    @Override
    public int addPay(Register register) {
        return lpayMapper.addPay(register);
    }

    @Override
    public List<Pay> selPays(Register register) {
        return lpayMapper.selPays(register);
    }

    @Override
    public List<Lrecord> selSurplus(Lrecord lrecord) {
        return lpayMapper.selSurplus(lrecord);
    }
}
