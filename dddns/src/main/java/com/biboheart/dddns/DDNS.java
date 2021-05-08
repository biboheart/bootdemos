package com.biboheart.dddns;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DDNS {
    public void update() {
        //  设置鉴权参数，初始化客户端
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",// 地域ID
                "LTAI5tDnFq5ToDKrLKhzkrNY",// 您的AccessKey ID
                "NDjgJZ3WdTfwcHAuuLF05rXM23782P");// 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        //查询指定二级域名的最新解析记录
        DescribeSubDomainRecordsRequest describeSubDomainRecordsRequest = new DescribeSubDomainRecordsRequest();
        describeSubDomainRecordsRequest.setSubDomain("eber.tsyljt.cn");
        DescribeSubDomainRecordsResponse describeSubDomainRecordsResponse = this.describeSubDomainRecords(describeSubDomainRecordsRequest, client);
        //log_print("describeSubDomainRecords",describeSubDomainRecordsResponse);
        List<DescribeSubDomainRecordsResponse.Record> domainRecords = describeSubDomainRecordsResponse.getDomainRecords();
        //  当前主机公网IP
        String currentHostIP = this.getCurrenHostIP();
        // System.out.println("-------------------------------当前主机公网IP为："+currentHostIP+"-------------------------------");
        //最新的一条解析记录
        if (domainRecords.size() == 0) {
            return;
        }
        DescribeSubDomainRecordsResponse.Record record = domainRecords.get(0);
        //  记录ID
        String recordId = record.getRecordId();
        //  记录值
        String recordsValue = record.getValue();
        String rR = record.getRR();
        if (!"eber".equals(rR)) {
            return;
        }
        if (currentHostIP.equals(recordsValue)) {
            return;
        }
        System.out.println("原IP:" + recordsValue + ";当前主机公网IP为" + currentHostIP + "");
        //  修改解析记录
        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
        //  主机记录
        updateDomainRecordRequest.setRR("eber");
        //  记录ID
        updateDomainRecordRequest.setRecordId(recordId);
        //  将主机记录值改为当前主机IP
        updateDomainRecordRequest.setValue(currentHostIP);
        //  解析记录类型
        updateDomainRecordRequest.setType("A");
        UpdateDomainRecordResponse updateDomainRecordResponse = this.updateDomainRecord(updateDomainRecordRequest, client);
        log_print("updateDomainRecord",updateDomainRecordResponse);
    }

    /**
     * 获取主域名的所有解析记录列表
     */
    private DescribeSubDomainRecordsResponse describeSubDomainRecords(DescribeSubDomainRecordsRequest request, IAcsClient client) {
        try{
            // 调用SDK发送请求
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            // 发生调用错误，抛出运行时异常
            throw new RuntimeException();
        }
    }

    /**
     * 修改解析记录
     */
    private UpdateDomainRecordResponse updateDomainRecord(UpdateDomainRecordRequest request, IAcsClient client) {
        try {
            //  调用SDK发送请求
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            //  发生调用错误，抛出运行时异常
            throw new RuntimeException();
        }
    }

    /**
     * 获取当前主机公网IP
     */
    private String getCurrenHostIP() {
        // 这里使用jsonip.com第三方接口获取本地IP
        String jsonip = "https://jsonip.com";
        // 接口返回结果
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            // 使用HttpURLConnection网络请求第三方接口
            URL url = new URL(jsonip);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //  正则表达式，提取xxx.xxx.xxx.xxx，将IP地址从接口返回结果中提取出来
        String rexp = "(\\d{1,3}\\.){3}\\d{1,3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(result.toString());
        String res = "";
        if (mat.find()) {
            res = mat.group();
        }
        return res;
    }

    private void log_print(String functionName, Object result) {
        Gson gson = new Gson();
        System.out.println("-------------------------------" + functionName + "-------------------------------");
        System.out.println(gson.toJson(result));
    }
}
