package com.group7.voluntaweb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.Models.ONG;
import com.group7.voluntaweb.Repositories.ONGRepository;

@Service
public class ONGService {

	@Autowired
	private ONGRepository ongRepository;

	public void save(ONG user) {
		ongRepository.save(user);
	}

	public Iterable<ONG> getAll() {
		return ongRepository.findAll();
	}

}