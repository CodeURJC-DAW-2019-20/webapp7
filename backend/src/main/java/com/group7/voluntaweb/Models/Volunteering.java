package com.group7.voluntaweb.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "volunteerings")
public class Volunteering {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//@NotEmpty
	private String name;

	//@NotEmpty
	private Long category_id;
	//@NotEmpty

	private Date startdate;
	//@NotEmpty

	private Date enddate;
	//@NotEmpty
	//@Lob
	private String description;
	//@NotEmpty
	private String image;
	//@NotEmpty
	private String city;
	//@NotEmpty
	private String email;
	
	
	@ManyToMany
	private List<ONG> ongs;

	public Volunteering() {
	}

	public Volunteering(String name, Long category_id,
			 Date startdate, Date enddate, String description,
			 String city,  String email, String image) {
		super();
		
		this.name = name;
		this.category_id = category_id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.city = city;
		this.email = email;
		
		this.ongs = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategory() {
		return category_id;
	}

	public void setCategory(Long category_id) {
		this.category_id = category_id;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setOngs(List<ONG> ongs){
		this.ongs = ongs;
	}
	
	public List<ONG> getOngs() {
		return this.ongs;
	}
	

}