package com.group7.voluntaweb.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/")
	public String index(Model model) {
		
		User user = userComponent.getLoggedUser();
		boolean logged = userComponent.isLoggedUser();
		
		model.addAttribute("user", user);
		model.addAttribute("logged", logged);
		model.addAttribute("title", "Bienvenido");
		model.addAttribute("chart", true);
		model.addAttribute("1", userRepo.usersPerMonth(1));
		model.addAttribute("2", userRepo.usersPerMonth(2));
		model.addAttribute("3", userRepo.usersPerMonth(3));
		model.addAttribute("4", userRepo.usersPerMonth(4));
		model.addAttribute("5", userRepo.usersPerMonth(5));
		model.addAttribute("6", userRepo.usersPerMonth(6));
		model.addAttribute("7", userRepo.usersPerMonth(7));
		model.addAttribute("8", userRepo.usersPerMonth(8));
		model.addAttribute("9", userRepo.usersPerMonth(9));
		model.addAttribute("10", userRepo.usersPerMonth(10));
		model.addAttribute("11", userRepo.usersPerMonth(11));
		model.addAttribute("12", userRepo.usersPerMonth(12));
		return "index";
	}
	
	@RequestMapping("/index")
	public String index2(Model model) {
		return "redirect:/";
	}


}
