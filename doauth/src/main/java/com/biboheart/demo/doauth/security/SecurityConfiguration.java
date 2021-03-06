package com.biboheart.demo.doauth.security;

import com.biboheart.demo.doauth.security.filter.MobileCodeAuthenticationProcessingFilter;
import com.biboheart.demo.doauth.security.handler.SecuritySuccessHandler;
import com.biboheart.demo.doauth.security.provider.MobileCodeAuthenticationProvider;
import com.biboheart.demo.doauth.security.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.authenticationProvider(mobileCodeAuthenticationProvider())
			.authenticationProvider(usernamePasswordAuthenticationProvider());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/home", "/loginIn", "/login", "/mobileCodeLogin", "/public/**", "/platformapi/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                //.successHandler(successHandler())
                .and()
            /*.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()*/
            .logout()
                .permitAll();
        http.addFilterBefore(mobileCodeAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public MobileCodeAuthenticationProcessingFilter mobileCodeAuthenticationProcessingFilter() {
    	MobileCodeAuthenticationProcessingFilter filter = new MobileCodeAuthenticationProcessingFilter();
    	filter.setAuthenticationManager(authenticationManager);
    	return filter;
    }
    
    @Bean
    public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider() {
    	return new UsernamePasswordAuthenticationProvider();
    }
    
    @Bean
    public MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider() {
    	return new MobileCodeAuthenticationProvider();
    }
    
    @Bean
    public AuthenticationSuccessHandler successHandler () {
    	return new SecuritySuccessHandler();
    }
    
    /*public AuthenticationEntryPoint authenticationEntryPoint () {
    	return new SecurityEntryPoint();
    }*/

}
