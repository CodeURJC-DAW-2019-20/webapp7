package com.group7.voluntaweb.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.services.ImageService;
import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.Like;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.UsersVolunteerings;
import com.group7.voluntaweb.models.Volunteering;

/*
 * 
 * Listar usuarios, conseguir un usuario por id, crear un usuario, actualizar un usuario y borrar un usuario.
 * 
 */

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserComponent userCompo;
	
	@Autowired
	ImageService imgService;

	interface UserBasico extends com.group7.voluntaweb.models.User.Basico {
	}

	interface UserCompuesto extends com.group7.voluntaweb.models.User.Basico, com.group7.voluntaweb.models.User.Likes,
			com.group7.voluntaweb.models.User.UsersVol, Like.Basico, UsersVolunteerings.Basico {
	}

	// AllUsers
	@JsonView(UserBasico.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {

		List<com.group7.voluntaweb.models.User> users = this.userRepo.findAll();

		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// AllUsers
	@JsonView(UserCompuesto.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable Long id) {

		User user = this.userRepo.findByid(id);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Anonymous
	@JsonView(UserCompuesto.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		user.setRegisteredAt(Date.valueOf(LocalDate.now()));

		this.userRepo.save(user);

		return user;
	}

	// Only logged users
	@JsonView(UserCompuesto.class)
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		if(userCompo.getLoggedUser().getRoles().contains("ROLE_USER")) {
			user.setId(this.userCompo.getLoggedUser().getId());

			if (this.userRepo.findByid(user.getId()) != null) {

				user.setLikes(this.userRepo.findByid(this.userCompo.getLoggedUser().getId()).getLikes());

				user.setRegistrations(this.userRepo.findByid(this.userCompo.getLoggedUser().getId()).getRegistrations());
				
				if(user.getPassword() != null) {
					user.setPassword(this.userRepo.findByid(this.userCompo.getLoggedUser().getId()).getPassword());
				}
				else {
					user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
				}

				this.userRepo.save(user);

				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		
	}

	// Only logged users
	@JsonView(UserCompuesto.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {

		Boolean isUser = userCompo.getLoggedUser() != null;
		User user = userCompo.getLoggedUser();
		User deletedUser = userRepo.findByid(id);
		if (isUser) {
			if ((user.getRoles().contains("ROLE_USER") && user.getId() == id)
					|| user.getRoles().contains("ROLE_ADMIN")) {
				userRepo.delete(deletedUser);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}

		} else {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	//Only logged users
		@JsonView(UserBasico.class)
		@PostMapping(value = "/image")
		public ResponseEntity<com.group7.voluntaweb.models.User> uploadImage(@RequestParam MultipartFile imageFile) throws IOException{
			
			com.group7.voluntaweb.models.User user = this.userCompo.getLoggedUser();
			
			user.setImage("true");
			
			this.imgService.saveImage("user",user.getId(), imageFile);
			
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		
		
		//Anonymous
		@JsonView(UserBasico.class)
		@GetMapping(value = "/{id}/image")
		public ResponseEntity<Object> downloadImage(@PathVariable Long id) throws MalformedURLException{

			com.group7.voluntaweb.models.User user = this.userRepo.findByid(id);
			
			if((user != null) && (user.getImage().equals("true"))) {
								
				return this.imgService.createResponseFromImage("user", user.getId());
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

}