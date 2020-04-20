package com.group7.voluntaweb.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;

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
	private GenericComponent genComponent;

	interface UserDetalle extends User.Basico, User.UsersVol, Volunteering.Basico {
	}

	@JsonView(UserDetalle.class)
	@RequestMapping(value = "/api/users/login", produces = "application/json;charset=UTF-8")
	public ResponseEntity<User> userLogin() {

		if (!genComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User loggedUser = (User) genComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/users/logout", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Boolean> userLogout(HttpSession session) {

		if (!genComponent.isLoggedUser()) {
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
	@RequestMapping(value = "/api/ongs/login", produces = "application/json;charset=UTF-8")
	public ResponseEntity<ONG> ongLogin() {

		if (!genComponent.isLoggedONG()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			ONG loggedUser = (ONG) genComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/ongs/logout", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Boolean> logOut(HttpSession session) {

		if (!genComponent.isLoggedONG()) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

}