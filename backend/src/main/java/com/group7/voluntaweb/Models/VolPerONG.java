package com.group7.voluntaweb.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="ngos_volunteerings")
public class VolPerONG implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ngo_id")
	@NotEmpty
	private ONG ong;
	
	@ManyToOne
	@JoinColumn(name = "volunteering_id")
	@NotEmpty
	private Volunteering volunteering;
	
	
}