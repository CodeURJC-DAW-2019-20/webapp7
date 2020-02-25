package com.group7.voluntaweb.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.group7.voluntaweb.Components.ONGComponent;
import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.ONG;

/**
 * This class is used to check http credentials against database data. Also it
 * is responsible to set database user info into userComponent, a session scoped
 * bean that holds session user information.
 * 
 * NOTE: This class is not intended to be modified by app developer.
 */
@Component
public class ONGRepositoryAuthProvider implements AuthenticationProvider {

	@Autowired
	private ONGRepository ongRepository;

	@Autowired
	private ONGComponent ongComponent;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		ONG ong = ongRepository.findByEmail(username);



		if (!new BCryptPasswordEncoder().matches(password, ong.getPassword())) {

			throw new BadCredentialsException("Wrong password");
		} else {

			ongComponent.setLoggedUser(ong);

			

			return new UsernamePasswordAuthenticationToken(username, password);
		}
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
