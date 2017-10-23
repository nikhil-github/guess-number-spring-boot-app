package com.guessnumber.service;

import static com.guessnumber.constants.GameConstants.GREATER;
import static com.guessnumber.constants.GameConstants.RIGHTGUESS;
import static com.guessnumber.constants.GameConstants.RIGHTGUESS1;
import static com.guessnumber.constants.GameConstants.SMALLER;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.guessnumber.model.Game;
import com.guessnumber.model.User;
import com.guessnumber.repository.GameRepository;
import com.guessnumber.repository.UsersRepository;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

	@InjectMocks
	private GameServiceImpl gameService;
	
	@Mock
	public UsersRepository userRepository;

	@Mock
	public GameRepository gameRepository;
	
	@Test
	public void startGameTest(){
		Game game = Game.getInstance();
		when(gameRepository.startGame()).thenReturn(game);
		Assert.assertNotNull(game);
	}

	@Test
	public void verifyGuessTest(){
		User user = new User();
		String result = gameService.verifyGuess(user,10,10);
		Assert.assertEquals(RIGHTGUESS+10+RIGHTGUESS1, result);

		String result1 = gameService.verifyGuess(user,10,9);
		Assert.assertEquals(SMALLER, result1);

		String result2 = gameService.verifyGuess(user,10,11);
		Assert.assertEquals(GREATER, result2);

	}
	
}
