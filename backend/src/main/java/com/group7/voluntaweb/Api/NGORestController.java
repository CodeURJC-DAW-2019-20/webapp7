package com.group7.voluntaweb.Api;

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


/*
 * 
 * Listar ong, conseguir ong, crear ong, actualizar ong y borrar ong.
 */


@RestController
@RequestMapping(value = "/api/ong")
public class NGORestController{
	
	@Autowired
	private ONGRepository ongRepo;
	
	@Autowired
	private VolunteeringRepository volRepo;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<ONG>> getNGOS(){
		
		List<ONG> ngos = this.ongRepo.findAll();
		
		if(ngos != null) {
			return new ResponseEntity<>(ngos,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ONG> getNGO(@PathVariable Long id){
		
		ONG ngo = this.ongRepo.findByid(id);
		
		if(ngo != null) {
			return new ResponseEntity<>(ngo, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ONG createNGO(@RequestBody String name, @RequestBody String responsibleName,
			@RequestBody String responsibleSurname,@RequestBody String address,
			@RequestBody String description, @RequestBody String email, @RequestBody String postal, 
			@RequestBody String image, @RequestBody String telephone, @RequestBody String password) {
		
		ONG ngo = new ONG(name, responsibleName, responsibleSurname, address,description,email,postal,image,telephone,password);
				
		this.ongRepo.save(ngo);
		
		return ngo;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<ONG> getNGOSettings(@RequestBody Long id,@RequestBody String name, @RequestBody String responsibleName,
			@RequestBody String responsibleSurname,@RequestBody String address,
			@RequestBody String description, @RequestBody String email, @RequestBody String postal, 
			@RequestBody String image, @RequestBody String telephone, @RequestBody String password) {
		
		ONG ngo = new ONG(name, responsibleName, responsibleSurname, address,description,email,postal,image,telephone,password);
		ngo.setId(id);
				
		if(this.ongRepo.findByid(ngo.getId())!= null) {
			
			this.ongRepo.save(ngo);
			
			return new ResponseEntity<>(ngo,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<ONG> deleteNGO(@RequestBody Long id){
		
		ONG ngo = this.ongRepo.findByid(id);
		
		if(ngo != null) {
			
			this.ongRepo.delete(ngo);
			
			return new ResponseEntity<>(ngo, HttpStatus.OK);
			
		}
		else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
}