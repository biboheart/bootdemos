package com.biboheart.dweixin.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class RequestUtils {
    public static String baseUrl(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String host = null;
        String proto = "http";
        String prefix = null;
        String port = "80";
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        Enumeration<String> es = request.getHeaderNames();
        while (es.hasMoreElements()) {
            String element = es.nextElement().toLowerCase();
            if ("x-forwarded-host".equals(element)) {
                String[] hosts = request.getHeader(element).split(",");
                if (hosts.length > 0) {
                    host = hosts[0];
                }
                if (null != host && host.contains(":")) {
                    host = host.substring(0, host.indexOf(":"));
                }
                continue;
            }
            if ("x-forwarded-proto".equals(element)) {
                String[] protos = request.getHeader(element).split(",");
                if (protos.length > 0) {
                    proto = protos[0];
                }
                continue;
            }
            if ("x-forwarded-prefix".equals(element)) {
                prefix = request.getHeader(element);
                continue;
            }
            if ("x-forwarded-port".equals(element)) {
                String[] ports = request.getHeader(element).split(",");
                if (ports.length > 0) {
                    port = ports[0];
                }
            }
        }
        if (null != host) {
            StringBuilder sb = new StringBuilder(proto);
            sb.append("://").append(host);
            if (!("80".equals(port) && "http".equals(proto)) && !("443".equals(port) && "https".equals(proto))) {
                sb.append(":").append(port);
            }
            if (null != prefix) {
                sb.append(prefix);
            }
            url = sb.toString();
        } else if (null != uri && uri.length() > 1) {
            url = url.substring(0, url.indexOf(uri));
        }
        url = url.replace("http:", "https:");
        return url;
    }

    public static String requestIp(HttpServletRequest request) {
        String ip = null;
        // X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
