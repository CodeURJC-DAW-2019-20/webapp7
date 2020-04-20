package com.group7.voluntaweb.components;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;

/**
 * This class is designed to manage the information for the user while he is
 * logged in the service. This object can be used in any other @Component
 * auto-wiring it as usual.
 * 
 * Instances of this class are never sent to the user in any REST endpoint. It
 * can hold sensible information that can not be known in the client.
 * 
 * NOTE: This class is intended to be extended by developer adding new
 * attributes. Current attributes can not be removed because they are used in
 * authentication procedures.
 */

@Component
@SessionScope
public class GenericComponent {

	private ONG ong;
	private User user;

	/*
	 * public ONG getLoggedUser() { return ong; }
	 * 
	 * public User getLoggedUser() { return user; }
	 */

	public Object getLoggedUser() {
		if (this.user != null) {
			return this.user;
		} else if (this.ong != null) {
			return this.ong;
		} else {
			return null;
		}
	}

	public void setLoggedUser(ONG ong) {
		this.ong = ong;
		this.user = null;
	}

	public void setLoggedUser(User user) {
		this.user = user;
		this.ong = null;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}

	public boolean isLoggedONG() {
		return this.ong != null;
	}

	public boolean isONG() {
		return true;
	}

	public boolean isUser() {
		return false;
	}

}
