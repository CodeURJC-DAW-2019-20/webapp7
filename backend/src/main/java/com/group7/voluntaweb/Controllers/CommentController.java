package com.group7.voluntaweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Models.Comment;
import com.group7.voluntaweb.Repositories.CommentRepository;

@Controller
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@GetMapping("/contact")
	public String contact(Model model) {
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
