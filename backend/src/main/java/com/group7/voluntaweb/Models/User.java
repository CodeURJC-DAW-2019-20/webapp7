package com.group7.voluntaweb.Models;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	@OneToMany(mappedBy = "user")
	private Set<Join> registrations;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String name;
	@NotEmpty
	private String surname;

	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String city;
	@NotEmpty
	private String telephone;
	
	//@NotEmpty
	//@ElementCollection(fetch = FetchType.EAGER)
	//private List<String> roles;
	@NotEmpty
	private String image;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public List<String> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<String> roles) {
//		this.roles = roles;
//	}

	public User() {
	}

	public Set<Join> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Join> registrations) {
		this.registrations = registrations;
	}

	public User(Set<Join> registrations, @NotEmpty String name, @NotEmpty String surname, @NotEmpty String email,
			@NotEmpty String password, @NotEmpty String city, @NotEmpty String telephone, //@NotEmpty List<String> roles,
			@NotEmpty String image) {
		super();
		this.registrations = registrations;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.telephone = telephone;
		//this.roles = roles;
		this.image = image;
	}

}