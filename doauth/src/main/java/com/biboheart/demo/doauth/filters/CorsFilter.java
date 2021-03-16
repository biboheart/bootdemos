package com.biboheart.demo.doauth.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Credentials, X-Auth-Token, x-csrf-token, Origin, X-Requested-With, X-PINGOTHER, Content-Type, Accept, Authorization, access_secret, access_token");
		response.setHeader("Access-Control-Max-Age", "3600");
		if(!request.getMethod().equals("OPTIONS")) {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
