package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.Services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;


	@GetMapping("/volunteering")
	public String register(Map<String, Object> model) {
		model.put("title", "Registrarse");

		return "volunteering";
	}


	@GetMapping("/users")
	public Iterable<User> listUsers() {

		return userRepo.findAll();
	}


}