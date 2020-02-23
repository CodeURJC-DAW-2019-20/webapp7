package com.group7.voluntaweb.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.Models.Volunteering;

import antlr.collections.List;

public interface VolunteeringRepository extends CrudRepository<Volunteering, Long> {
	
	Volunteering findById(String name);
	
	@Query("SELECT vol, ngo.name from Volunteering vol INNER JOIN VolPerONG vPo ON vol.id = vPo.volunteering_id INNER JOIN ONG ngo ON vPo.ngo_id = ngo.id where vol.category_id = :id")
	Iterable<Volunteering> findByCategory(@Param("id") int id);


	
	
}