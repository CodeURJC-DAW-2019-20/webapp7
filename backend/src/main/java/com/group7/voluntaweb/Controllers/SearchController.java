package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.Category;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.CategoryRepository;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;
import com.group7.voluntaweb.beans.VolAndCat;

import antlr.collections.List;

@Controller
public class SearchController {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private VolunteeringRepository volRepo;
	@Autowired
	private UserComponent userComponent;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ONGRepository ongRepo;

//	@GetMapping("/search")
//	
//	public String search(Model model) {
//		ArrayList<Category> categories = categoryRepo.findAll();
//		Iterable<VolAndCat> volunteerings = volRepo.findAllVols();
//		model.addAttribute("volunteerings", volunteerings);
//		
//		model.addAttribute("title", "Resultados de búsqueda");
//		model.addAttribute("categories", categories);
//		
//		
//		return "search";
//	}

	@GetMapping("/search/cat")

	public String search(Model model, @RequestParam(required = false) Long category) {

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

		ArrayList<Category> categories = categoryRepo.findAll();
		Iterable<Volunteering> volunteerings = volRepo.findByCategory(category);
		model.addAttribute("volunteeringscat", volunteerings);

		model.addAttribute("title", "Resultados de búsqueda");
		model.addAttribute("categories", categories);

		return "search";
	}

	@GetMapping("/search")

	public String search(Model model, @RequestParam(required = false) String s,
			@RequestParam(required = false) Long category) {
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
		if (s != null) {
			ArrayList<Category> categories = categoryRepo.findAll();
			Iterable<Volunteering> volunteerings = volRepo.findByQuery(s);
			model.addAttribute("volunteeringscat", volunteerings);
			model.addAttribute("search", s);

			model.addAttribute("title", "Resultados de búsqueda");
			model.addAttribute("categories", categories);
		} else {
			if (category == null) {
				ArrayList<Category> categories = categoryRepo.findAll();
				Iterable<VolAndCat> volunteerings = volRepo.findAllVols();
				model.addAttribute("volunteerings", volunteerings);

				model.addAttribute("title", "Resultados de búsqueda");
				model.addAttribute("categories", categories);
			} else {
				ArrayList<Category> categories = categoryRepo.findAll();
				Iterable<Volunteering> volunteerings = volRepo.findByCategory(category);
				model.addAttribute("volunteeringscat", volunteerings);
				model.addAttribute("title", "Resultados de búsqueda");
				model.addAttribute("categories", categories);
			}
		}

		return "search";
	}

}
