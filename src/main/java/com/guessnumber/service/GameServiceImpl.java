package com.guessnumber.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guessnumber.model.Game;
import com.guessnumber.model.User;
import com.guessnumber.repository.GameRepository;
import com.guessnumber.repository.UsersRepository;

import static com.guessnumber.constants.GameConstants.*;

@Service
public class GameServiceImpl implements GameService {

	private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
	
	@Autowired
	UsersRepository userRepository;

	@Autowired
	GameRepository gameRepository;

	public Game startGame() {
		Game game = gameRepository.startGame();
		return game;
	}

	public Game getGame() {
		return gameRepository.getGame();
	}

	public void stopGame() {
		 gameRepository.stopGame();
	}

	private void restartGame(){
		stopGame();
		startGame();
	}
	
	public String verifyGuess(User player, int secretNumber, int guessNumber) {

		String message = null;

		if (guessNumber == secretNumber) {
			restartGame();
			message = RIGHTGUESS + guessNumber	+ RIGHTGUESS1;
			player.addPoints(guessNumber);
		} else if (guessNumber < secretNumber) {
			message = SMALLER;
			player.deductPoints(penalty_Points);
		} else if (guessNumber > secretNumber) {
			message = GREATER;
			player.deductPoints(penalty_Points);
		}

		log.info(message);
		userRepository.update(player);

		return message;
	}

}
