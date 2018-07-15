package com.rps.exception;

/**
 * This exception will be thrown when there is a request to a non-existing
 * {@link Game} resource
 * 
 * @author mb
 *
 */
public class GameDoesNotExistException extends Exception {

	private static final long serialVersionUID = -276180896714262835L;

	public GameDoesNotExistException(long id) {
		super(String.format("The game with id %d does not exist!", id));
	}
}
