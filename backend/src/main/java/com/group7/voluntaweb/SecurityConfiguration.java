package com.group7.voluntaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.group7.voluntaweb.Repositories.UserRepositoryAuthProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		// Login pages
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		// Logout page
		http.authorizeRequests().antMatchers("/logout").authenticated();
		// Register pages and save actions
		http.authorizeRequests().antMatchers("/register").anonymous();
		http.authorizeRequests().antMatchers("/register-ong").anonymous();
		http.authorizeRequests().antMatchers("/add-ong").anonymous();
		http.authorizeRequests().antMatchers("/register-user").anonymous();
		http.authorizeRequests().antMatchers("/add-user").anonymous();
		// Assets
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/images/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/plugins/**").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN");
		
		http.authorizeRequests().anyRequest().authenticated();

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/login");
		http.formLogin().failureUrl("/loginerror");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// Disable CSRF at the moment
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);

	}

}