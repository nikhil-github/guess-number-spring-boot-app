package com.guessnumber.datatransfer;

/**
 * Create Data Transfer Object Guess
 * @author nikhil
 *
 */
public class Guess {
	
	private int guessNumber;
	
	private String userName;
	
	private int gameNumber;
	
	public Guess(){
		
	}

	public int getGuessNumber() {
		return guessNumber;
	}

	public void setGuessNumber(int guessNumber) {
		this.guessNumber = guessNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

}
