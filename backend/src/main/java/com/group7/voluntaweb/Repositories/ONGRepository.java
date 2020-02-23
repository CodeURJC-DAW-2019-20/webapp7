package com.group7.voluntaweb.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.ONG;

public interface ONGRepository extends JpaRepository<ONG, Long> {
	
	ONG findByName(String name);

	ONG findByid(Long id);

	List<ONG> findAll();
	
}