package com.group7.voluntaweb.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.repositories.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
	@Autowired
	private CategoryRepository catRepo;

	@GetMapping("/")
	public ResponseEntity<Object> getAll() {
		ArrayList<Category> categories = catRepo.findAll();

		if (categories != null) {
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
