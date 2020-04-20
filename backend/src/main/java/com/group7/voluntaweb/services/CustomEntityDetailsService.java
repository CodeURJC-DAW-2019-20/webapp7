package com.group7.voluntaweb.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;

@Service
public class CustomEntityDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ONGRepository ongRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ONGComponent ongComponent;
	@Autowired
	private GenericComponent genComponent;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// first try loading from User table
		User user = userRepository.findByEmail(username);

		if (user != null) {

			genComponent.setLoggedUser(user);
			userComponent.setLoggedUser(user);
			return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRoles().get(0));
		} else {
			// Not found in user table, so check ong
			ONG ong = ongRepository.findByEmail(username);
			if (ong != null) {
				genComponent.setLoggedUser(ong);
				ongComponent.setLoggedUser(ong);
				return new CustomUserDetails(ong.getEmail(), ong.getPassword(), null);
			}
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

	public class CustomUserDetails implements UserDetails {

		private String username;
		private String password;
		private Collection<? extends GrantedAuthority> authorities;

		public CustomUserDetails() {
			super();
		}

		public CustomUserDetails(String username, String password, String role) {
			this.username = username;
			this.password = password;
			if (role != null) {
				ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
				this.authorities = grantedAuthorities;
			}
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getUsername() {
			return username;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}

}