package com.guessnumber.client;

import static com.guessnumber.constants.GameConstants.COMPUTER;
import static com.guessnumber.constants.GameConstants.UPPER_LIMIT;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.guessnumber.controller.GameController;
import com.guessnumber.datatransfer.Guess;
import com.guessnumber.datatransfer.Response;
import com.guessnumber.model.Game;
import com.guessnumber.service.UserService;


/**
 * Create Computer Client who auto register and play the game 
 * @author nikhil
 *
 */
@Component
public class ComputerClient {

	private static final Logger log = LoggerFactory.getLogger(GameController.class);

	@Value("${game.get.uri}")
	private String getGameURI = "http://localhost:8080/api/game/";

	@Value("${guess.post.uri}")
	private String postGuessURI = "http://localhost:8080/api/guess/";

	@Autowired
	UserService userService;

	/**
	 * Scheduled to run at every fixedRate
	 */
	@Scheduled(fixedRate = 10000)
	private void runner() {
		Game game = getGame();
		if (null != game) {
			makeGuess(COMPUTER, randomNumber(), game.getGameNumber());
		}
	}
	
	/**
	 * Guess a secret number
	 * @return
	 */
	public static int randomNumber() {
		Random rn = new Random();
		return rn.nextInt(UPPER_LIMIT) + 1;
	}

	/**
	 * spring resttemplate to consume end point to get game
	 * @return
	 */
	public Game getGame() {

		Game game = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			game = restTemplate.getForObject(getGameURI, Game.class);
			if (game != null) {
				log.info("computer playing game number" + game.getGameNumber());
			} else {
				log.info("waiting registration-Game ON ");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return game;

	}

	/**
	 * Post the guess - spring resttemplate
	 * @param name
	 * @param guessNumber
	 * @param gameNumber
	 */
	private void makeGuess(String name, int guessNumber, int gameNumber) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			Guess guess = new Guess();
			guess.setGameNumber(gameNumber);
			guess.setUserName(name);
			guess.setGuessNumber(guessNumber);
			Response res = restTemplate.postForObject(postGuessURI, guess,Response.class);
			log.info("computer player message  : " +  res.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}
