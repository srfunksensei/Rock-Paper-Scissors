package com.rps.engine;

import com.rps.dto.Game;
import com.rps.dto.GameType;
import com.rps.dto.Move;
import com.rps.dto.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameEngineTest {

	@Test
	public void testNoGame() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> GameEngine.evaluateResult(null));
	}
	
	@Test
	public void testNoMoves() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			final Game game = new Game(GameType.PersonVsComputer);
			game.setPlayerOne(Move.Paper);

			GameEngine.evaluateResult(null);
		});
	}
	
	@Test
	public void testEvaluate_draw() {
		for(final Move move : Move.values()) {
			final Game game = new Game(GameType.PersonVsComputer);
			game.setPlayerOne(move);
			game.setPlayerTwo(move);

			final Result result = GameEngine.evaluateResult(game);

			Assertions.assertEquals(Result.Draw, result, "Expected different result");
		}
	}

	@Test
	public void testEvaluate_win() {
		for (final Rule rule : GameEngine.RULES) {
			final Game game = new Game(GameType.PersonVsComputer);
			game.setPlayerOne(rule.getWinner());
			game.setPlayerTwo(rule.getLoser());

			final Result result = GameEngine.evaluateResult(game);

			Assertions.assertEquals(Result.Win, result, "Expected different result");
		}
	}

	@Test
	public void testEvaluate_loose() {
		for (final Rule rule : GameEngine.RULES) {
			final Game game = new Game(GameType.PersonVsComputer);
			game.setPlayerOne(rule.getLoser());
			game.setPlayerTwo(rule.getWinner());

			final Result result = GameEngine.evaluateResult(game);

			Assertions.assertEquals(Result.Loose, result, "Expected different result");
		}
	}
}
