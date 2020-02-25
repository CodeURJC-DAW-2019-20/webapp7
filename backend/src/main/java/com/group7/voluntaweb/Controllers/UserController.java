package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.Services.UserService;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private UserComponent userComponent;

	@GetMapping("/register")
	public String register(Map<String, Object> model) {
		model.put("title", "Regístrarse");

		return "register";
	}

	@GetMapping("/register-user")
	public String registerUser(Map<String, Object> model) {
		model.put("title", "Registrar usuario");

		return "registerVolunteers";
	}

	@PostMapping("/add-user")
	public String addUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email,
			@RequestParam String city, @RequestParam String telephone, @RequestParam String password,
			Map<String, Object> model) {
		model.put("title", "Registrar usuario");

		String enc_password = new BCryptPasswordEncoder().encode(password);

		List<String> roles = new ArrayList<String>();

		roles.add("ROLE_USER");

		User user = new User(name, surname, email, enc_password, city, telephone, null, roles,
				Date.valueOf(LocalDate.now()));

		this.userService.save(user);

		return "redirect:register";

	}

	@GetMapping("/users")
	public Iterable<User> listUsers() {

		return userRepo.findAll();
	}

	@GetMapping("/login")
	public String login(Map<String, Object> model, HttpSession sesion) {
		model.put("title", "Iniciar sesión");

		Boolean logged = userComponent.isLoggedUser();
		User user = userComponent.getLoggedUser();

		model.put("logged", logged);
		model.put("user", user);

		return "login";
	}

}