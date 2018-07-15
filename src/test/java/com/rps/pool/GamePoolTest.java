package com.rps.pool;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.impl.GamePoolImpl;

public class GamePoolTest {
	
	private final GamePool gamePool = new GamePoolImpl();
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testNonExistingGame() throws GameDoesNotExistException {
		exception.expect(GameDoesNotExistException.class);
		gamePool.get(0);
	}
}
