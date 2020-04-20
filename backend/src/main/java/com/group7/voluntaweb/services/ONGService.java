package com.group7.voluntaweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.models.ONG;
import com.group7.voluntaweb.repositories.ONGRepository;

@Service
public class ONGService {

	@Autowired
	private ONGRepository ongRepository;

	public void save(ONG user) {
		ongRepository.save(user);
	}

	public void deleteCount(Long id) {
		ongRepository.deleteById(id);
	}

	public Iterable<ONG> getAll() {
		return ongRepository.findAll();
	}

	public List<ONG> findAll() {
		return ongRepository.findAll();
	}

	public Iterable<ONG> ongByPage(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
		Iterable<ONG> list = ongRepository.findAll(pageRequest);
		return list;
	}

}