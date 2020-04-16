package com.group7.voluntaweb.helpers;

import org.springframework.ui.Model;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;

public class Helpers {
	
	
	public void setNavbar(Model model, User user, ONG ong, Boolean isAdmin) {
		


		if (user != null && !isAdmin) {
			model.addAttribute("user", user);
			model.addAttribute("logged_user", true);
			model.addAttribute("logged", true);
		} else if (ong != null) {
			model.addAttribute("user", ong);
			model.addAttribute("logged_ong", true);
			model.addAttribute("logged", true);
		} else if (isAdmin) {
			model.addAttribute("user", user);
			model.addAttribute("logged_admin", true);
			model.addAttribute("logged", true);
		} else {
			model.addAttribute("logged", false);

		}
		
	}
	

}
