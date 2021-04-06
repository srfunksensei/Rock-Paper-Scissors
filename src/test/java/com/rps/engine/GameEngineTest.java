package com.rps.engine;

import com.rps.dto.Game;
import com.rps.dto.Move;
import com.rps.dto.Result;
import org.junit.Assert;
import org.junit.Test;

public class GameEngineTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNoGame() {
		GameEngine.evaluateResult(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNoMoves() {
		final Game game = new Game();
		game.setPlayerOne(Move.Paper);
		
		GameEngine.evaluateResult(null);
	}
	
	@Test
	public void testEvaluate_draw() {
		for(final Move move : Move.values()) {
			final Game game = new Game();
			game.setPlayerOne(move);
			game.setPlayerTwo(move);

			final Result result = GameEngine.evaluateResult(game);

			Assert.assertEquals("Expected different result", Result.Draw, result);
		}
	}

	@Test
	public void testEvaluate_win() {
		for (final Rule rule : GameEngine.RULES) {
			final Game game = new Game();
			game.setPlayerOne(rule.getWinner());
			game.setPlayerTwo(rule.getLoser());

			final Result result = GameEngine.evaluateResult(game);

			Assert.assertEquals("Expected different result", Result.Win, result);
		}
	}

	@Test
	public void testEvaluate_loose() {
		for (final Rule rule : GameEngine.RULES) {
			final Game game = new Game();
			game.setPlayerOne(rule.getLoser());
			game.setPlayerTwo(rule.getWinner());

			final Result result = GameEngine.evaluateResult(game);

			Assert.assertEquals("Expected different result", Result.Loose, result);
		}
	}
}
