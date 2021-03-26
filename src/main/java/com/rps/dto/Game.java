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
	private Move playerOne, playerTwo;
	
	public Game() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
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
