package com.group7.voluntaweb.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

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
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.UsersVolunteerings;
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
	interface CompleteVolunteering extends Volunteering.Basico, Category.Basico {}
	interface CompleteVolunteering2 extends Volunteering.Basico, Volunteering.NGO, ONG.Basico, Category.Basico {}
	interface CompleteVolunteering3 extends Volunteering.Basico{}
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
	public Collection<Volunteering> getVolunteerings() {
		return volunteeringService.findAll();
	}

	//obtain a volunteering
	@GetMapping("/{id}")
	@JsonView(CompleteVolunteering2.class)
	public ResponseEntity<Volunteering> getVolunteeringId(@PathVariable long id) {
		Volunteering volunteering = volunteeringService.findVolunteering(id);
		if(volunteering != null) {
			return new ResponseEntity<Volunteering>(volunteering,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Volunteering>(HttpStatus.NOT_FOUND);
		}
	}
	
	//create a new volunteering
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @JsonView(CompleteVolunteering2.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Volunteering createVolunteering(@RequestBody Volunteering ad) {
 
    	ONG ngo = ongComponent.getLoggedUser();
    	ad.setOng(ngo);
        volunteeringService.save(ad);
        return ad;
    }
		
}