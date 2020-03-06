package com.group7.voluntaweb.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "volunteerings")
public class Volunteering {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "volunteering")
	private Set<UsersVolunteerings> joined_users;

	@OneToMany(mappedBy = "volunteering")
	private Set<Like> likes;

	//@NotEmpty
	private String name;

	//@NotEmpty
	@ManyToOne
	private Category category;
	//@NotEmpty
	private Date startdate;
	// @NotEmpty

	private Date enddate;
	//@NotEmpty
	@Lob
	private String description;
	//@NotEmpty
	private String image;
	//@NotEmpty
	private String city;

	//@NotEmpty
	@ManyToOne
	private ONG ong;
	//@NotEmpty
	private String email;


	public Volunteering(Set<UsersVolunteerings> joined_users, @NotEmpty String name, @NotEmpty Category category,
			@NotEmpty Date startdate, @NotEmpty Date enddate, @NotEmpty String description, @NotEmpty String image,
			@NotEmpty String city, @NotEmpty String email) {
		super();

		this.name = name;
		this.category = category;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.city = city;
		this.email = email;
		this.image = image;
		this.joined_users = null;
		this.likes = null;
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Set<Like> getLikes() {
		return likes;
	}

	public void setLikes(Set<Like> likes) {
		this.likes = likes;
	}

	public Volunteering() {
	}

	public Volunteering(String name, Date startdate, Date enddate, String description, String image, String city) {
		super();
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.image = image;
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public ONG getOng() {
		return ong;
	}

	public void setOng(ONG ong) {
		this.ong = ong;
	}


}