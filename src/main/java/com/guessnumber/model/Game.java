package com.guessnumber.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.guessnumber.constants.GameConstants.*;

/**
 * Model Game 
 * 
 * @author nikhil
 */
public class Game {

	private final static Logger log = LoggerFactory.getLogger(Game.class);

	private static int sequence = 0;

	private int gameNumber;

	private int secretNumber;

	private List<User> players;

	private static Game instance = null;

	private Game() {
	}

	/**
	 * Singleton game 
	 * State of secret number and game number set
	 * @return
	 */
	public static Game getInstance() {
		if (instance == null) {
			synchronized (Game.class) {
				if (instance == null) {
					instance = new Game();
					instance.setSecretNumber();
					instance.setGameNumber(sequence++);
				}
			}
		}
		return instance;
	}

	public void destroyInstance() {
		instance = null;
	}

	private void setSecretNumber(){
		secretNumber = generateRandomNumber();
	}
	
	public int getSecretNumber() {
		return secretNumber;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	private void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	/**
	 * Get all players playing
	 * @return
	 */
	public List<User> getPlayers() {
		if (this.players == null)
			this.players = new ArrayList<User>();
		return players;
	}

	/**
	 * Add user to the game
	 * @param player
	 */
	public void addUser(User player) {
		getPlayers().add(player);
	}

	/**
	 * Generates random number b/w 1 and 100
	 * @return
	 */
	public static int generateRandomNumber() {
		int secretNumber = 0;
		secretNumber = (int) (Math.random() * (UPPER_LIMIT - 1) + 1);
		log.debug("Secret Number is " + secretNumber);
		return secretNumber;
	}

}
