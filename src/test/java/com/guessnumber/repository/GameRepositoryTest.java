package com.guessnumber.repository;

import org.junit.Assert;
import org.junit.Test;

import com.guessnumber.model.Game;

public class GameRepositoryTest {

	GameRepository gameRepository = new GameRepository();

	@Test
	public void startGameTest(){
		Game game = gameRepository.startGame();
		Assert.assertNotNull(game);
	}
	
	@Test
	public void getGameTest(){
		Game game = gameRepository.getGame();
		Assert.assertNotNull(game);
	}
	
	@Test
	public void stopGameTest(){
		gameRepository.stopGame();
		Game game = gameRepository.getGame();
		Assert.assertNull(game);	
	}

	
}
