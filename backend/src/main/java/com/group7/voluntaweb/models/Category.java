package com.group7.voluntaweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;
import com.group7.voluntaweb.models.Volunteering.Basico;

@Entity

@Table(name="categories")
public class Category {
	
	public interface Basico {
	}
	@JsonView(Basico.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(Basico.class)
	@NotEmpty
	private String name;
	                
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Category() {
		
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

}
