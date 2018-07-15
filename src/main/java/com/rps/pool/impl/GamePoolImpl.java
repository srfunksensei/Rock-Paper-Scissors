package com.rps.pool.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.rps.dto.Game;
import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.GamePool;

@Service
public class GamePoolImpl implements GamePool {

	private ConcurrentHashMap<Long, Game> games = new ConcurrentHashMap<>();
	 
	/*
	 * (non-Javadoc)
	 * @see com.rps.pool.GamePool#get(java.lang.String)
	 */
	@Override
	public Game get(long id) throws GameDoesNotExistException{
		if(!games.containsKey(id)) {
			throw new GameDoesNotExistException(id);
		}
		
		return games.get(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rps.pool.GamePool#add(com.rps.dto.Game)
	 */
	@Override
	public void add(Game game) {
		games.put(game.getId(), game);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rps.pool.GamePool#remove(java.lang.String)
	 */
	@Override
	public void remove(long id) {
		games.remove(id);
	}

}
