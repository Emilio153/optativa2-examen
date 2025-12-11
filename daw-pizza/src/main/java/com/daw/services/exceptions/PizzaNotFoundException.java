package com.daw.services.exceptions;

public class PizzaNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7239101262919209494L;

	public PizzaNotFoundException(String message) {
		super(message);
	}

}
