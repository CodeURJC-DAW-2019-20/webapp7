package com.group7.voluntaweb.Models;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="volunteering_id")
	private Volunteering volunteering;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Volunteering getVolunteering() {
		return volunteering;
	}

	public void setVolunteering(Volunteering volunteering) {
		this.volunteering = volunteering;
	}

	public Like() {
	
	}

	public Like(Long id, User user, Volunteering volunteering) {
		this.id = id;
		this.user = user;
		this.volunteering = volunteering;
	}
	
	
	
}
