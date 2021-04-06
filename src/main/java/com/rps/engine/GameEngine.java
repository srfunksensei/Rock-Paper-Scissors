package com.rps.engine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.rps.dto.Game;
import com.rps.dto.Move;
import com.rps.dto.Result;

/**
 * 
 * This class represent the "engine" which calculates outcome of the game
 *  
 * @author mb
 *
 */
public class GameEngine {
	
	public static final List<Rule> RULES = Arrays.asList(
        new Rule(Move.Rock, Move.Scissors),
        new Rule(Move.Paper, Move.Rock),
        new Rule(Move.Scissors, Move.Paper)
	);
	
	/**
	 * Evaluates if player one won the game
	 * 
	 * @param game Game for which result needs to be evaluated
	 * @return if player one won the game
	 */
	public static Result evaluateResult(final Game game) {
		if (game == null) {
			throw new IllegalArgumentException("The game must not be null to evaluate result!");
		}
		
		final Move playerOne = game.getPlayerOne(),
				playerTwo = game.getPlayerTwo();
		if (playerOne == null || playerTwo == null) {
			final String msg = String.format("The moves must not be null to evaluate result (playerOne: %s, playerTwo: %s)!", playerOne, playerTwo);
			throw new IllegalArgumentException(msg);
		}
		
		return isPlayerOneWinner(playerOne, playerTwo) ? Result.Win :
				isPlayerOneWinner(playerTwo, playerOne) ? Result.Loose : Result.Draw;
	}
	
	/**
	 * Finds if player one has won the game
	 * 
	 * @param playerOne move
	 * @param playerTwo move
	 * @return if player one has won the game
	 */
	private static boolean isPlayerOneWinner(final Move playerOne, final Move playerTwo) {
		final Optional<Rule> ruleOpt = RULES.stream().filter(r -> r.getWinner() == playerOne && r.getLoser() == playerTwo).findFirst();
		return ruleOpt.isPresent();
	}

}
