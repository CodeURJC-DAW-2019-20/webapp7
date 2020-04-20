package com.group7.voluntaweb.beans;

public class VolAndCat {

	private Long id;
	private String name;
	private String image;
	private String city;
	private String category;
	private String ong;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOng() {
		return ong;
	}

	public void setOng(String ong) {
		this.ong = ong;
	}

	public VolAndCat(Long id, String name, String image, String city, String category, String ong) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.city = city;
		this.category = category;
		this.ong = ong;
	}

}