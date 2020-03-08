package com.group7.voluntaweb.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="likes")
public class Like{
	
	public interface Basico{
	}
	
	public interface Users{
	}
	
	public interface Volunteerings{
	}
	
	
	@JsonView(Basico.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonView(Users.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonView(Volunteerings.class)
	@ManyToOne(fetch = FetchType.EAGER)
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
