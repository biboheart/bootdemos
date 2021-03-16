package com.biboheart.demo.doauth.controller;

import com.biboheart.brick.model.BhResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@SessionAttributes("authorizationRequest")
public class SecurityController {
	private final ApprovalStore approvalStore;

	@Autowired
	public SecurityController(ApprovalStore approvalStore) {
		this.approvalStore = approvalStore;
	}

	@RequestMapping(value = "/api/user/info")
	@ResponseBody
	public ResponseEntity<?> user(Principal principal) {
		return new ResponseEntity<>(principal, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/user/name")
	@ResponseBody
	public String username(Principal principal) {
		return principal.getName();
	}
	
	@RequestMapping(value = "/api/user/authorities")
	@ResponseBody
	public BhResponseResult<?> authorities(Principal principal) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return new BhResponseResult<>(0, "操作成功", authorities);
	}
	
	/*@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}*/
	
	@RequestMapping("/oauth/confirm_access")
	public ModelAndView getAccessConfirmation(Map<String, Object> model, Principal principal) {
		AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove("authorizationRequest");
		model.put("auth_request", clientAuth);
		model.put("client", "client");
		Map<String, String> scopes = new LinkedHashMap<String, String>();
		for (String scope : clientAuth.getScope()) {
			scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, "false");
		}
		for (Approval approval : approvalStore.getApprovals(principal.getName(), "client")) {
			if (clientAuth.getScope().contains(approval.getScope())) {
				scopes.put(OAuth2Utils.SCOPE_PREFIX + approval.getScope(),
						approval.getStatus() == ApprovalStatus.APPROVED ? "true" : "false");
			}
		}
		model.put("scopes", scopes);
		return new ModelAndView("access_confirmation", model);
	}
}
