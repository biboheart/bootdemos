package com.biboheart.demo.doauth.security.service;

import com.biboheart.brick.utils.CheckUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(CheckUtils.isEmpty(username)) {
			throw new BadCredentialsException("用户名不能为空");
		}
		System.out.println(username);
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new org.springframework.security.core.userdetails.User("test", "admin",
				true, true, true, true, authorities);
	}

}
