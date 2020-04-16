package com.group7.voluntaweb.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.beans.VolAndCat;
import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;

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
	private ONGComponent ongComponent;
	@Autowired
	private GenericComponent genCompo;
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

//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User loggedUser = null;
		ONG loggedNgo = null;
		Boolean isAdmin = false;
		if (this.genCompo.getLoggedUser() instanceof User) {
			loggedUser = (User) this.genCompo.getLoggedUser();
			isAdmin = loggedUser.getRoles().contains("ROLE_ADMIN");
		}else if (this.genCompo.getLoggedUser() instanceof ONG) {
			loggedNgo = (ONG) this.genCompo.getLoggedUser();
		}
		Helpers helper = new Helpers();
		helper.setNavbar(model, loggedUser, loggedNgo, isAdmin);

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
//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User loggedUser = null;
		ONG loggedNgo = null;
		Boolean isAdmin = false;
		if (this.genCompo.getLoggedUser() instanceof User) {
			loggedUser = (User) this.genCompo.getLoggedUser();
			isAdmin = loggedUser.getRoles().contains("ROLE_ADMIN");
		}else if (this.genCompo.getLoggedUser() instanceof ONG) {
			loggedNgo = (ONG) this.genCompo.getLoggedUser();
		}
		Helpers helper = new Helpers();
		helper.setNavbar(model, loggedUser, loggedNgo, isAdmin);
		
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