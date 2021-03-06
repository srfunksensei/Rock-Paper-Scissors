package com.rps.controller;

import java.net.URI;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rps.RpsApplication;
import com.rps.dto.Game;
import com.rps.dto.GameType;
import com.rps.dto.Move;
import com.rps.dto.Result;
import com.rps.engine.GameEngine;
import com.rps.engine.RandomEnum;
import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.GamePool;

/**
 * 
 * This is the REST controller for {@link RpsApplication}. It handles game
 * creation and the requests to a certain game (get information, play and
 * abort);
 * 
 * @author mb
 *
 */
@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	private final GamePool<String, Game> gamePool;
	
	@Autowired
	public GameController(GamePool<String, Game> gamePool) {
		this.gamePool = gamePool;
	}
	
	/**
	 * Controller method that returns list of possible game types 
	 * Listens to GET /games
	 * 
	 * @return list of all game types available
	 */
	@GetMapping(value = "gameTypes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<GameType> gameTypes() {
		return Arrays.asList(GameType.values());
	}

	/**
	 * Controller method that creates game based on the choice. 
	 * Listens to: POST /games/start
	 * 
	 * @param gameType of the game user selected {@link GameType}
	 * @return {@link HttpHeaders} with path to the created game in the location tag
	 */
	@PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public HttpHeaders startNewGame(@RequestBody final GameType gameType) {
		final Game game = new Game(gameType);

		gamePool.add(game);

		final HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(GameController.class).slash(game.getId()).toUri());
		headers.setLocation(URI.create("http://localhost:8080/games/" + game.getId()));
		return headers;
	}

	/**
	 * Controller method that returns a requested {@link Game} 
	 * Listens to: GET /games/{id}
	 * 
	 * @param id of the requested {@link Game}
	 * @return the requested {@link Game}
	 * @throws GameDoesNotExistException if the requested {@link Game} does not exist
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Game getGame(@PathVariable final String id) throws GameDoesNotExistException {
		return gamePool.get(id);
	}

	/**
	 * Controller method that takes care of game abortion/removal Listens to: DELETE
	 * /games/{id}
	 * 
	 * @param id of the requested {@link Game}
	 */
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abortGame(@PathVariable final String id) {
		gamePool.remove(id);
	}

	/**
	 * Controller method that takes care of the game play. It takes the input from
	 * the user and a computed random value and finds the result for this match.
	 * Listens to: PUT /games/{id}
	 * 
	 * @param id of the requested {@link Game}
	 * @param playerOne is the {@link Move} the user made
	 * @throws GameDoesNotExistException if the requested {@link Game} does not exist
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Result play(@PathVariable final String id, @RequestBody final Move playerOne) throws GameDoesNotExistException {
		Game game = gamePool.get(id);
		if (!GameType.ComputerVsComputer.equals(game.getGameType())) {
			if (playerOne != null) {
				game.setPlayerOne(playerOne);
			} else {
				game.setPlayerOne(RandomEnum.getValue(Move.class));
			}
		}

		final Move playerTwo = RandomEnum.getValue(Move.class);
		game.setPlayerTwo(playerTwo);
		
		return GameEngine.evaluateResult(game);
	}
}
