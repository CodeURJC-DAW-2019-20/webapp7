package com.group7.voluntaweb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Lob;

import javax.persistence.Table;

@Entity
@Table(name = "ngos")
public class ONG {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// @Column(unique = true)
	private String name;

	private String responsibleName;
	private String responsibleSurname;

	@Lob
	private String description;

	private String address;

	private String email;
	private String postal;
	private String image;

	@OneToMany(mappedBy = "ong", fetch = FetchType.EAGER)
	private List<Volunteering> anuncios;

	public ONG(String name, String responsibleName, String responsibleSurname, String address, String description,
			String email, String postal, String image, String telephone, String password) {
		super();
		this.name = name;
		this.responsibleName = responsibleName;
		this.responsibleSurname = responsibleSurname;
		this.address = address;
		this.description = description;
		this.email = email;
		this.postal = postal;
		this.image = image;
		this.telephone = telephone;
		this.password = password;

		this.anuncios = new ArrayList<>();
	}

	private String telephone;

	private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResponsibleName() {
		return responsibleName;
	}
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public void setResponsibleSurname(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getResponsibleSurname() {
		return responsibleSurname;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Volunteering> getVolunteerings() {
		return this.anuncios;
	}

	public void setVolunteerings(List<Volunteering> anuncios) {
		this.anuncios = anuncios;
	}

	public ONG() {
	}
}