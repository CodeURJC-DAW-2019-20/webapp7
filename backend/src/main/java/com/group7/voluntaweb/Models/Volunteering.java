package com.group7.voluntaweb.Models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "volunteerings")
public class Volunteering {
	
	@OneToMany(mappedBy = "volunteering")
	private Set<VolPerONG> volsPerOng;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)

	private String name;


	@ManyToOne
	private Category category;
	

	private Date startdate;

	private Date enddate;

	@Lob
	private String description;
	private String image;
	private String city;
	

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

	public Volunteering() {
	}

	public Volunteering(String name, Date startdate, Date enddate, String description, String image,
			String city) {
		super();
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.image = image;
		this.city = city;
	}

}