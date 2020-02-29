package com.group7.voluntaweb.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.Lob;

import javax.persistence.Table;

@Entity
@Table(name = "ngos")
public class ONG {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@Column(unique = true)
	private String name;

	private String responsible_name;
	private String responsible_surname;

	@Lob
	private String description;


	private String address;

	private String email;
	private String postal;
	private String image;

	
	@ManyToMany(mappedBy="ongs")
	private List<Volunteering> anuncios;
	

	
	public ONG(String name, String responsible_name, String responsible_surname, String address, String description,
			String email, String postal, String image, String telephone, String password) {
		super();
		this.name = name;
		this.responsible_name = responsible_name;
		this.responsible_surname = responsible_surname;
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

	public String getResponsible_name() {
		return responsible_name;
	}

	public void setResponsible_name(String responsible_name) {
		this.responsible_name = responsible_name;
	}

	public String getResponsible_surname() {
		return responsible_surname;
	}

	public void setResponsible_surname(String responsible_surname) {
		this.responsible_surname = responsible_surname;
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
	
	public List<Volunteering> getAnuncios(){
		return this.anuncios;
	}

	
	public void setAnuncios(List<Volunteering> anuncios) {
		this.anuncios = anuncios;
	}



	public ONG() {
	}
}