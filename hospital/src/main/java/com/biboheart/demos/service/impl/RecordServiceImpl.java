package com.biboheart.demos.service.impl;

import com.biboheart.demos.entity.Record;
import com.biboheart.demos.mapper.RecordMapper;
import com.biboheart.demos.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordMapper rd;
    @Override
    public List<Record> selrecord(Record record) {
        return rd.selrecord(record);
    }

    @Override
    public int addjilu(Record record) {
        return rd.addjilu(record);
    }
}
