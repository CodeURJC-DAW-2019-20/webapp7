package com.group7.voluntaweb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.Models.Like;
import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.LikeRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;

@Service
public class VolunteeringService {

	@Autowired
	private VolunteeringRepository volunteeringRepository;
	@Autowired 
	private LikeRepository likeRepository; 

	public Volunteering findVolunteering(long id) {
		return volunteeringRepository.findById(id);
	}

	public User findJoinedUser(long volunteeringId, long userId) {
		return volunteeringRepository.findUserVolunteering(volunteeringId, userId);
	}
	
	public void deleteJoin(long user_id, long volunteering_id) {
		volunteeringRepository.deleteJoin(user_id, volunteering_id);
	}
	public Like findLike(long volunteering_id, long user_id) {
		return likeRepository.findLike(user_id,volunteering_id);
	}
	/*
	public void addLike(long volunteeringId, long userId) {
		volunteeringRepository.addLike(volunteeringId, userId);
	}
	
	public void deleteLike(long user_id,long volunteering_id) {
		volunteeringRepository.deleteLike(user_id, volunteering_id);
	}
	*/
}