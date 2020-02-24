package com.group7.voluntaweb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;

@Repository("volunteeringRepository")
public interface VolunteeringRepository extends JpaRepository<Volunteering, Long> {

	@Query("SELECT user FROM User user JOIN user.registrations r JOIN r.volunteering volunteering WHERE volunteering.id = :volunteeringId AND user.id = :userId")
	User findUserVolunteering(@Param("volunteeringId") long volunteeringId, @Param("userId") long userId);

	Volunteering findById(long id);

}