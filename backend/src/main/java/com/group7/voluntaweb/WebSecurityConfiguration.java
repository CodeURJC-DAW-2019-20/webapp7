package com.group7.voluntaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.group7.voluntaweb.services.ONGDetailsService;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	public ONGRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/ongs").permitAll();
		http.authorizeRequests().antMatchers("/ongs/**").permitAll();
		http.authorizeRequests().antMatchers("/search").permitAll();
		http.authorizeRequests().antMatchers("/search/**").permitAll();
		http.authorizeRequests().antMatchers("/volunteering/**").permitAll();
		// Login pages
		http.authorizeRequests().antMatchers("/login").anonymous();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		// Logout page
		http.authorizeRequests().antMatchers("/logout").permitAll();
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
		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
		;
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
		 http.exceptionHandling()
         //Actually Spring already configures default AuthenticationEntryPoint - LoginUrlAuthenticationEntryPoint
         //This one is REST-specific addition to default one, that is based on PathRequest
         .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/api/**"));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = new BCryptPasswordEncoder();

		// Users
		auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");

		auth.inMemoryAuthentication().withUser("admin@admin.com").password("{noop}password").roles("USER", "ADMIN");

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
	
	private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

}