package com.group7.voluntaweb.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;
import com.group7.voluntaweb.services.ImageService;
import com.group7.voluntaweb.services.ONGService;
import com.group7.voluntaweb.services.VolunteeringService;

@Controller
public class ONGController {

	@Autowired
	private ONGRepository ongRepo;

	@Autowired
	private VolunteeringRepository volRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ONGComponent ongComponent;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private ONGService ongService;

	@Autowired
	private VolunteeringService volService;

	@Autowired
	private ImageService imgService;

	@GetMapping("/register-ong") // ONG REGISTER VIEW
	public String registerONG(Map<String, Object> model) {
		model.put("title", "Registrar ONG");

		return "registerONG"; // RETURNS registerONG.mustache
	}

	@GetMapping("/ongs")
	public String ngos(Model model) {
//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User user = userComponent.getLoggedUser();

		ONG ong = ongComponent.getLoggedUser();

		SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Boolean isAdmin = roles.contains(roleAdmin);

		Helpers helper = new Helpers();
		helper.setNavbar(model, user, ong, isAdmin);

		model.addAttribute("title", "ong");
		Iterable<ONG> ngos = ongService.getAll();
		model.addAttribute("ngos", ngos);
		return "ongs";
	}

	@RequestMapping("/ongs/{id}")
	public String ngo(Model model, @PathVariable Long id) {
//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User user = userComponent.getLoggedUser();

		ONG ong = ongComponent.getLoggedUser();

		SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Boolean isAdmin = roles.contains(roleAdmin);

		Helpers helper = new Helpers();
		helper.setNavbar(model, user, ong, isAdmin);
		ONG ngo = ongRepo.findByid(id);
		model.addAttribute("id", ngo.getId());
		model.addAttribute("title", "ONG");
		model.addAttribute("name", ngo.getName());
		model.addAttribute("email", ngo.getEmail());
		model.addAttribute("telephone", ngo.getTelephone());
		model.addAttribute("address", ngo.getAddress());
		model.addAttribute("image", ngo.getImage());
		model.addAttribute("description", ngo.getDescription());
		return "ong-detail";
	}

	@PostMapping("/add-ong") // ONG REGISTER ACTION
	public String addOng(@RequestParam String name, @RequestParam String email, @RequestParam String responsiblename,
			@RequestParam String responsiblesurname, @RequestParam String address, @RequestParam String telephone,
			@RequestParam String postal, @RequestParam String password, @RequestParam String description,
			@RequestParam MultipartFile imagenFile, Map<String, Object> model) throws IOException {

		model.put("title", "Registrar ONG");

		String encPassword = new BCryptPasswordEncoder().encode(password); // ENCRYPT PASSWORD

		ONG ong = new ONG(name, responsiblename, responsiblesurname, address, description, email, postal, "true",
				telephone, encPassword);

		this.ongService.save(ong); // INSERT INTO DATABASE

		imgService.saveImage("ong", ong.getId(), imagenFile);
		return "redirect:/index"; // REDIRECTS TO INDEX

	}

//	@GetMapping("/login-ong")
//	public String login(Map<String, Object> model, HttpSession sesion) {
//		model.put("title", "Iniciar sesión");
//
//		Boolean logged = ongComponent.isLoggedUser();
//		ONG user = ongComponent.getLoggedUser();
//
//		model.put("logged", logged);
//		model.put("user", user);
//
//		return "login";
//	}

	@GetMapping("/ong-settings")
	public String ongSettings(Model model) {

//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User user = userComponent.getLoggedUser();

		ONG ong = ongComponent.getLoggedUser();

		SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Boolean isAdmin = roles.contains(roleAdmin);

		Helpers helper = new Helpers();
		helper.setNavbar(model, user, ong, isAdmin);

		model.addAttribute("ong", ong);
		model.addAttribute("title", "Configuración");

		return "ONG-settings";
	}

	@PostMapping("/ong-settings-form")
	public String ongSettingsForm(Model model, @RequestParam String name, @RequestParam String responsibleName,
			@RequestParam String responsibleSurname, @RequestParam String address, @RequestParam String description,
			@RequestParam String email, @RequestParam String postal, @RequestParam String telephone,
			@RequestParam MultipartFile imagenFile) throws IOException {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();

		ONG ong = ongRepo.findByEmail(currentPrincipalName);

		ong.setName(name);
		ong.setResponsibleName(responsibleName);
		ong.setResponsibleSurname(responsibleSurname);
		ong.setAddress(address);
		ong.setDescription(description);
		ong.setDescription(description);
		ong.setEmail(email);
		ong.setPostal(postal);
		ong.setTelephone(telephone);

		this.ongRepo.save(ong);
		if (imagenFile.getSize() > 5) {
			imgService.saveImage("ong", ong.getId(), imagenFile);
		}
		model.addAttribute("ong", ong);

		return "ong-settings";
	}

	@RequestMapping("/ong-submit-advertisement")
	public String crearAnuncio(Model model) {

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();
		ONG ong = ongRepo.findByEmail(currentPrincipalName);

		Date fecha = new Date(System.currentTimeMillis());

		List<Category> categories = this.catRepo.findAll();

		Volunteering anuncio = new Volunteering(null, "", categories.get(0), fecha, fecha, "", "", "", "");

		model.addAttribute("anuncio", anuncio);
		model.addAttribute("categories", categories);
		model.addAttribute("user", ong);
		model.addAttribute("title", "Añadir voluntariado");
		return "ong-submit-advertisement";
	}

	@PostMapping("ong-submit-advertisement-form")
	public String subirAnuncio(Model model, @RequestParam String name, @RequestParam String city,
			@RequestParam long categoryId, @RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam String description, @RequestParam MultipartFile imagenFile, @RequestParam String email)
			throws IOException {

		Category cat = this.catRepo.findById(categoryId);

		Volunteering anuncio = new Volunteering(null, name, cat, startDate, endDate, description,
				"/images/volunteerings/", city, email);

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();
		ONG ong = ongRepo.findByEmail(currentPrincipalName);
		anuncio.setOng(ong);
		try {
			volService.save(anuncio);
		} catch (Exception e) {
			// TODO: handle exception

		}
		List<Volunteering> volunteerings = ong.getVolunteerings();

		volunteerings.add(anuncio);
		ong.setVolunteerings(volunteerings);
		ongService.save(ong);
		imgService.saveImage("volunteerings", anuncio.getId(), imagenFile);

		model.addAttribute("ong", ong);

		return "redirect:/";
	}

	@RequestMapping("/volunteering-gestion-panel")
	public String accessVolunteerings(Model model) {

//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User user = userComponent.getLoggedUser();

		ONG ong = ongComponent.getLoggedUser();

		SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Boolean isAdmin = roles.contains(roleAdmin);

		Helpers helper = new Helpers();
		helper.setNavbar(model, user, ong, isAdmin);

		List<Volunteering> anuncios = ong.getVolunteerings();

		// List<Volunteering> anuncios = this.volRepo.findAll();//we have to change this

		model.addAttribute("anuncios", anuncios);
		model.addAttribute("title", "Mi panel de gestión");

		return "volunteering-gestion-panel";
	}

	@RequestMapping("/ong-edit-advertisement-{id}")
	public String editVolunteerings(Model model, @PathVariable long id) {
//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		User user = userComponent.getLoggedUser();

		ONG ong = ongComponent.getLoggedUser();

		SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Boolean isAdmin = roles.contains(roleAdmin);

		Helpers helper = new Helpers();
		helper.setNavbar(model, user, ong, isAdmin);
		Volunteering anuncio = this.volRepo.findById(id);
		if (anuncio.getOng().getId() != ong.getId()) {
			return "redirect:/volunteering-gestion-panel";
		}

		List<Category> cats = this.catRepo.findAll();

		model.addAttribute("anuncio", anuncio);
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("categories", cats);
		model.addAttribute("title", "Editar voluntariado");
		model.addAttribute("user", ong);

		return "ong-submit-advertisement";
	}

	@RequestMapping("/ong-remove-advertisement-{id}")
	public String removeVolunteerings(Model model, @PathVariable long id) {

		/*
		 * ONG ong = this.ongRepo.findByid(this.id);
		 * 
		 * List<Volunteering> anuncios = ong.getAnuncios();
		 */

		this.volRepo.deleteById(id);

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();

		ONG ong = ongRepo.findByEmail(currentPrincipalName);

		model.addAttribute("ong", ong);

		return "ONG-settings";
	}

}