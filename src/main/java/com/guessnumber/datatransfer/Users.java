package com.guessnumber.datatransfer;

import java.util.List;

import com.guessnumber.model.User;

/**
 * Wraps array of JSON users
 * @author nikhil
 *
 */
public class Users {

	public List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
