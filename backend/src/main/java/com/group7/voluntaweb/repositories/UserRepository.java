package com.group7.voluntaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);

	User findByName(String name);

	User findByEmail(String email);

	@Query("SELECT count(*) as number from User where month(registered_at) = :month")
	int usersPerMonth(@Param("month") int month);

	// @Query("SELECT user FROM User user JOIN user.registrations r JOIN
	// r.volunteering_id volunteering WHERE volunteering_id = :idVolunteering AND
	// user_id =:idUser")
	// long findJoinedUser(@Param("idUser")long idUser, @Param("idVolunteering")long
	// idVolunteering);

	void deleteById(long id);
	
	

}