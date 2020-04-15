package com.group7.voluntaweb;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.group7.voluntaweb.services.ONGDetailsService;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");

		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/login").authenticated();
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/ong/login").authenticated();

		// Volunteerings
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
		
		 http.exceptionHandling()
         //Actually Spring already configures default AuthenticationEntryPoint - LoginUrlAuthenticationEntryPoint
         //This one is REST-specific addition to default one, that is based on PathRequest
         .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/api/**"));
		   

		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {
		});
		
		http.cors().configurationSource(corsConfigurationSource());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = new BCryptPasswordEncoder();

		// Users
		auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");

		// auth.inMemoryAuthentication().withUser("admin@admin.com").password("{noop}password").roles("USER",
		// "ADMIN");

		auth.authenticationProvider(authProvider());
	}

	//This can be customized as required
		CorsConfigurationSource corsConfigurationSource() {
		    CorsConfiguration configuration = new CorsConfiguration();
		    List<String> allowOrigins = Arrays.asList("*");
		    configuration.setAllowedOrigins(allowOrigins);
		    configuration.setAllowedMethods(Arrays.asList("*"));
		    configuration.setAllowedHeaders(Arrays.asList("*"));
		    //in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
		    configuration.setAllowCredentials(true);
		    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		    source.registerCorsConfiguration("/**", configuration);
		    return source;
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