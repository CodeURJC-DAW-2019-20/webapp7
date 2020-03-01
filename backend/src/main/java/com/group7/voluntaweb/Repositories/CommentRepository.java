package com.group7.voluntaweb.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;


import com.group7.voluntaweb.Models.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
	

}

