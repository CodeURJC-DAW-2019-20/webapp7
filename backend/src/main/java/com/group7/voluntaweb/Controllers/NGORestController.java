package com.group7.voluntaweb.Controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;


@RestController
public class NGORestController{
	
	@Autowired
	private ONGRepository ongRepo;
	
	@Autowired
	private VolunteeringRepository volRepo;
	
	
	/*@RequestMapping(value = "/register-ong", method = RequestMethod.GET) // ONG REGISTER VIEW
	public String registerONG() {
		model.put("title", "Registrar ONG");

		return "registerONG"; // RETURNS registerONG.mustache
	}*/
	
	@RequestMapping(value = "/ongs", method = RequestMethod.GET)
	public ResponseEntity<List<ONG>> getNGOS(){
		
		List<ONG> ngos = this.ongRepo.findAll();
		
		if(ngos != null) {
			return new ResponseEntity<>(ngos,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/ongs/{id}", method = RequestMethod.GET)
	public ResponseEntity<ONG> getNGO(@PathVariable Long id){
		
		ONG ngo = this.ongRepo.findByid(id);
		
		if(ngo != null) {
			return new ResponseEntity<>(ngo, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(value = "/add-ong", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ONG createNGO(@RequestBody ONG ngo) {
		
		this.ongRepo.save(ngo);
		
		return ngo;
	}
	
	
	/*@RequestMapping(value = "/ong-settings" method = RequestMethod.GET)
	public ResponseEntity<ONG> getNGOSettings(){
		
	}*/
	
	
	@RequestMapping(value = "/ong-settings-form", method = RequestMethod.PUT)
	public ResponseEntity<ONG> getNGOSettings(@RequestBody ONG ngo) {
		
		if(this.ongRepo.findByid(ngo.getId())!= null) {
			
			this.ongRepo.save(ngo);
			
			return new ResponseEntity<>(ngo,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//@RequestMapping(value = "/ong-submit-advertisement", method = RequestMethod.GET)
	
	
	@RequestMapping(value = "/ong-submit-advertisement-form", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Volunteering createVolunteering(@RequestBody Volunteering volunteering) {
		
		this.volRepo.save(volunteering);
		
		return volunteering;
	}
	
	//	@RequestMapping("/volunteering-gestion-panel")
	
	
	/*
	 * 
	 * Faltan dos metodos
	 * 
	 */
	
	
}