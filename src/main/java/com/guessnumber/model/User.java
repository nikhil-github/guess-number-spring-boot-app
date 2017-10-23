package com.guessnumber.model;

/**
 * Model User 
 * 
 * @author nikhil
 */

public class User {

	private String userName;

	private int points;

	public User() {	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPoints() {
		return points;
	}

	/**
	 * add points to user
	 * @param points
	 */
	public void addPoints(int points) {
		this.points += points;
	}

	/**
	 * subtract points from user
	 * @param points
	 */
	public void deductPoints(int points) {
		this.points -= points;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		User guest = (User) obj;
		return guest.getUserName().equalsIgnoreCase(this.userName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
}
