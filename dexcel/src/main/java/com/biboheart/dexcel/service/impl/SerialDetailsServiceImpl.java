package com.biboheart.dexcel.service.impl;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dexcel.entity.SerialDetailsForm;
import com.biboheart.dexcel.entity.SerialForm;
import com.biboheart.dexcel.model.SerialDetailsModel;
import com.biboheart.dexcel.repository.SerialDetailsFormRepository;
import com.biboheart.dexcel.repository.SerialFormRepository;
import com.biboheart.dexcel.service.SerialDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SerialDetailsServiceImpl implements SerialDetailsService {
    private final SerialFormRepository serialFormRepository;
    private final SerialDetailsFormRepository serialDetailsFormRepository;

    public SerialDetailsServiceImpl(SerialFormRepository serialFormRepository, SerialDetailsFormRepository serialDetailsFormRepository) {
        this.serialFormRepository = serialFormRepository;
        this.serialDetailsFormRepository = serialDetailsFormRepository;
    }

    @Override
    public void save(List<SerialDetailsModel> list) {
        SerialForm serialForm = new SerialForm();
        serialForm.setBillSn("20032"); // 要改
        serialForm.setContrastBillSn("20032"); // 要改
        serialForm.setBusinessSn("20032"); // 要改
        serialForm.setStoreSn("220212001"); // 要改
        serialForm.setStoreName("供应室"); // 要改
        serialForm.setSystemSn("0000");
        serialForm.setBusinessType("18");
        serialForm.setBusinessName("期初入库");
        serialForm.setOtherOrg(null);
        serialForm.setOtherOrgName(null);
        serialForm.setOtherDeptSn(null);
        serialForm.setOtherDeptName(null);
        serialForm.setOtherPersonSn(null);
        serialForm.setOtherPersonName(null);
        serialForm.setSupplier(null);
        serialForm.setDigested(null);
        serialForm.setStartTime(TimeUtils.getCurrentTimeInMillis());
        serialForm.setEndTime(TimeUtils.getCurrentTimeInMillis());
        serialForm.setStorekeeperSn("686213339898056704");
        serialForm.setStorekeeperName("超级管理员");
        serialForm.setPersonSn("686213339898056704");
        serialForm.setPersonName("超级管理员");
        serialForm.setAuditorSn("686213339898056704");
        serialForm.setAuditorName("超级管理员");
        serialForm.setExamineTime(TimeUtils.getCurrentTimeInMillis());
        serialForm = serialFormRepository.save(serialForm);
        List<SerialDetailsForm> detailsList = new ArrayList<>();
        for (SerialDetailsModel serialDetailsModel : list) {
            SerialDetailsForm form = new SerialDetailsForm();

//            form.setReservedField(serialDetailsModel.getReservedField().trim());
            if (!CheckUtils.isEmpty(serialDetailsModel.getBatchNumber())) {
                form.setBatchNumber(serialDetailsModel.getBatchNumber().trim());
                form.setBatchSn(serialDetailsModel.getBatchNumber().trim());
            } else {
                form.setBatchNumber("");
                form.setBatchSn("");
            }
            form.setPackageCount(serialDetailsModel.getPackageCount());
            /*form.setPieceSellingPrice(new BigDecimal(serialDetailsModel.getSellingPrice()).multiply(new BigDecimal(10000)).longValue());
            form.setPieceBuyingPrice(new BigDecimal(serialDetailsModel.getBuyingPrice()).multiply(new BigDecimal(10000)).longValue());
            form.setSaleUnit(serialDetailsModel.getSaleUnit());*/
            /*form.setPieceSellingPrice(new BigDecimal(serialDetailsModel.getSellingPrice()).multiply(new BigDecimal(10000)).longValue());*/
            /*form.setSellingPrice(form.getPieceSellingPrice());*/
            form.setPieceBuyingPrice(0L);
            //form.setSaleUnit(serialDetailsModel.getSaleUnit());

            form.setSerialId(serialForm.getId());
            long now = TimeUtils.getCurrentTimeInMillis();
            long manufactureTime = TimeUtils.getDateMillis("2022-02-01 12:00:00");
            long expirationTime = TimeUtils.getDateMillis("2026-02-01 12:00:00");
            form.setTime(now);
            form.setBillingPersonSn("686213339898056704");
            form.setBillingPersonName("超级管理员");
            form.setBillingDeptSn("220202009");
            form.setBillingDeptName("信息科");
            form.setStorageTime(now);
            form.setManufactureTime(manufactureTime);
            form.setExpirationTime(expirationTime);
            detailsList.add(form);
        }
        serialDetailsFormRepository.saveAll(detailsList);
    }

    @Override
    public Map<String, Object> list(Long serialId) {
        List<SerialDetailsForm> formList = serialDetailsFormRepository.findBySerialIdAndItemsSnNotNull(serialId);
        if (CheckUtils.isEmpty(formList)) return null;
        Map<String, Object> map = new HashMap<>();
        SerialForm serialForm = serialFormRepository.findById(serialId).orElse(null);
        BeanUtils.beanToMap(serialForm, map, null, Collections.singletonList("id"));
        if (CheckUtils.isEmpty(formList)) return null;
        /*for (SerialDetailsForm form : formList) {
            form.setBuyingPrice(form.getPieceBuyingPrice() / form.getConversionCoefficient());
            form.setSellingPrice(form.getPieceSellingPrice() * form.getConversionCoefficient());
        }
        serialDetailsFormRepository.saveAll(formList);*/
        map.put("details", serialDetailsFormRepository.findBySerialIdAndItemsSnNotNull(serialId));
        return map;
    }
}
