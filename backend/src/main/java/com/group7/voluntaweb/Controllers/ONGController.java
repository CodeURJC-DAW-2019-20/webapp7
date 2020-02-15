package com.group7.voluntaweb.Controllers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Repositories.ONGRepository;

import com.group7.voluntaweb.Services.ONGService;


@Controller
public class ONGController {
	
    @Autowired
    private ONGRepository ongRepo;
    
    @Autowired
    private ONGService ongService;

	// inject titles via application.properties
	@Value("${app.register.title}")
	private String TITLE = "";

	@Value("${app.registerUser.title}")
	private String UTITLE = "";
	
	@Value("${app.registerONG.title}")
	private String OTITLE = "";

	
	@GetMapping("/register-ong") //ONG REGISTER VIEW
	public String registerONG(Map<String, Object> model) {
		model.put("title", OTITLE);

		return "registerONG"; //RETURNS registerONG.mustache
	}
	
	
	
	@PostMapping("/add-ong") //ONG REGISTER ACTION
	public String addOng(@RequestParam String name,@RequestParam String email, @RequestParam String responsible_name, @RequestParam String responsible_surname, @RequestParam String address, @RequestParam String telephone, @RequestParam String postal, String image, String password, Map<String, Object> model) {
		model.put("title", UTITLE);
		
		
		String enc_password = new BCryptPasswordEncoder().encode(password); //ENCRYPT PASSWORD
		
		
		ONG ong = new ONG(name, email, responsible_name, responsible_surname, address, telephone, postal, image, enc_password); //CREATE USER
		
		
		
		this.ongService.save(ong); //INSERT INTO DATABASE
		
		
		
		return "redirect:index"; //REDIRECTS TO INDEX

	}
	
	@GetMapping("/ongs") //ONG LIST
	public Collection<ONG> listOngs() {
	
		return ongService.getAll();
	}
		


}