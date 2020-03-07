package com.group7.voluntaweb.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.models.User;

/**
 * This class is used to provide REST endpoints to logIn and logOut to the
 * service. These endpoints are used by Angular SPA client application.
 * 
 * NOTE: This class is not intended to be modified by app developer.
 */
@RestController
public class LoginRestController {

	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ONGComponent ongComponent;
	


	@RequestMapping(value = "/api/user/login", produces = "application/json;charset=UTF-8")
	public ResponseEntity<User> userLogin() {
		
		if (!userComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User loggedUser = userComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/user/logout", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Boolean> userLogout(HttpSession session) {

		if (!userComponent.isLoggedUser()) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}
	
	interface ONGDetalle extends ONG.Basico, ONG.Ads, Volunteering.Basico {
	}
	
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/api/ong/login", produces = "application/json;charset=UTF-8")
	public ResponseEntity<ONG> ongLogin() {
		
		if (!ongComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			ONG loggedUser = ongComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/ong/logout", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Boolean> logOut(HttpSession session) {

		if (!ongComponent.isLoggedUser()) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

}