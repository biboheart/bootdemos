package com.biboheart.demo.doauth.security.provider;

import com.biboheart.brick.utils.CheckUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
//		System.out.println(this.getClass().getName() + "---username:" + username);
		if (!"admin".equals(username)) {
			throw new BadCredentialsException("用户不存在");
		}
		String sn = UUID.randomUUID().toString();
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(sn,
				authentication.getCredentials(), listUserGrantedAuthorities(sn));
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		System.out.println(this.getClass().getName() + "---supports");
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	private Set<GrantedAuthority> listUserGrantedAuthorities(String username) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if (CheckUtils.isEmpty(username)) {
			return authorities;
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}
