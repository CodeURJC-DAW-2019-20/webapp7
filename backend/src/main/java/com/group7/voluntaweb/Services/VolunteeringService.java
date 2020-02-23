package com.group7.voluntaweb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;

@Service
public class VolunteeringService {

	@Autowired
	private VolunteeringRepository volunteeringRepository;

	public Volunteering findVolunteering(long id) {
		return volunteeringRepository.findById(id);
	}

}