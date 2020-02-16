package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.UserRepository;

import com.group7.voluntaweb.Services.UserService;


@Controller
public class UserController {
	
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserService userService;

	// inject via application.properties
	@Value("${app.register.title}")
	private String TITLE = "";

	@Value("${app.registerUser.title}")
	private String UTITLE = "";
	
	@Value("${app.registerONG.title}")
	private String OTITLE = "";

	@GetMapping("/register")
	public String register(Map<String, Object> model) {
		model.put("title", TITLE);

		return "register";
	}
	
	
	@GetMapping("/register-user")
	public String registerUser(Map<String, Object> model) {
		model.put("title", UTITLE);

		return "registerVolunteers";
	}
	
	@PostMapping("/add-user")
	public String addUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String city, @RequestParam String telephone, @RequestParam String password, Map<String, Object> model) {
		model.put("title", UTITLE);
		
		
		String enc_password = new BCryptPasswordEncoder().encode(password);
		
		List<String> roles = new ArrayList<String>();
		
		roles.add("ROLE_USER");
		
		
		User user = new User(name, surname, email, enc_password, city, telephone, null, roles);
		
		
		
		this.userService.save(user);
		
		
		
		return "redirect:register";

	}
	
	@GetMapping("/users")
	public Collection<User> listUsers() {
	
		return userRepo.findAll();
	}
	
	@GetMapping("/login")
	public String login(Map<String, Object> model) {
		model.put("title", "Iniciar sesión");
		return "login";
	}
	
		


}