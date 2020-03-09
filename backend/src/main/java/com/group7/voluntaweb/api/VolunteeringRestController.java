package com.group7.voluntaweb.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.Like;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.UsersVolunteerings;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.LikeRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;
import com.group7.voluntaweb.services.ImageService;
import com.group7.voluntaweb.services.UserService;
import com.group7.voluntaweb.services.VolunteeringService;

@RestController
@RequestMapping("/api/volunteerings")

public class VolunteeringRestController {
	interface CompleteVolunteering extends Volunteering.Basico, Category.Basico {
	}

	interface CompleteVolunteering2 extends Volunteering.Basico, Volunteering.NGO, ONG.Basico, Category.Basico {
	}

	interface CompleteVolunteering3 extends Volunteering.Basico {
	}

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
	private VolunteeringRepository volRepo;
	
	@Autowired
	private LikeRepository likeRepo;
	
	@Autowired
	private ImageService imgService;

	// volunteering's list
	@GetMapping("/")
	@JsonView(CompleteVolunteering.class)
	public Collection<Volunteering> getVolunteerings(@RequestParam(value = "page", required = false) Integer page) {
		Iterable<Volunteering> volunteerings;
		if (page != null) {
			volunteerings = volunteeringService.volunteeringByPage(page, 5);
		} else {
			volunteerings = volunteeringService.volunteeringByPage(0, 5);
		}
		List<Volunteering> list = StreamSupport.stream(volunteerings.spliterator(), false).collect(Collectors.toList());
		return list;
	}

	// obtain a volunteering
	@GetMapping("/{id}")
	@JsonView(CompleteVolunteering2.class)
	public ResponseEntity<Volunteering> getVolunteeringId(@PathVariable long id) {

		Volunteering volunteering = volunteeringService.findVolunteering(id);
		if (volunteering != null) {
			return new ResponseEntity<Volunteering>(volunteering, HttpStatus.OK);
		} else {
			return new ResponseEntity<Volunteering>(HttpStatus.NOT_FOUND);
		}
	}

	// create a new volunteering
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@JsonView(CompleteVolunteering2.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Volunteering> createVolunteering(@RequestBody Volunteering ad) {

		if (ongComponent.isLoggedUser() && ongComponent.getLoggedUser().getResponsibleName() != null) {
			ONG ngo = ongComponent.getLoggedUser();
			ad.setOng(ngo);
			Volunteering saved = volunteeringService.save(ad);
			return new ResponseEntity<Volunteering>(saved, HttpStatus.CREATED);

		}

		return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);

	}

	// delete volunteering
	@DeleteMapping("/{id}")
	@JsonView(CompleteVolunteering.class)
	public ResponseEntity<Volunteering> deleteVolunteering(@PathVariable Long id) {
		Boolean isONG = ongComponent.getLoggedUser() != null;
		ONG ong = ongComponent.getLoggedUser();
		User user = userComponent.getLoggedUser();

		Volunteering deletedVolunteering = volunteeringService.findVolunteering(id); // .get()
		if (isONG) {
			if (ong.getId().equals(deletedVolunteering.getOng().getId())) {
				if (deletedVolunteering != null) {
					volunteeringService.delete(id);
					return new ResponseEntity<Volunteering>(deletedVolunteering, HttpStatus.OK);
				} else {
					return new ResponseEntity<Volunteering>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
			}

		} else if (user.getRoles().contains("ROLE_ADMIN")) {
			volunteeringService.delete(id);
			return new ResponseEntity<Volunteering>(deletedVolunteering, HttpStatus.OK);
		} else {
			return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
		}

	}

	// update a volunteering
	@PutMapping("/{id}")
	@JsonView(CompleteVolunteering2.class)
	public ResponseEntity<Volunteering> deleteVolunteering(@PathVariable long id,
			@RequestBody Volunteering updatedVolunteering) {

		if (volunteeringService.findVolunteering(id) != null) {
			updatedVolunteering.setId(id);
			ONG ngo = ongComponent.getLoggedUser();
			if (ngo != null && volunteeringService.findVolunteering(id).getOng().getId().equals(ngo.getId())) {
				updatedVolunteering.setOng(ngo);
				volunteeringService.save(updatedVolunteering);
				return new ResponseEntity<Volunteering>(updatedVolunteering, HttpStatus.OK);

			} else {
				return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<Volunteering>(HttpStatus.NOT_FOUND);
		}

	}

	// joining to a volunteering
	@PostMapping("join/{id}")
	@JsonView(CompleteVolunteering2.class)
	public ResponseEntity<Volunteering> joiningVolunteering(@PathVariable Long id) {

		Boolean isUser = userComponent.getLoggedUser() != null;
		Volunteering vol = volunteeringService.findVolunteering(id);
		User user = userComponent.getLoggedUser();

		if (isUser && !user.getRoles().contains("ROLE_ADMIN")) {
			Set<UsersVolunteerings> registrationsSet = user.getRegistrations();

			User userFound = userService.findJoinedUser(vol.getId(), user.getId());
			if (userFound == null) {
				UsersVolunteerings connect = new UsersVolunteerings();
				connect.setUser(user);
				connect.setVolunteering(vol);
				connect.setDate(new Timestamp(new Date().getTime()));
				registrationsSet.add(connect);
				user.setRegistrations(registrationsSet);
				userService.save(user);
			} else {
				volunteeringService.deleteJoin(user.getId(), vol.getId());

			}

			return new ResponseEntity<Volunteering>(vol, HttpStatus.OK);

		} else if (!isUser || user.getRoles().contains("ROLE_ADMIN")) {

			return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
		}

	}

	// like volunteering
	@PostMapping("/like/{id}")
	@JsonView(CompleteVolunteering2.class)
	public ResponseEntity<Volunteering> likeVolunteering(@PathVariable long id) {

		Boolean isUser = userComponent.getLoggedUser() != null;
		Volunteering vol = volunteeringService.findVolunteering(id);
		User user = userComponent.getLoggedUser();

		if (isUser && !user.getRoles().contains("ROLE_ADMIN")) {

			Like like = new Like();

			like.setUser(user);
			like.setVolunteering(vol);
			Set<Like> userLikes = user.getLikes();

			if (volunteeringService.findLike(vol, user) == null) {
				userLikes.add(like);
				user.setLikes(userLikes);
				userService.save(user);
			} else {
				userLikes.remove(like);
				user.setLikes(userLikes);
				userRepo.save(user);
				likeRepo.deleteLike(vol, user);

			}

			return new ResponseEntity<Volunteering>(vol, HttpStatus.OK);

		} else if (!isUser || user.getRoles().contains("ROLE_ADMIN")) {

			return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<Volunteering>(HttpStatus.UNAUTHORIZED);
		}

	}

	// Only logged users
	@JsonView(CompleteVolunteering3.class)
	@PostMapping(value = "/image/{id}")
	public ResponseEntity<Volunteering> uploadImage(@PathVariable Long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		ONG ngo = this.ongComponent.getLoggedUser();
		
		Volunteering volunteering = this.volRepo.findById((long)id);
		
		if(ngo.getEmail().equals(volunteering.getEmail())) {
			
			volunteering.setImage("true");
			
			this.volRepo.save(volunteering);
			
			this.imgService.saveImage("volunteerings", id, imageFile);
			
			return new ResponseEntity<>(volunteering,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Anonymous
	@JsonView(CompleteVolunteering3.class)
	@GetMapping(value = "/image/{id}")
	public ResponseEntity<Object> downloadImage(@PathVariable Long id) throws MalformedURLException {

		Volunteering volunteering = this.volRepo.findById((long)id);
		
		if(volunteering != null && volunteering.getImage().equals("true")) {
			
			return this.imgService.createResponseFromImage("volunteerings", volunteering.getId());
			
		}
		else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
}