package com.group7.voluntaweb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.ONG;

public interface ONGRepository extends JpaRepository<ONG, Long> {
	
	ONG findByName(String name);
	
	

}