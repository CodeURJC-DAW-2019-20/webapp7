package com.group7.voluntaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.models.Comment;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CommentRepository;
import com.group7.voluntaweb.services.ONGService;
import com.group7.voluntaweb.services.UserService;
import com.group7.voluntaweb.services.VolunteeringService;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

	@Autowired
	private VolunteeringService volService;

	@Autowired
	private UserService userService;

	@Autowired
	private ONGService ongService;

	@Autowired
	private CommentRepository comments;

	interface ONGDetail extends ONG.Basico {
	}

	interface VolunteeringDetail extends Volunteering.Basico {
	}

	interface UserDetail extends User.Basico {
	}

	@JsonView(VolunteeringDetail.class)
	@GetMapping(value = "/volunteerings", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<Volunteering>> adminVolunteerings() {
		List<Volunteering> list = volService.findAll();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(UserDetail.class)
	@GetMapping(value = "/users", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<User>> adminUsers() {
		List<User> users = userService.findAll();
		if (!users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(ONGDetail.class)
	@GetMapping(value = "/ngos", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<ONG>> adminNgos() {
		List<ONG> ngos = ongService.findAll();
		if (!ngos.isEmpty()) {
			return new ResponseEntity<>(ngos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/comments", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<Comment>> adminComments() {
		List<Comment> list = comments.findAll();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
