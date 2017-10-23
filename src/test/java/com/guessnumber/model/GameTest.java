package com.guessnumber.model;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {
	
	@Test
	public void randomNumberTest(){
		Game game = Game.getInstance();
		Assert.assertTrue(game.getSecretNumber()>0 && game.getSecretNumber() <= 100);
	}

}
