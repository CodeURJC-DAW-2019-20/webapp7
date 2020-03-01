package com.group7.voluntaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.group7.voluntaweb.Repositories.UserRepositoryAuthProvider;

@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Il viejo truco de saltarte el login
		http.authorizeRequests().antMatchers("/ong-settings").permitAll();
		http.authorizeRequests().antMatchers("/ong-settings-form").permitAll();
		http.authorizeRequests().antMatchers("/ong-submit-advertisement").permitAll();
		http.authorizeRequests().antMatchers("/ong-submit-advertisement-form").permitAll();
		http.authorizeRequests().antMatchers("/ong-edit-advertisement**").permitAll();
		http.authorizeRequests().antMatchers("/ong-remove-advertisement**").permitAll();
		http.authorizeRequests().antMatchers("/volunteering-gestion-panel").permitAll();
		http.authorizeRequests().antMatchers("/volunteering/**").permitAll();
		
		//Il viejo truco de saltarte el login

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/ongs").permitAll();
		http.authorizeRequests().antMatchers("/ongs/**").permitAll();
		http.authorizeRequests().antMatchers("/contact").permitAll();
		http.authorizeRequests().antMatchers("/about-us").permitAll();
		http.authorizeRequests().antMatchers("/new-message").permitAll();
		// Login pages
		http.authorizeRequests().antMatchers("/login").anonymous();
		http.authorizeRequests().antMatchers("/loginerror").anonymous();
		// Logout page
		http.authorizeRequests().antMatchers("/logout").authenticated();
		// Register pages and save actions
		http.authorizeRequests().antMatchers("/register").anonymous();
		http.authorizeRequests().antMatchers("/register-ong").anonymous();
		http.authorizeRequests().antMatchers("/add-ong").anonymous();
		http.authorizeRequests().antMatchers("/register-user").anonymous();
		http.authorizeRequests().antMatchers("/add-user").anonymous();
		http.authorizeRequests().antMatchers("/login-ong").anonymous();
		// Assets
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/images/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/plugins/**").permitAll();
		http.authorizeRequests().antMatchers("/search/**").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN");
		
		http.authorizeRequests().anyRequest().authenticated();

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
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

