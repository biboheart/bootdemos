package com.biboheart.dwechat.wechat.utils;

import com.biboheart.dwechat.wechat.exception.AesException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WechatEncryptionUtils {
    private static Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 对密文进行解密.
     * @param text 需要解密的密文
     * @param appId 公众号APPID
     * @param encodingAesKey 加解密公钥
     * @return 解密得到的明文
     * @throws AesException aes解密失败
     */
    public static String decrypt(String text, String appId, String encodingAesKey) throws AesException {
        byte[] original;
        try {
            // 设置解密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            byte[] aesKey = Base64.getDecoder().decode(encodingAesKey + "=");
            SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.getDecoder().decode(text);

            // 解密
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.DecryptAESError);
        }

        String xmlContent, from_appid;
        try {
            // 去除补位字符
            byte[] bytes = PKCS7Encoder.decode(original);

            // 分离16位随机字符串,网络字节序和AppId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

            int xmlLength = recoverNetworkBytesOrder(networkOrder);

            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                    CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.IllegalBuffer);
        }

        // appid不相同的情况
        if (!from_appid.equals(appId)) {
            throw new AesException(AesException.ValidateAppidError);
        }
        return xmlContent;
    }

    /**
     * 生成4个字节的网络字节序
     * @param sourceNumber 转换的数字
     * @return 网络字节序
     */
    public static byte[] getNetworkBytesOrder(int sourceNumber) {
        byte[] orderBytes = new byte[4];
        orderBytes[3] = (byte) (sourceNumber & 0xFF);
        orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
        orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
        orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
        return orderBytes;
    }

    /**
     * 网络字节序还原成整数
     * @param orderBytes 网络字节序
     * @return 还原后的整数
     */
    public static int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    /**
     * 验证URL
     * @param msgSignature 签名串，对应URL参数的msg_signature
     * @param timeStamp 时间戳，对应URL参数的timestamp
     * @param nonce 随机串，对应URL参数的nonce
     * @param echoStr 随机串，对应URL参数的echostr
     * @param token 公众号token
     * @param appId 公众号APPID
     * @param encodingAesKey 公众号加解密key
     * @return 解密之后的echostr
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static String verifyUrl(String msgSignature, String timeStamp, String nonce, String echoStr, String token, String appId, String encodingAesKey)
            throws AesException {
        String signature = (null == echoStr || "".equals(echoStr.trim())) ? getSHA1(token, timeStamp, nonce) : getSHA1(token, timeStamp, nonce, echoStr);

        if (!signature.equals(msgSignature)) {
            throw new AesException(AesException.ValidateSignatureError);
        }
        if (null == echoStr || "".equals(echoStr.trim()) || null == appId || "".equals(appId.trim()) || null == encodingAesKey || "".equals(encodingAesKey.trim())) {
            return signature;
        }
        return decrypt(echoStr, appId, encodingAesKey);
    }

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

    /**
     * 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return 安全签名
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static String getSHA1(String token, String timestamp, String nonce) throws AesException
    {
        try {
            String[] array = new String[] { token, timestamp, nonce };
            StringBuilder sb = new StringBuilder();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return byteArrayToHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }
    }

    /**
     * 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param encrypt 密文
     * @return 安全签名
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
    {
        try {
            String[] array = new String[] { token, timestamp, nonce, encrypt };
            // 字符串排序
            Arrays.sort(array);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return byteArrayToHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }
    }

    public static String getSHA1(String data) {
        // SHA1签名生成
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            return byteArrayToHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder hexstr = new StringBuilder();
        String shaHex = "";
        for (byte aByte : bytes) {
            shaHex = Integer.toHexString(aByte & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }

        return hexstr.toString();
    }
}
