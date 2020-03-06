package com.group7.voluntaweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.Comment;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.repositories.CommentRepository;

@Controller
public class CErrorController implements ErrorController {
	
	@Autowired
	private UserComponent userComponent;
	
	
    @RequestMapping("/error")
    public String handleError(Model model) {
    	
    	User user = userComponent.getLoggedUser();
		boolean logged = userComponent.isLoggedUser();
		
		model.addAttribute("user", user);
		model.addAttribute("logged", logged);
        model.addAttribute("title", "Error");
        return "error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
