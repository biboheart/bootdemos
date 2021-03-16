package com.biboheart.demo.doauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
public class CookieController {
	private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

	@Autowired
	public CookieController(FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@RequestMapping("/test/findByUsername")
    @ResponseBody
    public Map<String, ? extends Session> findByUsername(@RequestParam String username) {
        Map<String, ? extends Session> usersSessions = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
        return usersSessions;
    }
	
	@RequestMapping("/test/session")
	@ResponseBody
	public ResponseEntity<?> session(HttpSession session, Principal principal) {
		String username = principal.getName();
		if (session.isNew()) {
			System.out.println("Successfully creates a session ，the id of session ：" + session.getId());
			session.setAttribute("username", username);
		} else {
			System.out.println("session already exists in the server, the id of session ：" + session.getId());
			Object oun = session.getAttribute("username");
			String un = null == oun ? null : oun.toString();
			if (null == un) {
				session.setAttribute("username", username);
			} else {
				System.out.println(session.getAttribute("username").toString());
			}
			System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		}
		return new ResponseEntity<>(username, HttpStatus.OK);
	}

}
