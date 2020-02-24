package com.group7.voluntaweb.Models;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Column;
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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UsersVolunteerings> registrations;

	private String name;

	private String surname;

	@Column(unique = true)
	private String email;


	private String password;
	private String city;
	private String telephone;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


	public Set<UsersVolunteerings> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<UsersVolunteerings> registrations) {
		this.registrations = registrations;
	}

	public User() {
	}

	public User(String name, String surname, String email, String password, String city, String telephone, String image,
			List<String> roles) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.telephone = telephone;
		this.image = image;
		this.roles = roles;

	}


	public User(Set<UsersVolunteerings> registrations, @NotEmpty String name, @NotEmpty String surname,
			@NotEmpty String email, @NotEmpty String password, @NotEmpty String city, @NotEmpty String telephone,
			@NotEmpty List<String> roles, @NotEmpty String image) {
		super();
		this.registrations = registrations;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.telephone = telephone;
		this.roles = roles;
		this.image = image;
	}

	public void addJoinedVolunteering(Volunteering volunteering) {
		UsersVolunteerings join = new UsersVolunteerings();
		join.setUser(this);
		join.setVolunteering(volunteering);
		join.setDate(new Timestamp(new Date().getTime()));
		registrations.add(join);
	}

	public void unjoinVolunteering(Volunteering volunteering) {
		UsersVolunteerings join = new UsersVolunteerings();
		for (UsersVolunteerings a : registrations) {
			if (a.getUser().equals(this) && a.getVolunteering().equals(volunteering)) {
				join = a;
			}
		}
		registrations.remove(join);
	}

}

