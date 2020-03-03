package com.group7.voluntaweb.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.Comment;
import com.group7.voluntaweb.Models.ONG;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAll();
}
