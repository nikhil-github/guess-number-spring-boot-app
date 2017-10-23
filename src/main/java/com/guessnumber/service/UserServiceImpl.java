package com.guessnumber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guessnumber.model.User;
import com.guessnumber.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UsersRepository userRepository;
	
	public User find(String userName){
		return userRepository.find(userName);
	}

	public void  updateUser(User user){
		userRepository.update(user);
	}
	
	public boolean registerUser(User newUser){
		return userRepository.registerUser(newUser);
	}

}
