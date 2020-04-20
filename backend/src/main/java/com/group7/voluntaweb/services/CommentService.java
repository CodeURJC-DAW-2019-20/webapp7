package com.group7.voluntaweb.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.models.Comment;
import com.group7.voluntaweb.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository comRepo;

	public Collection<Comment> getAllComments() {
		return comRepo.findAll();
	}

	public Comment findComment(Long comment_id) {

		return comRepo.findSpecificComment(comment_id);
	}

	public void saveCommentary(Comment comentary) {

		comRepo.save(comentary);

	}

	public void deleteComment(Long id) {
		comRepo.deleteById(id);

	}

	public Iterable<Comment> commentByPage(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
		Iterable<Comment> list = comRepo.findAll(pageRequest);
		return list;
	}

}
