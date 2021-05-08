package com.biboheart.demos.service;


import com.biboheart.demos.entity.Lrecord;
import com.biboheart.demos.entity.Pay;
import com.biboheart.demos.entity.Register;

import java.util.List;

public interface LpayService {

    int updPay(Register register);
    int addPay(Register register);
    List<Pay> selPays(Register register);
    List<Lrecord> selSurplus(Lrecord lrecord);
}
