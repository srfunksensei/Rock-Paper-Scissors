package com.rps.pool;

import com.rps.dto.Game;
import com.rps.exception.GameDoesNotExistException;

/**
 * Represents interface for manipulation with games. Provides methods for 
 * adding/removing/obtaining game resource.
 * 
 * @author mb
 *
 */
public interface GamePool {
	
	/**
	 * Returns game with specified id (if exist), otherwise throws an exception
	 * @param id
	 * @return
	 */
	Game get(long id) throws GameDoesNotExistException;
	
	/**
	 * Adds game to the pool 
	 * @param game
	 */
	void add(Game game);
	
	/**
	 * Removes game from the pool
	 * @param id
	 */
	void remove(long id);
}
