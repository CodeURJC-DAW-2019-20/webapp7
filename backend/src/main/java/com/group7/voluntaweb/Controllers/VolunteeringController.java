package com.group7.voluntaweb.Controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/volunteering/{id}")
	public String prueba(Model model, @PathVariable long id) {
		model.addAttribute("title", "Voluntariado");
		Volunteering advert = volunteeringRepo.findById(id);
		model.addAttribute("advert", advert);
		model.addAttribute("buttonName", "Apuntarse");
		//User user = userComponent.getLoggedUser();
		model.addAttribute("user", 52);
		model.addAttribute("volunteering", id);

		return "volunteering";
	}

	@RequestMapping(value = "/joinToVolunteering", method = RequestMethod.POST)
	public String join(Model model, @ModelAttribute("volunteering") long volunteeringId,
			@ModelAttribute("user") long userId) {
		//User user = userComponent.getLoggedUser();
		User user = userService.findUser(userId);
		Volunteering volunteering = volunteeringService.findVolunteering(volunteeringId);
		
		
			UsersVolunteerings join = new UsersVolunteerings();
			join.setUser(user);
			join.setVolunteering(volunteering);
			join.setDate(new Timestamp(new Date().getTime()));
			
			Set<UsersVolunteerings> x = user.getRegistrations();
			x.add(join);
	
			user.setRegistrations(x);
	
			userService.save(user);	
		
		return "redirect:volunteering/" + volunteeringId;
	}
	

}