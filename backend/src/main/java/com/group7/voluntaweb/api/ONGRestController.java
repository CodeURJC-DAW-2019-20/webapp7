package com.group7.voluntaweb.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.services.ImageService;
import com.group7.voluntaweb.services.ONGService;

/*
 * Listar ong, conseguir ong, crear ong, actualizar ong y borrar ong.
 */

@RestController
@RequestMapping(value = "/api/ongs")
public class ONGRestController {

	@Autowired
	private ONGRepository ongRepo;
	
	

	@Autowired
	private ONGComponent ongCompo;
	@Autowired
	private UserComponent userCompo;
	
	@Autowired
	private ONGService service;
	
	@Autowired
	private ImageService imgService;
	
	@Autowired
	private GenericComponent genCompo ;

	interface ONGDetalle extends ONG.Basico, ONG.Ads, Volunteering.Basico {
	}

	interface ONGSDetalle extends ONG.Basico {
	}

	// All ussers
	@JsonView(ONGSDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<ONG>> getNGOS(@RequestParam(value = "page", required = false) Integer page) {

		Iterable<ONG> ngos;
		if (page != null) {
			ngos = service.ongByPage(page, 5);
		} else {
			ngos = service.ongByPage(0, 5);
		}
		List<ONG> list = StreamSupport.stream(ngos.spliterator(), false).collect(Collectors.toList());
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// All ussers
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<ONG> getNGO(@PathVariable Long id) {

		ONG ngo = this.ongRepo.findByid(id);

		if (ngo != null) {
			return new ResponseEntity<>(ngo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// Anonymous
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.POST /* , produces = "application/json;charset=UTF-8" */)
	@ResponseStatus(HttpStatus.CREATED)
	public ONG createNGO(@RequestBody ONG ngo) {

		ngo.setPassword(new BCryptPasswordEncoder().encode(ngo.getPassword()));

		this.ongRepo.save(ngo);

		return ngo;
	}

	// Only logged ngo
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/", method = RequestMethod.PUT /* , produces = "application/json;charset=UTF-8" */)
	public ResponseEntity<ONG> updateNGO(@RequestBody ONG ngo) {
		
		if (genCompo.getLoggedUser() instanceof ONG) {
			
			ONG loggedNgo = (ONG) this.genCompo.getLoggedUser();
			
			ngo.setId(loggedNgo.getId());

			if (this.ongRepo.findByid(ngo.getId()) != null) {

				if (ngo.getPassword() == null) {
					ngo.setPassword(this.ongRepo.findByid(loggedNgo.getId()).getPassword());
				} else {
					ngo.setPassword(new BCryptPasswordEncoder().encode(ngo.getPassword()));
				}

				this.ongRepo.save(ngo);

				return new ResponseEntity<>(ngo, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	// Only logged ngo
	@JsonView(ONGDetalle.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<ONG> deleteNGO(@PathVariable Long id) {

		if(this.ongRepo.findByid(id) != null) {
			if (genCompo.getLoggedUser() instanceof ONG) {
				
				ONG ngo = (ONG) this.genCompo.getLoggedUser();
				
				
				if (ngo.getId().equals(id)) {

					this.ongRepo.deleteById(id);
					
					return new ResponseEntity<>(ngo, HttpStatus.OK);
				} else {
					
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					
				}
				
			} else if(genCompo.getLoggedUser() instanceof User){
				
				User user = (User) this.genCompo.getLoggedUser();
				
				if(user.getRoles().contains("ROLE_ADMIN")) {
					ONG deletedNgo = this.ongRepo.findByid(id);
					
					this.ongRepo.deleteById(id);
					
					return new ResponseEntity<>(deletedNgo, HttpStatus.OK);	
				}
				else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			}
			else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Only logged users
	@JsonView(ONGSDetalle.class)
	@PostMapping(value = "/image")
	public ResponseEntity<ONG> uploadImage(@RequestParam MultipartFile file0) throws IOException {

		ONG ngo = (ONG) this.genCompo.getLoggedUser();


		Path path = this.imgService.saveImage("ong", file0);
		String filePath = path.getFileName().toString();
		ngo.setImage(filePath);
		
		this.ongRepo.save(ngo);

		return new ResponseEntity<>(ngo, HttpStatus.OK);
	}

	// Anonymous
	@JsonView(ONGSDetalle.class)
	@GetMapping(value = "/{filename}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable String filename) throws MalformedURLException {

			return this.imgService.createResponseFromImage("ong", filename);
		
	}
}