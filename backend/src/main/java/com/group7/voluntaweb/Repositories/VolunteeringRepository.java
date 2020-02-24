package com.group7.voluntaweb.Repositories;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.beans.VolAndCat;

import antlr.collections.List;


public interface VolunteeringRepository extends CrudRepository<Volunteering, Long> {
  
  @Query("SELECT user FROM User user JOIN user.registrations r JOIN r.volunteering volunteering WHERE volunteering.id = :volunteeringId AND user.id = :userId")
	User findUserVolunteering(@Param("volunteeringId") long volunteeringId, @Param("userId") long userId);
	
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE c.id = :c_id")
	Iterable<Volunteering> findByCategory(@Param("c_id") Long cid);
	
    //@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id")
    @Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id")
    Iterable<VolAndCat> findAllVols();
	
	Optional<Volunteering> findById(Long id);
	
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE v.name LIKE %:search%")
	Iterable<Volunteering> findByQuery(@Param("search") String search);
	
	
}