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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.Like;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.UsersVolunteerings;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.CategoryRepository;
import com.group7.voluntaweb.Repositories.LikeRepository;
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
	private CategoryRepository categoryRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LikeRepository likeRepo;

	@GetMapping("/volunteering/{id}")
	public String prueba(Model model, @PathVariable long id) {

		Boolean logged = userComponent.isLoggedUser();
		if (logged) {

			String email = userComponent.getLoggedUser().getEmail();
			User user = userRepo.findByEmail(email);

			Volunteering vol = volunteeringRepo.findById(id);

			model.addAttribute("logged", logged);
			model.addAttribute("user", user);

			User row = volunteeringService.findJoinedUser(id, user.getId());
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

		Long likenumber = likeRepo.countLike(id);
		model.addAttribute("likesnumber", likenumber);

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
		} else {

			volunteeringService.deleteJoin(userId, volunteeringId);

		}
		return "redirect:volunteering/" + volunteeringId;
	}

	@PostMapping("/like")
	public String like(@RequestParam Long volunteering) {
		User user = userComponent.getLoggedUser();
		User usuario = userRepo.findByEmail(user.getEmail());
		Like like = new Like();
		Volunteering vol = volunteeringRepo.findOneById(volunteering);

		like.setUser(usuario);
		like.setVolunteering(vol);
		Set<Like> userLikes = usuario.getLikes();

		// Adding condition
		if (volunteeringService.findLike(vol, usuario) == null) {
			userLikes.add(like);
			user.setLikes(userLikes);
			userRepo.save(user);
		} else {
			userLikes.remove(like);
			user.setLikes(userLikes);
			userRepo.save(user);
			likeRepo.deleteLike(vol, usuario);

		}
		return "redirect:volunteering/" + volunteering;

	}

}