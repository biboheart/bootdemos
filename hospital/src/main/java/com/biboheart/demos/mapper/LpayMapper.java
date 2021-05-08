package com.biboheart.demos.mapper;

import com.biboheart.demos.entity.Lrecord;
import com.biboheart.demos.entity.Pay;
import com.biboheart.demos.entity.Register;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LpayMapper {

    int updPay(Register register);
    int addPay(Register register);
    List<Pay> selPays(Register register);
    List<Lrecord> selSurplus(Lrecord lrecord);
}
