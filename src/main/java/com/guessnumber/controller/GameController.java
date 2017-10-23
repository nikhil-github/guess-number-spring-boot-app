package com.guessnumber.controller;

import static com.guessnumber.constants.GameConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guessnumber.datatransfer.Guess;
import com.guessnumber.datatransfer.Response;
import com.guessnumber.datatransfer.Users;
import com.guessnumber.exception.DuplicateUserException;
import com.guessnumber.exception.UserNotFoundException;
import com.guessnumber.model.Game;
import com.guessnumber.model.User;
import com.guessnumber.service.GameService;
import com.guessnumber.service.UserService;

/**
* Creates GameController that get the game player, active players , game details 
* Register user for the game , verify the user guess
* @author nikhil
*/
@RestController
public class GameController {

	private static final Logger log = LoggerFactory.getLogger(GameController.class);

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;

	/**
	 * Method returns all registered users
	 * @return Users 
	 */
	@RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> getRegisteredUser() {

		log.info("Getting List of Registered User");

		List<User> users = new ArrayList<>();

		Game game = gameService.getGame();
		if (game != null) {
			users = game.getPlayers();
		}

		Users players = new Users();
		players.setUsers(users);

		return new ResponseEntity<Users>(players, HttpStatus.OK);
	}

	/**
	 * Method returns game details - there will be only one game at a time
	 * @return Game 
	 */
	@RequestMapping(value = "/api/game", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> getGame() {
		Game game = gameService.getGame();
		return new ResponseEntity<Game>(game, HttpStatus.OK);
	}
	


	/**
	 * Method returns user from user name throws UserNotFoundException if user not found
	 * 
	 * @param userName
	 * @return
	 * @throws  
	 */
	@RequestMapping(value = "/api/user", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@RequestParam("userName") String userName) throws UserNotFoundException{
		User user = userService.find(userName);
		if (null == user)
			throw new UserNotFoundException(NOTFOUND);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	/**
	 * <h2> Method registers a new user to the game <h2> 
	 * <li> Start a new game if not game is active</li>
	 * <li> Get user and Add user to the a game</li>
	 * <li> Add 100 balance points </li>
	 * <li> Throws exception if user already registered</li>
	 * @param newUser
	 * @return
	 * @throws DuplicateUserException
	 */
	@RequestMapping(value = "/api/register", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registerUser(@RequestBody User newUser) throws DuplicateUserException {

		boolean isregistered = userService.registerUser(newUser);
		
		if (isregistered) {
			Game game = gameService.getGame();
			User user = userService.find(newUser.getUserName());
			if (null == game) {
				game = gameService.startGame();
				game.addUser(userService.find(COMPUTER));
			}
			game.addUser(user);
			newUser.addPoints(free_Points);
			return new ResponseEntity<Response>(new Response(OK), HttpStatus.OK);
		} else {
			throw new DuplicateUserException(DUPLICATE);
		}
	}

	
	/**
	 * The method  Verify Guessed Number 
	 * Notify user that game already over
	 * @param guess
	 * @return
	 * 
	 */
	@RequestMapping(value = "/api/guess", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> verfiyGuess(@RequestBody Guess guess) {

		Response response = new Response();

		log.info("user submitting" + guess.getUserName());

		int guessNumber = guess.getGuessNumber();
		int gameNumber = guess.getGameNumber();

		User player = userService.find(guess.getUserName());
		if ((null == gameService.getGame())
				|| gameService.getGame().getGameNumber() != gameNumber) {
			response.setMessage(GAMEOVER);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		int secretNumber = gameService.getGame().getSecretNumber();
		String message = gameService.verifyGuess(player, secretNumber,
				guessNumber);
		response.setMessage(message);

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
