package com.group7.voluntaweb.Controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.ONGComponent;
import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.Comment;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.CommentRepository;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.helpers.Helpers;

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
	@Autowired
	private ONGComponent ongComponent;

	@GetMapping("/contact")
	public String contact(Model model) {
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
