package com.biboheart.demo.doauth.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
	}
}
