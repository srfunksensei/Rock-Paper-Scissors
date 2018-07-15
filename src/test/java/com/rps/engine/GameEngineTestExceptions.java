package com.rps.engine;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rps.dto.Game;
import com.rps.dto.Move;
import com.rps.dto.Result;

public class GameEngineTestExceptions {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testNoGame() {
		exception.expect(IllegalArgumentException.class);
		GameEngine.evaluateResult(null);
	}
	
	@Test
	public void testNoMoves() {
		Game game = new Game();
		game.setPlayerOne(Move.Paper);
		
		exception.expect(IllegalArgumentException.class);
		GameEngine.evaluateResult(null);
	}
	
	@Test
	public void testEvaluate() {
		Game game = new Game();
		game.setPlayerOne(Move.Paper);
		game.setPlayerTwo(Move.Paper);
		
		Result result = GameEngine.evaluateResult(game);
		
		Assert.assertEquals(Result.Draw, result);
	}
}
