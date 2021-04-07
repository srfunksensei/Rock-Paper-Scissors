package com.rps.pool;

import com.rps.dto.Game;
import com.rps.dto.GameType;
import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.impl.GamePoolImpl;
import org.junit.Assert;
import org.junit.Test;

public class GamePoolTest {
	
	private final GamePool<String, Game> gamePool = new GamePoolImpl();
	
	@Test(expected = GameDoesNotExistException.class)
	public void get_nonExistingGame() throws GameDoesNotExistException {
		gamePool.get("non-existing-id");
	}

	@Test
	public void get() throws GameDoesNotExistException {
		final Game game = new Game(GameType.PersonVsComputer);
		gamePool.add(game);

		final Game result = gamePool.get(game.getId());
		Assert.assertEquals("Expected different game", game, result);
	}

	@Test
	public void add() throws GameDoesNotExistException {
		final Game game = new Game(GameType.PersonVsComputer);

		final Game result = gamePool.add(game);
		Assert.assertNull("Expected null", result);

		final Game game1 = gamePool.get(game.getId());
		Assert.assertEquals("Expected different game", game, game1);
	}

	@Test
	public void add_sameGameTwice() throws GameDoesNotExistException {
		final Game game = new Game(GameType.PersonVsComputer);

		final Game result = gamePool.add(game);
		Assert.assertNull("Expected null", result);

		final Game resultAddSecondTime = gamePool.add(game);
		Assert.assertNotNull("Expected null", resultAddSecondTime);


		final Game game1 = gamePool.get(game.getId());
		Assert.assertEquals("Expected different game", game, game1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void add_nullGame() {
		gamePool.add(null);
	}

	@Test
	public void remove() {
		gamePool.remove("id");
	}
}
