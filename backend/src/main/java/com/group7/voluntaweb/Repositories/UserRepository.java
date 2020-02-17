package com.group7.voluntaweb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.group7.voluntaweb.Models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByName(String name);
	User findByEmail(String email);

}