package com.group7.voluntaweb.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	// inject via application.properties
	@Value("${app.register.title}")
	private String TITLE = "";

	@Value("${app.registerUser.title}")
	private String UTITLE = "";
	
	@Value("${app.registerONG.title}")
	private String OTITLE = "";

	@RequestMapping("/register")
	public String register(Map<String, Object> model) {
		model.put("title", TITLE);

		return "register";
	}
	
	@RequestMapping("/register-ong")
	public String registerONG(Map<String, Object> model) {
		model.put("title", OTITLE);

		return "registerONG";
	}
	
	@RequestMapping("/register-user")
	public String registerUser(Map<String, Object> model) {
		model.put("title", UTITLE);

		return "registerVolunteers";
	}


	// test 5xx errors
	@RequestMapping("/5xx")
	public String ServiceUnavailable() {
		throw new RuntimeException("ABC");
	}

}