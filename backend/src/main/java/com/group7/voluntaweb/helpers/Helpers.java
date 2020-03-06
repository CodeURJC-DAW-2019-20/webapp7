package com.group7.voluntaweb.helpers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;

public class Helpers {
	
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ONGComponent ongComponent;
	
	public void setNavbar(Model model, User user, ONG ong, Boolean isAdmin) {
		



		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("logged_user", true);
			model.addAttribute("logged", true);
		} else if (ong != null) {
			model.addAttribute("user", ong);
			model.addAttribute("logged_ong", true);
			model.addAttribute("logged", true);
		} else if (isAdmin) {
			model.addAttribute("logged_admin", true);
			model.addAttribute("logged", true);
		} else {
			model.addAttribute("logged", false);

		}
		
	}

}
