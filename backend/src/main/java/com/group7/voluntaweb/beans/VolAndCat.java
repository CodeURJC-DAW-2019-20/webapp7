package com.group7.voluntaweb.beans;

import java.sql.Date;

import javax.persistence.Lob;

public class VolAndCat {

	private Long id;
	private String name;
	private String image;
	private String city;
	private String category;
	private String ong;

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