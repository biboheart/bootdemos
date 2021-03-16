package com.biboheart.dsession.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/hello")
	public ResponseEntity<?> hello(HttpSession session) {
		if (session.isNew()) {
			logger.info("Successfully creates a session ，the id of session ：" + session.getId());
			session.setAttribute("key", "hello");
		} else {
			logger.info("session already exists in the server, the id of session ：" + session.getId());
			logger.info(session.getAttribute("key").toString());
		}
		return new ResponseEntity<>("Hello World", HttpStatus.OK);
	}

}
