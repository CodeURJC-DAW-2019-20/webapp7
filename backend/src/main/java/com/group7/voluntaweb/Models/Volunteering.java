package com.group7.voluntaweb.Models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "volunteerings")
public class Volunteering {
	@OneToMany(mappedBy = "volunteering")
	private Set<Join> joined_users;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String name;

	@NotEmpty
	private String category;
	@NotEmpty
	private Date startDate;
	@NotEmpty
	private Date endDate;
	@NotEmpty
	@Lob
	private String description;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Set<Join> getJoined_users() {
		return joined_users;
	}

	public void setJoined_users(Set<Join> joined_users) {
		this.joined_users = joined_users;
	}

	public Volunteering() {
	}

	public Volunteering(Set<Join> joined_users, @NotEmpty String name, @NotEmpty String category,
			@NotEmpty Date startDate, @NotEmpty Date endDate, @NotEmpty String description, @NotEmpty String image) {
		super();
		this.joined_users = joined_users;
		this.name = name;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.image = image;
	}

}