package com.rps.exception;

import com.rps.dto.Game;

/**
 * This exception will be thrown when there is a request to a non-existing
 * {@link Game} resource
 * 
 * @author mb
 *
 */
public class GameDoesNotExistException extends Exception {

	private static final long serialVersionUID = -276180896714262835L;

	public GameDoesNotExistException(final String id) {
		super(String.format("The game with id %s does not exist!", id));
	}
}
