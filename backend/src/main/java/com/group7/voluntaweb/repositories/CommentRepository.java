package com.group7.voluntaweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAll();

	@Query("SELECT c FROM Comment c WHERE id = :id")
	Comment findSpecificComment(@Param("id") Long id);
}
