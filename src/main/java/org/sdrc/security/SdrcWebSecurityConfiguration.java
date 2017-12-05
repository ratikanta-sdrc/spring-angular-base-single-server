package org.sdrc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SdrcWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private  UserAuthenticationProvider userAuthenticationProvider;

	@Autowired
	private void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(userAuthenticationProvider);
	}
	
	
	/*
	 * To alter any configuration related to WEB-Application please update the configuration.
	 * This Authentication provider internally manages the authentication creation mechanism,
	 * using the UserRoleFeaturePermissionMapping tables. 
	 */
	

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.httpBasic().and()
				.authorizeRequests()
					.antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
					.anyRequest().authenticated()
					.and()
				.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			// @formatter:on
		}
	
	 
}
