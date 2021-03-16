package com.biboheart.demo.doauth.security.provider;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.demo.doauth.security.tokens.MobileCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class MobileCodeAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String mobile = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
//		System.out.println(this.getClass().getName() + "---mobile:" + mobile);
		if (!"13989830241".equals(mobile)) {
			throw new BadCredentialsException("用户不存在");
		}
		MobileCodeAuthenticationToken result = new MobileCodeAuthenticationToken(mobile,
				listUserGrantedAuthorities(mobile));
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		System.out.println(this.getClass().getName() + "---supports");
		return (MobileCodeAuthenticationToken.class.isAssignableFrom(authentication));
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
