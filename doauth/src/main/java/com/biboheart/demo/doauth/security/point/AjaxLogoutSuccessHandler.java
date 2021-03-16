package com.biboheart.demo.doauth.security.point;

import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.demo.doauth.security.model.AjaxResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.getWriter().write(JsonUtils.obj2json(new AjaxResponseBody(200, "success", null, null)));
    }
}
