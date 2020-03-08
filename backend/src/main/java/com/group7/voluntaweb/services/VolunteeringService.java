package com.group7.voluntaweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.models.Like;
import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.models.Volunteering;
import com.group7.voluntaweb.repositories.LikeRepository;
import com.group7.voluntaweb.repositories.VolunteeringRepository;

@Service
public class VolunteeringService {

	@Autowired
	private VolunteeringRepository volunteeringRepository;
	@Autowired
	private LikeRepository likeRepository;

	public Volunteering findVolunteering(long id) {
		return volunteeringRepository.findById(id);
	}

	public void save(Volunteering anuncio) {
		volunteeringRepository.save(anuncio);
	}
	
	public List<Volunteering> findAll() {
		return volunteeringRepository.findAll();
	}

	public Iterable<Volunteering> findByCategory(long cid) {
		return volunteeringRepository.findByCategory(cid);
	}
	
	public Iterable<Volunteering> findByQuery(String search) {
		return volunteeringRepository.findByQuery(search);
	}


	public User findJoinedUser(long volunteeringId, long userId) {
		return volunteeringRepository.findUserVolunteering(volunteeringId, userId);
	}

	public void deleteJoin(long user_id, long volunteering_id) {
		volunteeringRepository.deleteJoin(user_id, volunteering_id);
	}

	public Like findLike(Volunteering volunteering_id, User user_id) {
		return likeRepository.findLike(volunteering_id, user_id);
	}

	public void delete(long id) {
		volunteeringRepository.deleteById(id);
	}

	/*
	 * public void addLike(long volunteeringId, long userId) {
	 * volunteeringRepository.addLike(volunteeringId, userId); }
	 * 
	 * public void deleteLike(long user_id,long volunteering_id) {
	 * volunteeringRepository.deleteLike(user_id, volunteering_id); }
	 */
}