package com.group7.voluntaweb.controllers;

import java.io.IOException;
import java.nio.file.Path;
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

import com.group7.voluntaweb.components.GenericComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
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

	/*
	 * @Autowired private UserRepository userRepo;
	 */

	@Autowired
	private GenericComponent genCompo;

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

		Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof User) {
			User user = (User) genCompo.getLoggedUser();
			Boolean isAdmin = user.getRoles().contains("ROLE_ADMIN");
			helper.setNavbar(model, user, null, isAdmin);
		} else if (genCompo.getLoggedUser() instanceof ONG) {
			ONG ong = (ONG) genCompo.getLoggedUser();
			helper.setNavbar(model, null, ong, false);
		}

		model.addAttribute("title", "ong");
		Iterable<ONG> ngos = ongService.getAll();
		model.addAttribute("ngos", ngos);
		return "ongs";
	}

	@RequestMapping("/ongs/{id}")
	public String ngo(Model model, @PathVariable Long id) {

		Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof User) {
			User user = (User) genCompo.getLoggedUser();
			Boolean isAdmin = user.getRoles().contains("ROLE_ADMIN");
			helper.setNavbar(model, user, null, isAdmin);
		} else if (genCompo.getLoggedUser() instanceof ONG) {
			ONG ong = (ONG) genCompo.getLoggedUser();
			helper.setNavbar(model, null, ong, false);
		}

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
			@RequestParam MultipartFile file0, Map<String, Object> model) throws IOException {

		model.put("title", "Registrar ONG");

		String encPassword = new BCryptPasswordEncoder().encode(password); // ENCRYPT PASSWORD

		ONG ong = new ONG(name, responsiblename, responsiblesurname, address, description, email, postal, "true",
				telephone, encPassword);

		Path path = imgService.saveImage("ong", file0);
		String filePath = path.getFileName().toString();
		ong.setImage(filePath);
		this.ongService.save(ong); // INSERT INTO DATABASE

		return "redirect:/index"; // REDIRECTS TO INDEX

	}

	@GetMapping("/ong-settings")
	public String ongSettings(Model model) {

		Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof ONG) {
			ONG ong = (ONG) genCompo.getLoggedUser();
			helper.setNavbar(model, null, ong, false);

			model.addAttribute("ong", ong);
			model.addAttribute("title", "Configuración");

			return "ONG-settings";
		} else {
			return "redirect:index";
		}
	}

	@PostMapping("/ong-settings-form")
	public String ongSettingsForm(Model model, @RequestParam String name, @RequestParam String responsiblename,
			@RequestParam String responsiblesurname, @RequestParam String address, @RequestParam String description,
			@RequestParam String email, @RequestParam String postal, @RequestParam String telephone,
			@RequestParam MultipartFile file0) throws IOException {

		if (genCompo.getLoggedUser() instanceof ONG) {
			ONG ong = (ONG) this.genCompo.getLoggedUser();

			ong.setName(name);
			ong.setResponsibleName(responsiblename);
			ong.setResponsibleSurname(responsiblesurname);
			ong.setAddress(address);
			ong.setDescription(description);
			ong.setDescription(description);
			ong.setEmail(email);
			ong.setPostal(postal);
			ong.setTelephone(telephone);

			if (file0.getSize() > 5) {
				Path path = imgService.saveImage("ong", file0);
				String filePath = path.getFileName().toString();
				ong.setImage(filePath);
			}
			ong = this.ongRepo.save(ong);

			genCompo.setLoggedUser(ong);

			model.addAttribute("ong", ong);
			model.addAttribute("title", "Configuración");

			return "redirect:ong-settings";
		} else {
			return "redirec:index";
		}

	}

	@RequestMapping("/ong-submit-advertisement")
	public String crearAnuncio(Model model) {

		Helpers helper = new Helpers();
		if (genCompo.getLoggedUser() instanceof ONG) {
			ONG ong = (ONG) this.genCompo.getLoggedUser();

			helper.setNavbar(model, null, ong, false);

			Date fecha = new Date(System.currentTimeMillis());

			List<Category> categories = this.catRepo.findAll();

			Volunteering anuncio = new Volunteering(null, "", categories.get(0), fecha, fecha, "", "", "", "");

			model.addAttribute("anuncio", anuncio);
			model.addAttribute("categories", categories);
			model.addAttribute("user", ong);
			model.addAttribute("title", "Añadir voluntariado");
			return "ong-submit-advertisement";
		} else {
			return "redirect:index";
		}
	}

	@PostMapping("ong-submit-advertisement-form")
	public String subirAnuncio(Model model, @RequestParam String name, @RequestParam String city,
			@RequestParam long categoryid, @RequestParam Date startdate, @RequestParam Date enddate,
			@RequestParam String description, @RequestParam MultipartFile file0, @RequestParam String email)
			throws IOException {

		Category cat = this.catRepo.findById(categoryid);

		Volunteering anuncio = new Volunteering(null, name, cat, startdate, enddate, description, null, city, email);

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();
		ONG ong = ongRepo.findByEmail(currentPrincipalName);
		anuncio.setOng(ong);

		try {
			if (file0.getSize() > 5) {

				Path path = imgService.saveImage("volunteerings", file0);
				String filePath = path.getFileName().toString();
				anuncio.setImage(filePath);

				volService.save(anuncio);
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		List<Volunteering> volunteerings = ong.getVolunteerings();

		volunteerings.add(anuncio);
		ong.setVolunteerings(volunteerings);
		ongService.save(ong);

		genCompo.setLoggedUser(ong);

		model.addAttribute("ong", ong);

		return "redirect:/";
	}

	@RequestMapping("/volunteering-gestion-panel")
	public String accessVolunteerings(Model model) {

//		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = principal.getName();
//		User user = userRepo.findByEmail(currentPrincipalName);

		if (genCompo.getLoggedUser() instanceof ONG) {
			ONG ong = (ONG) genCompo.getLoggedUser();

			SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

			Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities();
			Boolean isAdmin = roles.contains(roleAdmin);

			Helpers helper = new Helpers();
			helper.setNavbar(model, null, ong, isAdmin);

			List<Volunteering> anuncios = ong.getVolunteerings();

			model.addAttribute("anuncios", anuncios);
			model.addAttribute("title", "Mi panel de gestión");

			return "volunteering-gestion-panel";
		} else {
			return "redirect:index";
		}

	}

	@RequestMapping("/ong-edit-advertisement-{id}")
	public String editVolunteerings(Model model, @PathVariable long id) {

		if (genCompo.getLoggedUser() instanceof ONG) {

			ONG ong = (ONG) genCompo.getLoggedUser();

			Volunteering anuncio = this.volRepo.findById(id);

			if (anuncio.getOng().getId().equals(ong.getId())) {

				Helpers helper = new Helpers();

				helper.setNavbar(model, null, ong, false);

				List<Category> cats = this.catRepo.findAll();

				model.addAttribute("anuncio", anuncio);
				model.addAttribute("anuncio", anuncio);
				model.addAttribute("categories", cats);
				model.addAttribute("title", "Editar voluntariado");
				model.addAttribute("user", ong);

				return "ong-submit-advertisement";
			} else {
				return "redirect:/volunteering-gestion-panel";
			}
		} else {
			return "redirect:index";
		}
	}

	@RequestMapping("/ong-remove-advertisement-{id}")
	public String removeVolunteerings(Model model, @PathVariable long id) {

		if (genCompo.getLoggedUser() instanceof ONG) {

			ONG ong = (ONG) genCompo.getLoggedUser();

			Volunteering anuncio = this.volRepo.findById(id);

			if (anuncio.getOng().getId().equals(ong.getId())) {

				this.volRepo.deleteById(id);

				/*
				 * model model.addAttribute("ong", ong);
				 */

				return "redirect:/ong-settings";
			} else {
				return "redirect:/volunteering-gestion-panel";
			}
		} else {
			return "redirect:index";
		}
	}

}