package com.group7.voluntaweb.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private GenericComponent genCompo;

	@RequestMapping("/")
	public String index(Model model) {
		Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof User) {
			User user = (User) genCompo.getLoggedUser();
			Boolean isAdmin = user.getRoles().contains("ROLE_ADMIN");
			helper.setNavbar(model, user, null, isAdmin);
			
		} else if (genCompo.getLoggedUser() instanceof ONG){
			ONG ong = (ONG) genCompo.getLoggedUser();
			helper.setNavbar(model, null, ong, false);
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

		Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof User) {
			User user = (User) genCompo.getLoggedUser();
			Boolean isAdmin = user.getRoles().contains("ROLE_ADMIN");
			helper.setNavbar(model, user, null, isAdmin);
			
		} else if (genCompo.getLoggedUser() instanceof ONG){
			ONG ong = (ONG) genCompo.getLoggedUser();
			helper.setNavbar(model, null, ong, false);
		}
		model.addAttribute("title", "¿Quienes somos?");

		return "aboutUs";
	}
}
