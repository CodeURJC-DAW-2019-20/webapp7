package com.group7.voluntaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.group7.voluntaweb.Repositories.UserRepositoryAuthProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    public UserRepositoryAuthProvider userRepoAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        	//Login pages
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        	//Logout page
        http.authorizeRequests().antMatchers("/logout").permitAll();
        	//Register pages and save actions
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/register-ong").permitAll();
        http.authorizeRequests().antMatchers("/add-ong").permitAll();
        http.authorizeRequests().antMatchers("/register-user").permitAll();
        http.authorizeRequests().antMatchers("/add-user").permitAll();
        	//Assets
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        http.authorizeRequests().antMatchers("/plugins/**").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN");;

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