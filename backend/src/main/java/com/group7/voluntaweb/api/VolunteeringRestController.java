package com.group7.voluntaweb.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.LikeRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;
import com.group7.voluntaweb.services.UserService;
import com.group7.voluntaweb.services.VolunteeringService;

@RestController
@RequestMapping("/api/volunteering")

public class VolunteeringRestController {
	interface CompleteVolunteering extends Volunteering.Basico {}
	Date date = new Date();

	@Autowired
	private VolunteeringService volunteeringService;

	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ONGComponent ongComponent;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ONGRepository ongRepo;

	@Autowired
	private LikeRepository likeRepo;

	
	//volunteering's list
	@GetMapping("/")
	@JsonView(CompleteVolunteering.class)
	public Collection<Volunteering> getBrands() {
		return volunteeringService.findAll();
	}

}