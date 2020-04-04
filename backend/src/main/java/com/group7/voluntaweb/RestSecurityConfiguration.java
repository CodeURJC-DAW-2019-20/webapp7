package com.group7.voluntaweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.group7.voluntaweb.services.ONGDetailsService;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/login").authenticated();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/ong/login").authenticated();
		
		//Volunteerings
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/volunteering/").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/volunteering/").hasRole("ADMIN");
//		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/volunteering/").not().hasAnyRole("USER", "ADMIN");
//		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/volunteering/").not().hasAnyRole("USER");
//		
//		//ONGs
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/ong/").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/ong/").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/ong/").not().hasAnyRole("USER", "ADMIN");
//		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/ong/").not().hasAnyRole("USER");
//		
//		//Comments
//		
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/comment/").hasAnyRole("ADMIN");
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/comment/").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/comment/").hasRole("ADMIN");
//		
//		// Search
//		
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/search/**").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/search**").permitAll();
		

		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = new BCryptPasswordEncoder();

		// Users
		auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");

		//auth.inMemoryAuthentication().withUser("admin@admin.com").password("{noop}password").roles("USER", "ADMIN");

		auth.authenticationProvider(authProvider());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new ONGDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());

		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}
}