package com.group7.voluntaweb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Repositories.ONGRepository;
import com.group7.voluntaweb.Repositories.VolunteeringRepository;


@Service
public class VolunteeringService {

	@Autowired
	private VolunteeringRepository volunteeringRepository;

	public void save(ONG user) {
		ongRepository.save(user);
	}
	
	public List<ONG> getAll(){
		return ongRepository.findAll();
	}

}