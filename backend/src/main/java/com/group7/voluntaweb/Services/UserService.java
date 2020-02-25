package com.group7.voluntaweb.Services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Repositories.UserRepository;

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

	public User findUser(long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}

//	public boolean isJoined(long userId, long volunteeringId) {
//		long searchingUserId = userRepository.findJoinedUser(userId, volunteeringId);
//		boolean isThere = searchingUserId != 0;
//		return isThere;
//	}

}