package com.guessnumber.service;

import com.guessnumber.model.User;

/**
* Creates UserService user CRUD operations 
* @author nikhil
*/
public interface UserService {

	/**
	 * find user
	 * @param userName
	 * @return
	 */
	public User find(String userName);

	/**
	 * modify user
	 * @param user
	 */
	public void  updateUser(User user);
	
	/**
	 * add user 
	 * @param newUser
	 * @return
	 */
	public boolean registerUser(User newUser);
}	
