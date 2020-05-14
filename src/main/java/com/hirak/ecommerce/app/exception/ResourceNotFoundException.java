package com.hirak.ecommerce.app.exception;

import lombok.NoArgsConstructor;

/**
 * @author hirak_mahanta
 *
 */
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731746189377504479L;

	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

	public ResourceNotFoundException(final Throwable cause) {
		super(cause);
	}

}
