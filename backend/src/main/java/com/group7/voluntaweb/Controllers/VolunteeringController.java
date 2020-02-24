package com.group7.voluntaweb.Controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.UsersVolunteerings;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;
import com.group7.voluntaweb.Services.UserService;
import com.group7.voluntaweb.Services.VolunteeringService;

@Controller

public class VolunteeringController {
	Date date = new Date();

	@Autowired
	private VolunteeringRepository volunteeringRepo;

	@Autowired
	private VolunteeringService volunteeringService;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/volunteering/{id}")
	public String prueba(Model model, @PathVariable long id) {
		
		Boolean logged = userComponent.isLoggedUser();
		System.out.print(logged);
		
		String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		User user = userRepo.findByEmail(email);

		model.addAttribute("logged", logged);
		model.addAttribute("user", user);
		model.addAttribute("title", "Voluntariado");
		Volunteering advert = volunteeringRepo.findById(id);
		model.addAttribute("advert", advert);
		model.addAttribute("buttonName", "Apuntarse");
		//model.addAttribute("user", 9);
		model.addAttribute("volunteering", id);

		return "volunteering";
	}

	@RequestMapping(value = "/joinToVolunteering", method = RequestMethod.POST)
	public String join(Model model, @ModelAttribute("volunteering") long volunteeringId,
			@ModelAttribute("user") long userId) {

		User user = userService.findUser(userId);
		Volunteering volunteering = volunteeringService.findVolunteering(volunteeringId);
		
		if (volunteeringService.findJoinedUser(volunteeringId, userId) == null) {
			UsersVolunteerings join = new UsersVolunteerings();
			join.setUser(user);
			join.setVolunteering(volunteering);
			join.setDate(new Timestamp(new Date().getTime()));
			
			Set<UsersVolunteerings> x = user.getRegistrations();
			x.add(join);
	
			user.setRegistrations(x);
	
			userService.save(user);	
		}else {
			System.out.println("Ya está añadido");
		}
		return "redirect:volunteering/" + volunteeringId;
	}

}