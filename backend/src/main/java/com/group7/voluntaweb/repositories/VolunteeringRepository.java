package com.group7.voluntaweb.repositories;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.group7.voluntaweb.beans.VolAndCat;
import com.group7.voluntaweb.models.Like;
import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;

public interface VolunteeringRepository extends JpaRepository<Volunteering, Long> {

	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE c.id = :c_id")
	Iterable<Volunteering> findByCategory(@Param("c_id") Long cid);

	// @Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id,
	// v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN
	// Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id")
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id")
	Iterable<VolAndCat> findAllVols();

	Volunteering findById(long id);

	@Query("SELECT v FROM Volunteering v WHERE v.id = :id")
	Volunteering findOneById(@Param("id") Long id);

	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE v.name LIKE %:search%")
	Iterable<Volunteering> findByQuery(@Param("search") String search);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM users_volunteerings WHERE user_id = :user_id AND volunteering_id = :volunteering_id", nativeQuery = true)
	void deleteJoin(@Param("user_id") long user_id, @Param("volunteering_id") long volunteering_id);
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(
	 * value="DELETE FROM like WHERE user_id = :user_id AND volunteering_id = :volunteering_id"
	 * , nativeQuery = true) void deleteLike(@Param("user_id") long
	 * user_id, @Param("volunteering_id") long volunteering_id); //
	 * 
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(
	 * value="INSERT INTO like WHERE user_id = :user_id AND volunteering_id = :volunteering_id"
	 * , nativeQuery = true) void addLike(@Param("user_id") long
	 * user_id, @Param("volunteering_id") long volunteering_id);
	 * 
	 * @Query("SELECT like FROM Like like WHERE volunteering.id = :volunteeringId AND user.id = :userId"
	 * ) Like findLike(@Param("volunteeringId") long
	 * volunteeringId, @Param("userId") long userId);
	 * 
	 * Like findLikeById(long id);
	 * 
	 */

	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id INNER JOIN UsersVolunteerings uv ON v.id = uv.volunteering WHERE uv.user = :user")
	Iterable<Volunteering> findMyVolunteerings(@Param("user") User user);
	
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id INNER JOIN Like l ON v.id = l.volunteering WHERE l.user = :user")
	Iterable<Volunteering> findMyLiked(@Param("user") User user);
	
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE v.ong = :ong")
	Iterable<Volunteering> findVolunteeringsByNGO(@Param("ong") ONG ong);
}