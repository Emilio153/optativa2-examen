package com.daw.services.exceptions;

public class PedidoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 126638385495687392L;

	public PedidoNotFoundException(String message) {
		super(message);
	}

}
