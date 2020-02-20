package com.group7.voluntaweb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;

public interface VolunteeringRepository extends CrudRepository<Volunteering, Long> {
	
	Volunteering findById(String name);

	
	
	//@Query("SELECT id FROM user_volunteerings WHERE user_volunteerings.user_id = :idUser AND user_volunteerings.volunteering_id = :idVolunteering")
	//Long findIdByUsersVolunteerings(@Param("idUser")int idUser, @Param("idVolunteering")int idVolunteering);
	
	
}