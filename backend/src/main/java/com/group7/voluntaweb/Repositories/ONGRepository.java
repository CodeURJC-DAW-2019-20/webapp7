package com.group7.voluntaweb.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;

public interface ONGRepository extends JpaRepository<ONG, Long> {
	
	ONG findByName(String name);

	ONG findByid(Long id);

	List<ONG> findAll();

	ONG findByEmail(String username);
	
}