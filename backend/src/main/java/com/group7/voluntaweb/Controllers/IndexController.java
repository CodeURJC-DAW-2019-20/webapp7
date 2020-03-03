package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.Category;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.CategoryRepository;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ONGRepository ongRepo;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private CategoryRepository categoryRepo;

	@RequestMapping("/")
	public String index(Model model) {

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
		} else if (admin_logged) {
			model.addAttribute("admin_logged", true);
			model.addAttribute("logged", true);
		} else {
			model.addAttribute("logged", false);

		}

		model.addAttribute("title", "Bienvenido");
		model.addAttribute("chart", true);
		model.addAttribute("1", userRepo.usersPerMonth(1));
		model.addAttribute("2", userRepo.usersPerMonth(2));
		model.addAttribute("3", userRepo.usersPerMonth(3));
		model.addAttribute("4", userRepo.usersPerMonth(4));
		model.addAttribute("5", userRepo.usersPerMonth(5));
		model.addAttribute("6", userRepo.usersPerMonth(6));
		model.addAttribute("7", userRepo.usersPerMonth(7));
		model.addAttribute("8", userRepo.usersPerMonth(8));
		model.addAttribute("9", userRepo.usersPerMonth(9));
		model.addAttribute("10", userRepo.usersPerMonth(10));
		model.addAttribute("11", userRepo.usersPerMonth(11));
		model.addAttribute("12", userRepo.usersPerMonth(12));

		ArrayList<Category> categories = categoryRepo.findByQuantity(8);

		model.addAttribute("categories", categories);

		return "index";
	}

	@RequestMapping("/index")
	public String index2(Model model) {
		return "redirect:/";
	}

	@GetMapping("/about-us")
	public String about(Model model) {
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
		} else if (admin_logged) {
			model.addAttribute("admin_logged", true);
			model.addAttribute("logged", true);
		} else {
			model.addAttribute("logged", false);

		}
		model.addAttribute("title", "¿Quienes somos?");

		return "aboutUs";
	}
}
