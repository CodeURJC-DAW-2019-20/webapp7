package com.group7.voluntaweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.Comment;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.CommentRepository;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.UserRepository;

@Controller
public class CommentController {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ONGRepository ongRepo;

	@Autowired
	private UserComponent userComponent;

	@GetMapping("/contact")
	public String contact(Model model) {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();
		User user = userRepo.findByEmail(currentPrincipalName);

		ONG ong = ongRepo.findByEmail(currentPrincipalName);

		Boolean admin_logged = userComponent.isLoggedUser();

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("logged_user", true);
			model.addAttribute("logged", true);
		} else if (ong != null) {
			model.addAttribute("user", ong);
			model.addAttribute("logged_ong", true);
			model.addAttribute("logged", true);
		} else if(admin_logged) {
			model.addAttribute("admin_logged", true);
		} else {
			model.addAttribute("logged", false);
			
		}

		model.addAttribute("title", "Contacta con nosotros");
		return "contact";
	}

	@PostMapping("/new-message")
	public String message(@RequestParam String name, @RequestParam String email, @RequestParam String message) {

		Comment comment = new Comment(name, email, message);

		commentRepo.save(comment);

		return "redirect:/contact";
	}

}
