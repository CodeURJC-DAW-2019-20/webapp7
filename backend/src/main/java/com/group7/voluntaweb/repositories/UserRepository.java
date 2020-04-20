package com.group7.voluntaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.UsersVolunteerings;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByid(long id);

	User findByName(String name);

	User findByEmail(String email);

	@Query("SELECT count(*) as number from User where month(registered_at) = :month")
	int usersPerMonth(@Param("month") int month);

	@Query("SELECT user FROM User user JOIN user.registrations r JOIN r.volunteering volunteering WHERE volunteering.id = :volunteeringId AND user.id = :userId")
	User findUserVolunteering(@Param("volunteeringId") long volunteeringId, @Param("userId") long userId);

	@Query("SELECT r FROM User user JOIN user.registrations r JOIN r.volunteering volunteering WHERE volunteering.id = :volunteeringId AND user.id = :userId")
	UsersVolunteerings findUserVolunteerings(@Param("volunteeringId") long volunteeringId,
			@Param("userId") long userId);

	// @Query("SELECT user FROM User user JOIN user.registrations r JOIN
	// r.volunteering_id volunteering WHERE volunteering_id = :idVolunteering AND
	// user_id =:idUser")
	// long findJoinedUser(@Param("idUser")long idUser, @Param("idVolunteering")long
	// idVolunteering);

	void deleteById(long id);

}