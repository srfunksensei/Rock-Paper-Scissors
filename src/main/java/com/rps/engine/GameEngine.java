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
	
	private static List<Rule> RULES = Arrays.asList( 
        new Rule(Move.Rock, Move.Scissors),
        new Rule(Move.Paper, Move.Rock),
        new Rule(Move.Scissors, Move.Paper)
	);
	
	/**
	 * Evaluates who won the game
	 * 
	 * @param game
	 * @return
	 */
	public static Result evaluateResult(Game game) {
		if(game == null) {
			String msg = "The game must not be null to evaluate result!";
			throw new IllegalArgumentException(msg);
		}
		
		Move playerOne = game.getPlayerOne(),
				playerTwo = game.getPlayerTwo();
		if(playerOne == null || playerTwo == null) {
			String msg = String.format("The moves must not be null to evalutate result (playerOne: %s, playerTwo: %s)!", playerOne, playerTwo);
			throw new IllegalArgumentException(msg);
		}
		
		Result result = Result.Draw;
		
		Optional<Rule> rule = findWinningRule(playerOne, playerTwo);
		if(rule.isPresent()) {
			result = Result.Win;
		}
		
		rule = findWinningRule(playerTwo, playerOne);
		if(rule.isPresent()) {
			result = Result.Loose;
		}
		
		return result;
	}
	
	/**
	 * Finds if there is a winning rule for passed moves
	 * 
	 * @param playerOne move
	 * @param playerTwo move
	 * @return
	 */
	private static Optional<Rule> findWinningRule(Move playerOne, Move playerTwo)
    {
        return RULES.stream().filter(r -> r.winner == playerOne && r.loser == playerTwo).findFirst();
    }

}
