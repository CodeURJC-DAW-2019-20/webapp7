package com.group7.voluntaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.UsersVolunteerings;
import com.group7.voluntaweb.models.Volunteering;

public interface UsersVolunteeringsRepository extends JpaRepository<UsersVolunteerings, Long> {

	User findByid(long id);

	void deleteById(long id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM UsersVolunteerings uv WHERE uv.user.id=:userId AND uv.volunteering.id=:volId")
	void deleteJoin(@Param("userId") Long userId, @Param("volId") Long volId);

}