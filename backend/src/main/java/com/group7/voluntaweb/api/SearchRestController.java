package com.group7.voluntaweb.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.models.Category;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.services.VolunteeringService;

@RestController
@RequestMapping("/api")
public class SearchRestController {

	@Autowired
	private VolunteeringService service;

	interface VolunteeringDetalle extends Volunteering.Basico, Volunteering.NGO {
	}

	interface VolunteeringSDetalle extends Volunteering.Basico, Category.Basico, Volunteering.NGO, ONG.Basico {
	}

	@GetMapping(value = "/search", produces = "application/json;charset=UTF-8")
	@JsonView(VolunteeringSDetalle.class)
	public ResponseEntity<List<Volunteering>> search(@RequestParam(value = "page", required = false) Integer page) {
		Iterable<Volunteering> volunteerings;
		if (page != null) {
			volunteerings = service.volunteeringByPage(page, 5);
		} else {
			volunteerings = service.volunteeringByPage(0, 5);
		}
		List<Volunteering> list = StreamSupport.stream(volunteerings.spliterator(), false).collect(Collectors.toList());
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/search/", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<Volunteering>> searchParam(
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "category", required = false) Long category) {

		if (keyword != null) {
			Iterable<Volunteering> volunteerings = service.findByQuery(keyword);
			List<Volunteering> list = StreamSupport.stream(volunteerings.spliterator(), false)
					.collect(Collectors.toList());
			if (!list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else if (category != null) {
			Iterable<Volunteering> volunteerings = service.findByCategory(category);
			List<Volunteering> list = StreamSupport.stream(volunteerings.spliterator(), false)
					.collect(Collectors.toList());
			if (!list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	/*
	 * @JsonView(VolunteeringSDetalle.class)
	 * 
	 * @GetMapping(value = "/search", produces = "application/json;charset=UTF-8")
	 * public ResponseEntity<List<Volunteering>> searchParam(@RequestParam(value =
	 * "search", required = false) String search,
	 * 
	 * @RequestParam(value = "category", required = false) Long category) {
	 * 
	 * if (search != null) { Iterable<Volunteering> volunteerings =
	 * service.findByQuery(search); List<Volunteering> list =
	 * StreamSupport.stream(volunteerings.spliterator(), false)
	 * .collect(Collectors.toList()); if(!list.isEmpty()) { return new
	 * ResponseEntity<>(list,HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } } else if (category != null) {
	 * Iterable<Volunteering> volunteerings = service.findByCategory(category);
	 * List<Volunteering> list = StreamSupport.stream(volunteerings.spliterator(),
	 * false) .collect(Collectors.toList()); if(!list.isEmpty()) { return new
	 * ResponseEntity<>(list,HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } } List<Volunteering> list =
	 * service.findAll(); return new ResponseEntity<>(list,HttpStatus.OK); }
	 */

}
