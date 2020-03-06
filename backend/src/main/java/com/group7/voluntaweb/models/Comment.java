package com.group7.voluntaweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

	
	@Entity
	@Table(name="comments")
	public class Comment {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		                
		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		@NotEmpty
		private String name;


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		@NotEmpty
		private String email;
		
		public String getEmail() {
			return email;
		}
		
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		@NotEmpty
		@Lob
		private String message;


		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}
		
		public Comment(String name, String email, String message) {
			this.name = name;
			this.email = email;
			this.message = message;
		}
		
		
		public Comment() {
			
		}
	}


