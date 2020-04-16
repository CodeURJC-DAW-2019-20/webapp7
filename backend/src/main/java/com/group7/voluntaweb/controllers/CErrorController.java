package com.group7.voluntaweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;

@Controller
public class CErrorController implements ErrorController {
	
	@Autowired
	private GenericComponent genCompo;
	
	
    @RequestMapping("/error")
    public String handleError(Model model) {
    	
    	Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof User) {
			User user = (User) genCompo.getLoggedUser();
			Boolean isAdmin = user.getRoles().contains("ROLE_ADMIN");
			helper.setNavbar(model, user, null, isAdmin);
			
		} else if (genCompo.getLoggedUser() instanceof ONG){
			ONG ong = (ONG) genCompo.getLoggedUser();
			helper.setNavbar(model, null, ong, false);
		}
        model.addAttribute("title", "Error");
        return "error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
