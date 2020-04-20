package com.group7.voluntaweb.components;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.group7.voluntaweb.models.ONG;

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
public class ONGComponent {

	private ONG ong;

	public ONG getLoggedUser() {
		return ong;
	}

	public void setLoggedUser(ONG ong) {
		this.ong = ong;
	}

	public boolean isLoggedUser() {
		return this.ong != null;
	}

	public boolean isONG() {
		return true;
	}

	public boolean isUser() {
		return false;
	}

}
