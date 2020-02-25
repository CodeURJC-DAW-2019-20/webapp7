package com.group7.voluntaweb.Controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group7.voluntaweb.Components.ONGComponent;
import com.group7.voluntaweb.Models.Categories;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.CategoryRepository;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;
import com.group7.voluntaweb.Services.ImageService;
import com.group7.voluntaweb.Services.ONGService;

@Controller
public class ONGController {
	
	//We have to change this by the ong logged
	
	private final long id=2;
	
	//We have to change this by the ong logged
	

	@Autowired
	private ONGRepository ongRepo;
	
	@Autowired
	private VolunteeringRepository volRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ONGComponent ongComponent;

	@Autowired
	private ONGService ongService;
	
	@Autowired
	private ImageService imgService;

	/*@GetMapping("/register-ong") // ONG REGISTER VIEW
	public String registerONG(Map<String, Object> model) {
		model.put("title", "Registrar ONG");

		return "registerONG"; // RETURNS registerONG.mustache
	}
	
	@GetMapping("/ongs")
	public String ngos(Model model) {
		model.addAttribute("title", "ong");
		Iterable<ONG> ngos = ongService.getAll();
		model.addAttribute("ngos", ngos);
		return "ongs";
	}

	@RequestMapping("/ongs/{id}")
	public String ngo(Map<String, Object> model, @PathVariable Long id) {
		ONG ngo = ongRepo.findByid(id);
		model.put("title", ngo.getName());
		model.put("name", ngo.getName());
		model.put("email", ngo.getEmail());
		model.put("telephone", ngo.getTelephone());
		model.put("address", ngo.getAddress());
		model.put("image", ngo.getImage());
		model.put("description", ngo.getDescription());
		return "ong-detail";
	}
	
	@PostMapping("/add-ong") // ONG REGISTER ACTION
	public String addOng(@RequestParam String name, @RequestParam String email, @RequestParam String responsible_name, @RequestParam String responsible_surname, @RequestParam String address, @RequestParam String telephone, @RequestParam String postal, @RequestParam String password, @RequestParam String description, @RequestParam MultipartFile imagenFile, Map<String, Object> model) throws IOException {
		model.put("title", "Registrar ONG");
		
		String enc_password = new BCryptPasswordEncoder().encode(password); // ENCRYPT PASSWORD

		ONG ong = new ONG(name, email, responsible_name, responsible_surname, address, telephone, postal, "true", enc_password, description);
		ong.setImage("/images/ong/image-"+ong.getId()+".jpg");
		//ong.setImage(true);
		this.ongService.save(ong); // INSERT INTO DATABASE
		
		imgService.saveImage("ong", ong.getId(), imagenFile);
		return "redirect:index"; // REDIRECTS TO INDEX

	}
	
	@GetMapping("/login-ong")
	public String login(Map<String, Object> model, HttpSession sesion) {
		model.put("title", "Iniciar sesión");

		Boolean logged = ongComponent.isLoggedUser();
		ONG user = ongComponent.getLoggedUser();

		model.put("logged", logged);
		model.put("user", user);

		return "login";
	}*/
	
	
	
	
	
	
	
	
	@RequestMapping("/ong-settings")
	public String ongSettings(Model model) {
		
		ONG ong = this.ongRepo.findByid(this.id);
		
		model.addAttribute("ong", ong);
		
		
		return "ONG-settings";
	}
	
	@PostMapping("/ong-settings-form")
	public String ongSettingsForm(Model model,@RequestParam String name, @RequestParam String responsible_name, @RequestParam String responsible_surname,
									@RequestParam String address, @RequestParam String description, @RequestParam String email, @RequestParam String image, 
									@RequestParam String password, @RequestParam String postal, @RequestParam String telephone) {
		
		ONG ong = new ONG(name, responsible_name,responsible_surname,address,description,email, postal , image, telephone, password);
		
		ong.setId(this.id);
		
		this.ongRepo.save(ong);
		
		model.addAttribute("ong", ong);
		
		return "ong-settings";
	}
	
	
	@RequestMapping("/ong-submit-advertisement")
	public String crearAnuncio(Model model) {
		
		Date fecha = new Date(System.currentTimeMillis());
		
		Volunteering anuncio = new Volunteering("",(long)1,fecha,fecha,"","","","");
		
		
		List<Categories> categories = this.catRepo.findAll();
		
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("categories", categories);
		
		
		
		
		return "ong-submit-advertisement";
	}
	
	
	@PostMapping("ong-submit-advertisement-form")
	public String subirAnuncio(Model model, @RequestParam String city, @RequestParam String description, @RequestParam String email, @RequestParam Date enddate,@RequestParam String image,@RequestParam String name, @RequestParam Date startdate, @RequestParam long category_id) {
		
		Volunteering anuncio = new Volunteering(name,category_id, startdate, enddate, description, image, city, email);
		
		System.out.println(anuncio.toString());
		
		ONG ong = this.ongRepo.findByid(this.id);
		
		List<Volunteering> anuncios = ong.getAnuncios();
		anuncios.add(anuncio);
		ong.setAnuncios(anuncios);
		
		
		List<ONG> ongs = anuncio.getOngs();
		ongs.add(ong);
		anuncio.setOngs(ongs);
		
		
		
		this.ongRepo.save(ong);
		this.volRepo.save(anuncio);
		
		
		model.addAttribute("ong", ong);
		
		return "ONG-settings";
	}
	
	
	@RequestMapping("/volunteering-gestion-panel")
	public String accessVolunteerings(Model model) {
		
			ONG ong = this.ongRepo.findByid(this.id);
			
			List<Volunteering> anuncios = ong.getAnuncios();
		
			//List<Volunteering> anuncios = this.volRepo.findAll();//we have to change this
			
			model.addAttribute("anuncios", anuncios);
		
		return "volunteering-gestion-panel";
	}
	
	
	@RequestMapping("/ong-edit-advertisement-{id}")
	public String editVolunteerings(Model model,@PathVariable long id) {
		
		Volunteering anuncio = this.volRepo.findById(id);
		
		List<Categories> cats = this.catRepo.findAll();
		
		model.addAttribute("anuncio", anuncio);model.addAttribute("anuncio", anuncio);
		model.addAttribute("categories", cats);
		
		return "ong-submit-advertisement";
	}
	
	
	@RequestMapping("/ong-remove-advertisement-{id}")
	public String removeVolunteerings(Model model,@PathVariable long id) {
		
		/*ONG ong = this.ongRepo.findByid(this.id);
		
		List<Volunteering> anuncios = ong.getAnuncios();*/
		
		this.volRepo.deleteById(id);
		
		ONG ong = this.ongRepo.findByid(this.id);
		model.addAttribute("ong", ong);
		
		return "ONG-settings";
	}
	

}