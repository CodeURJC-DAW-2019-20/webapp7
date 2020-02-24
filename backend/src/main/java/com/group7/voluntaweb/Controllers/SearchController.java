package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Models.Category;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.CategoryRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;
import com.group7.voluntaweb.beans.VolAndCat;

import antlr.collections.List;

@Controller
public class SearchController {
	
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private VolunteeringRepository volRepo;
	
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
		ArrayList<Category> categories = categoryRepo.findAll();
		Iterable<Volunteering> volunteerings = volRepo.findByCategory(category);
		model.addAttribute("volunteeringscat", volunteerings);
		

		model.addAttribute("title", "Resultados de búsqueda");
		model.addAttribute("categories", categories);
		
		
		return "search";
	}

@GetMapping("/search")

public String search(Model model, @RequestParam(required = false) String search, @RequestParam(required = false) Long category) {
	if (search != null) {
		ArrayList<Category> categories = categoryRepo.findAll();
		Iterable<Volunteering> volunteerings = volRepo.findByQuery(search);
		model.addAttribute("volunteeringscat", volunteerings);
		model.addAttribute("search", search);
		

		model.addAttribute("title", "Resultados de búsqueda");
		model.addAttribute("categories", categories);
	} else {
		if(category == null) {
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
