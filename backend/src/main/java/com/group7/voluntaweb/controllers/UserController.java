package com.group7.voluntaweb.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group7.voluntaweb.components.ONGComponent;
import com.group7.voluntaweb.components.UserComponent;
import com.group7.voluntaweb.helpers.Helpers;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.Comment;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.CategoryRepository;
import com.group7.voluntaweb.repositories.CommentRepository;
import com.group7.voluntaweb.repositories.ONGRepository;
import com.group7.voluntaweb.repositories.UserRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;
import com.group7.voluntaweb.services.ImageService;
import com.group7.voluntaweb.services.ONGService;
import com.group7.voluntaweb.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private VolunteeringRepository volRepo;
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ONGRepository ongRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ImageService imgService;

	@Autowired
	private UserService userService;

	@Autowired
	private ONGService ongService;

	@Autowired
	private ONGComponent ongComponent;

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

		return "login";
	}

	@GetMapping("/settings")
	public String prueba(Model model) {
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
		model.addAttribute("title", "Ajustes");
		return "user-settings";
	}

	@PostMapping("/user-settings-form")
	public String userSettingsForm(Model model, @RequestParam String name, @RequestParam String surname,
			@RequestParam String city, @RequestParam String telephone, @RequestParam String email,
			@RequestParam MultipartFile imagenFile) throws IOException {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = principal.getName();
		User user = userRepo.findByEmail(currentPrincipalName);

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
		userService.deleteCount(user);
		return "redirect:logout";
	}

	@GetMapping("/myvolunteerings")
	public String myvolunteerings(Model model) {
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
		Iterable<Volunteering> myvolunteerings = volRepo.findMyVolunteerings(user);
		model.addAttribute("title", "Mis Voluntariados");
		model.addAttribute("user", user);
		model.addAttribute("myvolunteerings", myvolunteerings);

		return "myvolunteerings";
	}

	/*
	 * Esto hay que refactorizarlo. Hay una clase igual en indexController
	 */

	@GetMapping("/admin")
	public String admin(Model model) {

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
		model.addAttribute("title", "Bienvenido");
		model.addAttribute("chart", true);
		model.addAttribute("1", userRepo.usersPerMonth(1));
		model.addAttribute("2", userRepo.usersPerMonth(2));
		model.addAttribute("3", userRepo.usersPerMonth(3));
		model.addAttribute("4", userRepo.usersPerMonth(4));
		model.addAttribute("5", userRepo.usersPerMonth(5));
		model.addAttribute("6", userRepo.usersPerMonth(6));
		model.addAttribute("7", userRepo.usersPerMonth(7));
		model.addAttribute("8", userRepo.usersPerMonth(8));
		model.addAttribute("9", userRepo.usersPerMonth(9));
		model.addAttribute("10", userRepo.usersPerMonth(10));
		model.addAttribute("11", userRepo.usersPerMonth(11));
		model.addAttribute("12", userRepo.usersPerMonth(12));

		ArrayList<Category> categories = categoryRepo.findByQuantity(8);

		model.addAttribute("categories", categories);

		return "index";
	}

	@GetMapping("/admin/volunteerings")
	public String volunteerings(Model model) {
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
		Iterable<Volunteering> volunteerings = volRepo.findAll();
		model.addAttribute("title", "Voluntariados");
		model.addAttribute("volunteerings", volunteerings);

		return "adminVolunteerings";
	}

	@RequestMapping(value = "/admin/deleteVolunteering/{id}", method = RequestMethod.POST)
	public String deleteVolunteering(Model model, @PathVariable long id) {
		volRepo.deleteById(id);
		return "redirect:/admin/volunteerings";
	}

	@GetMapping("/admin/users")
	public String adminVolunteerings(Model model) {
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
		Iterable<User> users = userRepo.findAll();
		model.addAttribute("title", "Usuarios");
		model.addAttribute("users", users);

		return "adminUsers";
	}

	@RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.POST)
	public String deleteUser(Model model, @PathVariable long id) {
		User user = userRepo.findByid(id);
		userService.deleteCount(user);
		return "redirect:/admin/users";
	}

	@GetMapping("/admin/ngos")
	public String adminNgos(Model model) {
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
		Iterable<ONG> ngos = ongRepo.findAll();
		model.addAttribute("title", "ONGs");
		model.addAttribute("ngos", ngos);

		return "adminNGOS";
	}

	@RequestMapping(value = "/admin/deleteNGO/{id}", method = RequestMethod.POST)
	public String deleteNGO(Model model, @PathVariable long id) {
		ongService.deleteCount(id);
		return "redirect:/admin/ngos";
	}

	@GetMapping("/admin/comments")
	public String adminComments(Model model) {
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
		Iterable<Comment> comments = commentRepo.findAll();
		model.addAttribute("title", "Comments");

		model.addAttribute("comments", comments);

		return "adminComments";
	}
}