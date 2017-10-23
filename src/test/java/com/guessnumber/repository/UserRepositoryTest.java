package com.guessnumber.repository;

import org.junit.Assert;
import org.junit.Test;

import com.guessnumber.model.User;

public class UserRepositoryTest {

	private final static String NAME = "HUMANUSER";

	private final static int points  = 100;

	
	UsersRepository usersRepository = new UsersRepository();
	
	@Test
	public void registerUserTest(){
		User newUser = new User();
		newUser.setUserName(NAME);
		newUser.addPoints(points);
		Assert.assertTrue(usersRepository.registerUser(newUser));
	}
	


	@Test
	public void updateTest(){
		User modifyUser = new User();
		modifyUser.setUserName(NAME);
		modifyUser.addPoints(1);
		usersRepository.update(modifyUser);
		User usr = usersRepository.find(NAME);
		Assert.assertEquals("Wrong user", 1, usr.getPoints());
	}
	
}
