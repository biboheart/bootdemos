package com.biboheart.demo.doauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class DemoController {
    private final HttpSession httpSession;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public DemoController(HttpSession httpSession, StringRedisTemplate stringRedisTemplate) {
        this.httpSession = httpSession;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/api/demo")
    public String demo() {
        return "demo";
    }

    @GetMapping("/public/session")
    public ResponseEntity<?> session(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.info("name:" + cookie.getName() + ";value:" + cookie.getValue());
            }
        }
        System.out.println(request.getHeader(HttpHeaders.AUTHORIZATION));
        String sessionId = httpSession.getId();
        if (httpSession.isNew()) {
            log.info("Successfully creates a session ，the id of session ：" + sessionId);
        } else {
            log.info("session already exists in the server, the id of session ：" + sessionId);
            Object tokenObj = httpSession.getAttribute("access_token");
            if (null != tokenObj) {
                log.info("token:" + String.valueOf(tokenObj));
            }
        }
        return new ResponseEntity<>(sessionId, HttpStatus.OK);
    }

    @RequestMapping("/public/session/save")
    public ResponseEntity<?> saveSession(String token, Long expires, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.info("name:" + cookie.getName() + ";value:" + cookie.getValue());
                if ("SESSION".equals(cookie.getName())) {
                    stringRedisTemplate.opsForValue().set(cookie.getValue(), token, expires, TimeUnit.MILLISECONDS);
                }
            }
        }
        httpSession.setAttribute("access_token", token);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping("/platformapi/user/session/save")
    public ResponseEntity<?> saveSession2(String token, Long expires, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.info("name:" + cookie.getName() + ";value:" + cookie.getValue());
                if ("SESSION".equals(cookie.getName())) {
                    stringRedisTemplate.opsForValue().set(cookie.getValue(), token, expires, TimeUnit.MILLISECONDS);
                }
            }
        }
        httpSession.setAttribute("access_token", token);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/test/session")
    public ResponseEntity<?> testSession(HttpServletRequest request, Principal principal) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.info("name:" + cookie.getName() + ";value:" + cookie.getValue());
            }
        }
        String sessionId = httpSession.getId();
        if (httpSession.isNew()) {
            log.info("Successfully creates a session ，the id of session ：" + sessionId);
        } else {
            log.info("session already exists in the server, the id of session ：" + sessionId);
        }
        return new ResponseEntity<>(principal.getName(), HttpStatus.OK);
    }

    @GetMapping("/api/session")
    public ResponseEntity<?> apiSession(HttpServletRequest request, Principal principal) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.info("name:" + cookie.getName() + ";value:" + cookie.getValue());
            }
        }
        String sessionId = httpSession.getId();
        if (httpSession.isNew()) {
            log.info("Successfully creates a session ，the id of session ：" + sessionId);
        } else {
            log.info("session already exists in the server, the id of session ：" + sessionId);
        }
        return new ResponseEntity<>(principal.getName(), HttpStatus.OK);
    }
}
