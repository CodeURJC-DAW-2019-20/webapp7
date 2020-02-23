package com.group7.voluntaweb.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group7.voluntaweb.Models.Category;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.CategoryRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;

import antlr.collections.List;

@Controller
public class SearchController {
	
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private VolunteeringRepository volRepo;
	
	@GetMapping("/search")
	
	public String search(Model model) {
		ArrayList<Category> categories = categoryRepo.findAll();
		Iterable<Volunteering> volunteerings = volRepo.findAll();
		model.addAttribute("volunteerings", volunteerings);
		
		System.out.print(volunteerings.getClass().getFields().toString());
		model.addAttribute("title", "Resultados de búsqueda");
		model.addAttribute("categories", categories);
		
		
		return "search";
	}

}
