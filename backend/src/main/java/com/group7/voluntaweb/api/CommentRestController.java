package com.group7.voluntaweb.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.Comment;
import com.group7.voluntaweb.services.CommentService;
import com.group7.voluntaweb.services.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
	@Autowired
	private CommentService comService;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public Collection<Comment> getAll() {

		return comService.getAllComments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comment> getOneComment(@PathVariable Long id) {
		Comment commentToFind = comService.findComment(id);
		if (commentToFind != null) {
			return new ResponseEntity<>(commentToFind, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Comment saveComment(@RequestBody Comment comment) {

		comService.saveCommentary(comment);
		return comment;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
		if (userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")) {
			Comment commentToDelete = comService.findComment(id);
			if (commentToDelete != null) {
				comService.deleteComment(id);
				return new ResponseEntity<>(commentToDelete, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
