package com.group7.voluntaweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.Services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;


	@GetMapping("/volunteering")
	public String register(Model model) {
		model.addAttribute("title", "Volunteering");

		return "volunteering";
	}

	@GetMapping("/add-volunteering")
	public String adding(Model model, Volunteering advert) {
		model.addAttribute("advert", advert);

		return "ong-submit-advertisement";
	}
	
	@GetMapping("/users")
	public Iterable<User> listUsers() {

		return userRepo.findAll();
	}


}