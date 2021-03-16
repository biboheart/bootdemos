package com.biboheart.dwechat.wechat.utils;

import com.biboheart.dwechat.wechat.exception.AesException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WechatEncryptionUtils {
    /* 提取出xml数据包中的加密消息
     * @param xmltext 待提取的xml字符串
     * @return 提取出的加密消息字符串
     * @throws AesException
     * @throws AesException 异常信息
     */
    public static Object[] extract(String xmltext) throws AesException {
        Object[] result = new Object[3];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmltext);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");
            NodeList nodelist2 = root.getElementsByTagName("ToUserName");
            result[0] = 0;
            result[1] = nodelist1.item(0).getTextContent();
            result[2] = nodelist2.item(0).getTextContent();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ParseXmlError);
        }
    }

    /**
     * 生成随机字符串，包含随机字母与数字的组合
     * @param count 字符串长度
     * @return {count}位长的随机字符串
     */
    public static String getRandomStr(int count) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成xml消息
     * @param encrypt 加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return 生成的xml字符串
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {
        String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
                + "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
                + "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
        return String.format(format, encrypt, signature, timestamp, nonce);
    }

    /**
     * 生成xml消息，简单把map转成xml
     * @param data map数据
     * @return 转成xml后的字符串
     */
    public static String generatePayData(Map<String, Object> data) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>\n");
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (null == value) {
                continue;
            }
            sb.append("<");
            sb.append(key);
            sb.append(">");
            sb.append(value);
            sb.append("</");
            sb.append(key);
            sb.append(">\n");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 微信支付签名
     * @param data 发送或者接收的数据
     * @param encodingAesKey 商户key
     * @return MD5签名结果
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static String sign(Map<String, Object> data, String encodingAesKey) throws AesException {
        if (null == data || data.isEmpty() || null == encodingAesKey || "".equals(encodingAesKey)) {
            throw new AesException(AesException.ValidateSignatureError);
        }
        List<String> keyList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (null == value) {
                continue;
            }
            if ("sign".equals(key)) {
                continue;
            }
            keyList.add(key);
        }
        String[] array = keyList.toArray(new String[keyList.size()]);
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        for (String key : array) {
            sb.append(key);
            sb.append("=");
            sb.append(data.get(key));
            sb.append("&");
        }
        String stringSignTemp = sb.toString() + "key=" + encodingAesKey;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(stringSignTemp.getBytes());
            byte[] digest = md.digest();

            StringBuilder hexstr = new StringBuilder();
            String shaHex = "";
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new AesException(AesException.ValidateSignatureError);
        }
    }
}
