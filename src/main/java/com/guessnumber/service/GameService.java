package com.guessnumber.service;

import com.guessnumber.model.Game;
import com.guessnumber.model.User;


/**
* Creates GameService that get current game, start a new game , verify guessed number 
* Register user for the game , verify the user guess
* @author nikhil
*/
public interface GameService {
	

	/**
	 * start a new game
	 * @return
	 */
	public Game startGame();
	

	/**
	 * Get current game 
	 * @return
	 */
	public Game getGame();

	/**
	 * Verify guess of the user comparing secret number
	 * Reduce / add points 
	 * Updates the user points in the user data store
	 * @param player
	 * @param secretNumber
	 * @param guessNumber
	 * @return
	 */
	public String verifyGuess(User player,int secretNumber,int guessNumber);	
}
