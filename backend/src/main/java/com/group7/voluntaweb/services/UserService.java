package com.group7.voluntaweb.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return userRepository.findById(userId);
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
        }
        else {
            userRepository.deleteById(id);
        }
    }
	

//	public boolean isJoined(long userId, long volunteeringId) {
//		long searchingUserId = userRepository.findJoinedUser(userId, volunteeringId);
//		boolean isThere = searchingUserId != 0;
//		return isThere;
//	}

}