package com.rps.pool;

import com.rps.exception.GameDoesNotExistException;

/**
 * Represents interface for manipulation with games. Provides methods for 
 * adding/removing/obtaining game resource.
 * 
 * @author mb
 *
 */
public interface GamePool<ID, T> {

	/**
	 Returns game with specified id (if exist), otherwise throws an exception
	 * @param id of the game to be fetched
	 * @return game object
	 * @throws GameDoesNotExistException in case that game with specified id does not exist
	 */
	T get(final ID id) throws GameDoesNotExistException;
	
	/**
	 * Adds game to the pool 
	 * @param game to be added
	 * @return game
	 */
	T add(final T game);
	
	/**
	 * Removes game from the pool
	 * @param id of the game to be removed
	 */
	void remove(final ID id);
}
