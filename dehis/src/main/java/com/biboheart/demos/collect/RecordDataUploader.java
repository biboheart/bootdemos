package com.biboheart.demos.collect;

import com.biboheart.brick.exception.BhException;

import java.util.Map;

public interface RecordDataUploader {
    Map<String, Object> uploadData(Map<String, Object> data) throws BhException;
}
