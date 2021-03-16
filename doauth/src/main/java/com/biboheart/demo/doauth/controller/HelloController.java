package com.biboheart.demo.doauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HelloController {
	private final HttpSession httpSession;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public HelloController(HttpSession httpSession, @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
		this.httpSession = httpSession;
		this.authenticationManager = authenticationManager;
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(HttpServletRequest request) {
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
		return "home";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(HttpServletRequest request) {
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
		return "hello";
	}

	@RequestMapping(value = "/loginIn", method = RequestMethod.GET)
	public String loginIn() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken("admin", "1234");
			System.out.println(authenticationManager);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch(AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
		}
		return "redirect:/";
	}

}
