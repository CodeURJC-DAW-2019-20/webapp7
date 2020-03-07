package com.group7.voluntaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.ONGRepository;



/*
 * Listar ong, conseguir ong, crear ong, actualizar ong y borrar ong.
 */


@RestController
@RequestMapping(value = "/api/ong")
public class ONGRestController{
	
	@Autowired
	private ONGRepository ongRepo;
	
	@Autowired
	private ONGComponent ongCompo;
	
	interface ONGDetalle extends ONG.Basico, ONG.Ads, Volunteering.Basico {
	}
	
	interface ONGSDetalle extends ONG.Basico {
	}
	
	//All ussers
	@JsonView(ONGSDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.GET , produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<ONG>> getNGOS(){
		
		List<ONG> ngos = this.ongRepo.findAll();
		
		if(ngos != null) {
			return new ResponseEntity<>(ngos,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//All ussers
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET , produces = "application/json;charset=UTF-8")
	public ResponseEntity<ONG> getNGO(@PathVariable Long id){
		
		ONG ngo = this.ongRepo.findByid(id);
		
		if(ngo != null) {
			return new ResponseEntity<>(ngo, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

	//Anonymous
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.POST /*, produces = "application/json;charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.CREATED)
	public ONG createNGO(@RequestBody ONG ngo) {
				
		ngo.setPassword(new BCryptPasswordEncoder().encode(ngo.getPassword()));
		
		this.ongRepo.save(ngo);
		
		return ngo;
	}
	
	/*
	 * 
	 * A partir de aquí no funciona
	 * 
	 */
	
	
	//Only logged ngo
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.PUT /*, produces = "application/json;charset=UTF-8"*/)
	public ResponseEntity<ONG> updateNGO(@RequestBody ONG ngo){	
		
		
		//ONG ngo = new ONG(name, responsibleName, responsibleSurname, address,description,email,postal,image,telephone,password);
		
		ngo.setId(this.ongCompo.getLoggedUser().getId());
		
		//ngo.setPassword(new BCryptPasswordEncoder().encode(ngo.getPassword()));
		
		//System.out.println(ngo.getPassword());
		
		this.ongRepo.save(ngo);
		
		if(this.ongRepo.findByid(ngo.getId())!= null) {
			
			this.ongRepo.save(ngo);
			
			return new ResponseEntity<>(ngo,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	//Only logged ngo
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.DELETE , produces = "application/json;charset=UTF-8")
	public ResponseEntity<ONG> deleteNGO(){
		
		ONG ngo = this.ongCompo.getLoggedUser();
		
		if(ngo != null) {
			
			this.ongRepo.delete(ngo);
			
			return new ResponseEntity<>(ngo, HttpStatus.OK);
			
		}
		else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
}