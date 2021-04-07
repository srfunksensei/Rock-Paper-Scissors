package com.rps.dto;

import java.util.UUID;

/**
 * Object class which represent the game which is played
 * 
 * @author mb
 *
 */
public class Game {
	private final String id;
	private final GameType gameType;
	private Move playerOne, playerTwo;
	
	public Game(final GameType gameType) {
		this.id = UUID.randomUUID().toString();
		this.gameType = gameType;
	}
	
	public String getId() {
		return id;
	}

	public GameType getGameType() {
		return gameType;
	}

	public Move getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(final Move playerOne) {
		this.playerOne = playerOne;
	}

	public Move getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(final Move playerTwo) {
		this.playerTwo = playerTwo;
	}
}
