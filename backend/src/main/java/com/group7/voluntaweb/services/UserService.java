package com.group7.voluntaweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.models.User;
import com.group7.voluntaweb.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findUser(long userId) {
		// TODO Auto-generated method stub
		return userRepository.findByid(userId);
	}

	public void deleteCount(User user) {
		userRepository.deleteById(user.getId());
	}

	private boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public void deleteById(Long id) throws Exception {
		if (!existsById(id)) {
			throw new Exception("Cannot find User with id: " + id);
		} else {
			userRepository.deleteById(id);
		}
	}

	public User findJoinedUser(Long volunteeringId, Long userId) {
		return userRepository.findUserVolunteering(volunteeringId, userId);
	}
	
	public Iterable<User> userByPage(int pageNumber, int pageSize){
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
		Iterable<User> list = userRepository.findAll(pageRequest);
		return list;
	}

//	public boolean isJoined(long userId, long volunteeringId) {
//		long searchingUserId = userRepository.findJoinedUser(userId, volunteeringId);
//		boolean isThere = searchingUserId != 0;
//		return isThere;
//	}

}