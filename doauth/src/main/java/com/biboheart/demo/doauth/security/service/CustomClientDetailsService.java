package com.biboheart.demo.doauth.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.HashMap;

//@Component("customClientDetailsService")
public class CustomClientDetailsService implements ClientDetailsService {
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomClientDetailsService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
		BaseClientDetails details = new BaseClientDetails("client", null, "all",
				"client_credentials,password,refresh_token,authorization_code", null, null);
		details.setClientSecret(passwordEncoder.encode("secret"));
		details.setAccessTokenValiditySeconds(3600);
		details.setRefreshTokenValiditySeconds(null);
		details.setAdditionalInformation(new HashMap<>());
		return details;
	}

}
