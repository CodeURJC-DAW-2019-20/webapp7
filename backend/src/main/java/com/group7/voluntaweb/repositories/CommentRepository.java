package com.group7.voluntaweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.models.Comment;
import com.group7.voluntaweb.models.ONG;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAll();
}
