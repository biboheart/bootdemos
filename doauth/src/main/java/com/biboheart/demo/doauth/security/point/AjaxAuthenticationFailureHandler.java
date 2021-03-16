package com.biboheart.demo.doauth.security.point;

import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.demo.doauth.security.model.AjaxResponseBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.getWriter().write(JsonUtils.obj2json(new AjaxResponseBody(400, "登录失败", null, null)));
    }
}
