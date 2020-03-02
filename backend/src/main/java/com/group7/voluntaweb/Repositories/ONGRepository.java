package com.group7.voluntaweb.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Models.User;

public interface ONGRepository extends JpaRepository<ONG, Long> {

	ONG findByName(String name);

	ONG findByid(Long id);

	List<ONG> findAll();

	@Query("SELECT o FROM ONG o WHERE o.email = :username")
	ONG findByEmail(@Param("username") String username);

}