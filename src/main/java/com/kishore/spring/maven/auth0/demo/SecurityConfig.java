package com.kishore.spring.maven.auth0.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);

		http.authorizeHttpRequests().mvcMatchers("/", "/images/**")
		.permitAll().anyRequest().authenticated().and()
		.oauth2Login().and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.addLogoutHandler(logoutHandler);
	}

}
