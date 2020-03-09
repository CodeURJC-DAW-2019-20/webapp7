package com.group7.voluntaweb.controllers;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Like;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.UsersVolunteerings;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.LikeRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;
import com.group7.voluntaweb.services.UserService;
import com.group7.voluntaweb.services.VolunteeringService;

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
	private ONGComponent ongComponent;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ONGRepository ongRepo;

	@Autowired
	private LikeRepository likeRepo;

	@GetMapping("/volunteering/{id}")
	public String prueba(Model model, @PathVariable long id) {

//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User user = userComponent.getLoggedUser();

		ONG ong = ongComponent.getLoggedUser();

		SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Boolean isAdmin = roles.contains(roleAdmin);

		Helpers helper = new Helpers();
		helper.setNavbar(model, user, ong, isAdmin);

		if (user != null) {

			Volunteering vol = volunteeringRepo.findById(id);

			User row = userService.findJoinedUser(id, user.getId());
			if (row != null) {
				model.addAttribute("alert", "¡Te has apuntado a este voluntariado!");
				model.addAttribute("buttonName", "Desapuntarse");
				model.addAttribute("color-button", "danger");
			} else {
				model.addAttribute("color-button", "primary");
				model.addAttribute("buttonName", "Apuntarse");
			}

			if (volunteeringService.findLike(vol, user) == null) {

				model.addAttribute("b-color", "grey2");
				model.addAttribute("i-color", "grey");
			} else {
				model.addAttribute("b-color", "blue2");
				model.addAttribute("i-color", "blue");
			}

		}

		Volunteering advert = volunteeringRepo.findById(id);
		model.addAttribute("title", advert.getName());

		String category = advert.getCategory().getName();
		model.addAttribute("category", category);
		model.addAttribute("advert", advert);

		model.addAttribute("volunteering", id);

		Long likeNumber = likeRepo.countLike(id);
		model.addAttribute("likesnumber", likeNumber);

		return "volunteering";
	}

	@RequestMapping(value = "/joinToVolunteering", method = RequestMethod.POST)
	public String join(Model model, @ModelAttribute("volunteering") long volunteeringId,
			@ModelAttribute("user") long userId) {
		User user = userService.findUser(userId);
		Volunteering volunteering = volunteeringService.findVolunteering(volunteeringId);
		if (userService.findJoinedUser(volunteeringId, userId) == null) {
			UsersVolunteerings join = new UsersVolunteerings();
			join.setUser(user);
			join.setVolunteering(volunteering);
			join.setDate(new Timestamp(new Date().getTime()));

			Set<UsersVolunteerings> x = user.getRegistrations();
			x.add(join);

			user.setRegistrations(x);

			userService.save(user);
		} else {

			volunteeringService.deleteJoin(userId, volunteeringId);

		}
		return "redirect:volunteering/" + volunteeringId;
	}

	@PostMapping("/like")
	public String like(@RequestParam Long volunteering) {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();
		User user = userRepo.findByEmail(currentPrincipalName);

		ONG ong = ongRepo.findByEmail(currentPrincipalName);


		Like like = new Like();
		Volunteering vol = volunteeringRepo.findOneById(volunteering);

		like.setUser(user);
		like.setVolunteering(vol);
		Set<Like> userLikes = user.getLikes();

		// Adding condition
		if (volunteeringService.findLike(vol, user) == null) {
			userLikes.add(like);
			user.setLikes(userLikes);
			userRepo.save(user);
		} else {
			userLikes.remove(like);
			user.setLikes(userLikes);
			userRepo.save(user);
			likeRepo.deleteLike(vol, user);

		}
		return "redirect:volunteering/" + volunteering;

	}

}