package com.biboheart.demos.collect.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.demos.collect.RecordDataUploader;
import com.biboheart.demos.support.BhHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecordDataUploaderImpl implements RecordDataUploader {
    private final RestTemplate oauth2RestTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> uploadData(Map<String, Object> data) throws BhException {
        return BhHttpClient.request(oauth2RestTemplate, "POST",
                "http://jhhis.tsyljt.com/platforminsurance/platformapi/insurance/record/data/save",
                null,
                data,
                null,
                Map.class);
    }
}
