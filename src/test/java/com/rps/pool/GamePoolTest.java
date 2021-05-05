package com.rps.pool;

import com.rps.dto.Game;
import com.rps.dto.GameType;
import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.impl.GamePoolImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamePoolTest {
	
	private final GamePool<String, Game> gamePool = new GamePoolImpl();
	
	@Test
	public void get_nonExistingGame() {
		Assertions.assertThrows(GameDoesNotExistException.class, () -> gamePool.get("non-existing-id"));
	}

	@Test
	public void get() throws GameDoesNotExistException {
		final Game game = new Game(GameType.PersonVsComputer);
		gamePool.add(game);

		final Game result = gamePool.get(game.getId());
		Assertions.assertEquals(game, result, "Expected different game");
	}

	@Test
	public void add() throws GameDoesNotExistException {
		final Game game = new Game(GameType.PersonVsComputer);

		final Game result = gamePool.add(game);
		Assertions.assertNull(result, "Expected null");

		final Game game1 = gamePool.get(game.getId());
		Assertions.assertEquals(game, game1, "Expected different game");
	}

	@Test
	public void add_sameGameTwice() throws GameDoesNotExistException {
		final Game game = new Game(GameType.PersonVsComputer);

		final Game result = gamePool.add(game);
		Assertions.assertNull(result, "Expected null");

		final Game resultAddSecondTime = gamePool.add(game);
		Assertions.assertNotNull(resultAddSecondTime, "Expected null");


		final Game game1 = gamePool.get(game.getId());
		Assertions.assertEquals(game, game1, "Expected different game");
	}

	@Test
	public void add_nullGame() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> gamePool.add(null));
	}

	@Test
	public void remove() {
		gamePool.remove("id");
	}
}
