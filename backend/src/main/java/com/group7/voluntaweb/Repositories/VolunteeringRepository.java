package com.group7.voluntaweb.Repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.Models.Volunteering;

import antlr.collections.List;

public interface VolunteeringRepository extends CrudRepository<Volunteering, Long> {
	
	@Query("SELECT vol FROM Volunteering vol INNER JOIN FETCH vol.category")
	Iterable<Volunteering> findAll();
	
	Optional<Volunteering> findById(Long id);
	
	@Query("SELECT vol, ngo.name from Volunteering vol INNER JOIN VolPerONG vPo ON vol.id = vPo.volunteering INNER JOIN ONG ngo ON vPo.ong = ngo.id where vol.category = :id")
	Iterable<Volunteering> findByCategory(@Param("id") Long id);


	
	
}