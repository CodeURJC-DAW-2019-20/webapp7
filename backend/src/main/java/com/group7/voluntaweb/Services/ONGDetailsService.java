package com.group7.voluntaweb.Services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.UserRepository;

import antlr.collections.List;

@Service
public class ONGDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ONGRepository ongRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // first try loading from User table
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getName());
        } else {
            // Not found in user table, so check ong
            ONG admin = ongRepository.findByEmail(username);
            if (admin != null) {
                return new CustomUserDetails(admin.getEmail(), admin.getPassword(), null);
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