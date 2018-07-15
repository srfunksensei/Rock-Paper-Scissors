package com.rps.dto;

import java.util.Random;

/**
 * Object class which represent the game which is played
 * 
 * @author mb
 *
 */
public class Game {
	private long id;
	private Move playerOne, playerTwo;
	
	public Game() {
		this.id = new Random().nextLong();
	}
	
	public long getId() {
		return id;
	}

	public Move getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Move playerOne) {
		this.playerOne = playerOne;
	}

	public Move getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(Move playerTwo) {
		this.playerTwo = playerTwo;
	}
}
