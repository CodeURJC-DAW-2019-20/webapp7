package com.group7.voluntaweb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByName(String name);

}