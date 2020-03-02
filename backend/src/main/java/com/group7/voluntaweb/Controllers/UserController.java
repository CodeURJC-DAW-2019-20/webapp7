package com.group7.voluntaweb.Controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group7.voluntaweb.Components.UserComponent;
import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.UserRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;
import com.group7.voluntaweb.Services.ImageService;
import com.group7.voluntaweb.Services.ONGService;
import com.group7.voluntaweb.Services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private VolunteeringRepository volRepo;

	@Autowired
	private ONGRepository ongRepo;

	@Autowired
	private ImageService imgService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ONGService ongService;

	@Autowired
	private UserComponent userComponent;

	@GetMapping("/register")
	public String register(Map<String, Object> model) {
		model.put("title", "Regístrarse");

		return "register";
	}

	@GetMapping("/register-user")
	public String registerUser(Map<String, Object> model) {
		model.put("title", "Registrar usuario");

		return "registerVolunteers";
	}

	@PostMapping("/add-user")
	public String addUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email,
			@RequestParam String city, @RequestParam String telephone, @RequestParam String password,
			Map<String, Object> model) {
		model.put("title", "Registrar usuario");

		String enc_password = new BCryptPasswordEncoder().encode(password);

		List<String> roles = new ArrayList<String>();

		roles.add("ROLE_USER");

		User user = new User(name, surname, email, enc_password, city, telephone, null, roles,
				Date.valueOf(LocalDate.now()));

		this.userService.save(user);

		return "redirect:register";
	}

	@GetMapping("/login")
	public String login(Map<String, Object> model, HttpSession sesion) {
		model.put("title", "Iniciar sesión");

		Boolean logged = userComponent.isLoggedUser();
		User user = userComponent.getLoggedUser();

		model.put("logged", logged);
		model.put("user", user);

		return "login";
	}

	@GetMapping("/settings")
	public String prueba(Model model) {
		Boolean logged = userComponent.isLoggedUser();
		User user = userComponent.getLoggedUser();
		user = userRepo.findByEmail(user.getEmail());

		model.addAttribute("logged", logged);
		model.addAttribute("user", user);
		model.addAttribute("title", "Ajustes");
		return "user-settings";
	}

	@PostMapping("/user-settings-form")
	public String userSettingsForm(Model model, @RequestParam String name, @RequestParam String surname,
			@RequestParam String city, @RequestParam String telephone, @RequestParam String email,
			@RequestParam MultipartFile imagenFile) throws IOException {
		User user = userComponent.getLoggedUser();
		user = userRepo.findByEmail(user.getEmail());
		user.setName(name);
		user.setSurname(surname);
		user.setCity(city);
		user.setTelephone(telephone);
		user.setEmail(email);

		this.userRepo.save(user);
		if (imagenFile.getSize() > 5) {
			imgService.saveImage("user", user.getId(), imagenFile);
		}
			model.addAttribute("user", user);
		return "redirect:settings";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model) {
		User user = userComponent.getLoggedUser();
		userService.deleteCount(user);
		return "redirect/settings";
	}

	@GetMapping("/myvolunteerings")
	public String myvolunteerings(Model model) {
		User usuario = userComponent.getLoggedUser();
		User user = userRepo.findByEmail(usuario.getEmail());
		Iterable<Volunteering> myvolunteerings = volRepo.findMyVolunteerings(user);
		model.addAttribute("title", "Mis Voluntariados");
		model.addAttribute("user", user);
		model.addAttribute("myvolunteerings", myvolunteerings);

		return "myvolunteerings";
	}
	
	@GetMapping("/admin/volunteerings")
	public String volunteerings(Model model) {
		User usuario = userComponent.getLoggedUser();
		User user = userRepo.findByEmail(usuario.getEmail());
		Iterable<Volunteering> volunteerings = volRepo.findAll();
		model.addAttribute("title", "Voluntariados");
		model.addAttribute("user", user);
		model.addAttribute("volunteerings", volunteerings);

		return "adminVolunteerings";
	}
	
	@RequestMapping(value = "/admin/deleteVolunteering", method = RequestMethod.POST)
	public String deleteVolunteering(Model model, @RequestParam long id) {
		volRepo.deleteById(id);
		return "redirect:/admin/volunteerings";
	}
	
	@GetMapping("/admin/users")
	public String adminVolunteerings(Model model) {
		User usuario = userComponent.getLoggedUser();
		User user = userRepo.findByEmail(usuario.getEmail());
		Iterable<User> users = userRepo.findAll();
		model.addAttribute("title", "Usuarios");
		model.addAttribute("user", user);
		model.addAttribute("users", users);

		return "adminUsers";
	}
	
	@RequestMapping(value = "/admin/deleteUser", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam long id) {
		User user = userRepo.findById(id);
		userService.deleteCount(user);
		return "redirect:/admin/users";
	}
		
	@GetMapping("/admin/ngos")
	public String adminNgos(Model model) {
		User usuario = userComponent.getLoggedUser();
		User user = userRepo.findByEmail(usuario.getEmail());
		Iterable<ONG> ngos = ongRepo.findAll();
		model.addAttribute("title", "ONGs");
		model.addAttribute("user", user);
		model.addAttribute("ngos", ngos);

		return "adminNGOS";
	}

	@RequestMapping(value = "/admin/deleteNGO", method = RequestMethod.POST)
	public String deleteNGO(Model model, @RequestParam long id) {
		ongService.deleteCount(id);
		return "redirect:/admin/ngos";
	}
}