package com.guessnumber.repository;

import org.springframework.stereotype.Repository;

import com.guessnumber.model.Game;

/**
* Creates GameRepository for Game
* static reference to the existing game 
* start game create a singleton game 
* @author nikhil
*/
@Repository
public class GameRepository {
	
	public static Game game = null;
	
	/**
	 * Create a singleton game 
	 * @return
	 */
	public Game startGame(){
		game = Game.getInstance();
		return game;
	}
	
	/**
	 * Get current game
	 * @return
	 */
	public Game getGame(){
		return game;
	}
	
	/**
	 * Destroy singleton instance
	 */
	public void stopGame(){
		getGame().destroyInstance();
		game= null;
	}
}
