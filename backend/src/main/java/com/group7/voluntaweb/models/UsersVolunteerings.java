package com.group7.voluntaweb.models;

import java.sql.Timestamp;

import javax.persistence.Column;
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
@Table(name = "users_volunteerings")
public class UsersVolunteerings {

	public interface Basico {
	}

	public interface Volunteerings extends Volunteering.Basico {
	}

	public interface Users {
	}

	@JsonView(Basico.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonView(Users.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonView(Volunteerings.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "volunteering_id")
	private Volunteering volunteering;

	@JsonView(Basico.class)
	@Column(nullable = false)
	private Timestamp date;

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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}