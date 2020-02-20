package com.group7.voluntaweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;
import com.group7.voluntaweb.Services.VolunteeringService;

@Controller
public class VolunteeringController {

	@Autowired
	private VolunteeringRepository volunteeringRepo;

	@Autowired
	private VolunteeringService volunteeringService;

	@GetMapping("/volunteering/{id}")
	public String prueba(Model model, @PathVariable long id) {
		Volunteering voluntariado = volunteeringRepo.findById(id).get();
		model.addAttribute("voluntariado", voluntariado);
		return "volunteering";
	}
	
	@GetMapping("/hola")
	public String prueba(Model model) {
	
		return "ong-submit-advertisement";
	}
	
	@GetMapping("/hola2")
	public String prueba2(Model model) {
	
		return "volunteering";
	}

//	@GetMapping("/users")
//	public Iterable<User> listUsers() {
//
//		return volunteeringRepo.findAll();
//	}

}